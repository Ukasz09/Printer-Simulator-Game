package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterContainersException;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterException;
import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsPlayer;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.ISpriteGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObservable;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane.ErrorKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.inks.InkSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.IPaperGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.ImagePaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.PaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.WhitePaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterLowerBody;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterSalver;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterUpperBody;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.PrinterState;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class PrinterSprite extends SpriteWithEventHandler implements IEventKindObservable {
    public final static double WIDTH_TO_FRAME_PROPORTION = 28.0 / 160;
    public final static double HEIGHT_TO_FRAME_PROPORTION = 15.0 / 90;
    private final static double DEFAULT_PRINTING_SPEED = 4;
    private final static double UPPER_TO_LOWER_BODY_PROPORTION = 0.4;
    private final static double SALVER_TO_PRINTER_WIDTH_PROPORTION = 0.6;
    private final static double SALVER_TO_PRINTER_HEIGHT_PROPORTION = 0.4;
    private final static double SPACE_BETWEEN_INKS_TO_FRAME_WIDTH_PROPORTION = 2.0 / 160;
    private final static double SPACE_BETWEEN_PAPERS_TO_FRAME_WIDTH_PROPORTION = 3.0 / 1600; //to made all papers visual in stack (avoid superimpose)
    private final static double DEFAULT_PRINTING_VOLUME = 0.9;
    private final ErrorKind[] logicPrinterGraphicErrors = {
            ErrorKind.RUN_OUT_OF_INK_ERROR,
            ErrorKind.FULL_PRINTED_PAPER_STACK_ERROR,
            ErrorKind.RUN_OUT_OF_PAPER_ERROR,
    };
    private Printer printer;

    private Deque<IPaperGraphic> whitePapersQueue;
    private Deque<IPaperGraphic> printedPapersQueue;
    private List<ISpriteGraphic> inksList;
    private Map<String, ErrorKind> logicPrinterErrors;
    private Set<IEventKindObserver> observers;

    private PrinterLowerBody printerLowerBody;
    private PrinterUpperBody printerUpperBody;
    private PrinterSalver printerUpperSalver;
    private PrinterSalver printerDownSalver;

    private SoundsPlayer printingSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterSprite(double positionX, double positionY, double inkPositionY) {
        super(WIDTH_TO_FRAME_PROPORTION * ViewManager.getInstance().getRightFrameBorder(), HEIGHT_TO_FRAME_PROPORTION * ViewManager.getInstance().getBottomFrameBorder(), positionX, positionY);
        observers = new HashSet<>();
        printer = new Printer();
        printingSound = SoundsProperties.printing(DEFAULT_PRINTING_VOLUME);
        initializeLists();
        initializeAllSprites(inkPositionY);
        addUpperSalverEventHandler();
        logicPrinterErrors = new HashMap<>();
        addAllPrintErrorsToMap();

        //TODO: tmp dopoki nie ma komputera zeby nie zaslanialy inne czesci eventHandlera na drukowanie
        printerLowerBody.setImageViewVisable(false);
        printerUpperBody.setImageViewVisable(false);
        printerDownSalver.setImageViewVisable(false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initializeLists() {
        whitePapersQueue = new LinkedList<>();
        printedPapersQueue = new LinkedList<>();
        inksList = new ArrayList<>();
    }

    private void initializeAllSprites(double inkPositionY) {
        //ORDER OF INITIALIZATION IS VERY IMPORTANT!
        initializeLowerBody(width, height);
        initializeUpperBody(width, height, printerLowerBody.getHeight());
        initializeUpperSalver(width, height, printerUpperBody.getPositionY());
        initializeDownSalver(width, height);
        initializeWhitePapersList();
        addInkSprites(inkPositionY);
    }

    private void addInkSprites(double inkPositionY) {
        double positionX = manager.getRightFrameBorder();
        double positionY = inkPositionY;

        for (Map.Entry<ColorEnum, ColorInk> entry : printer.getPrinterInks().entrySet()) {
            positionX -= (InkSprite.WIDTH_TO_FRAME_PROPORTION * manager.getRightFrameBorder()
                    + SPACE_BETWEEN_INKS_TO_FRAME_WIDTH_PROPORTION * manager.getRightFrameBorder());
            inksList.add(new InkSprite(getInkImageByColor(entry.getKey()), entry.getValue(), positionX, positionY));
        }
    }

    private Image getInkImageByColor(ColorEnum color) {
        switch (color) {
            case RED:
                return ImagesProperties.redInkSprite();
            case YEALLOW:
                return ImagesProperties.yellowInkSprite();
            case BLUE:
                return ImagesProperties.blueInkSprite();
            case BLACK:
                return ImagesProperties.blackInkSprite();
            default:
                return ImagesProperties.whiteInkSprite();
        }
    }

    private void initializeLowerBody(double printerWidth, double printerHeight) {
        Point2D lowerBodySize = calculateLowerBodySize(printerWidth, printerHeight);
        Point2D lowerBodyPosition = calculateLowerBodyPosition(positionX, positionY + HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder(), lowerBodySize.getY());
        printerLowerBody = new PrinterLowerBody(lowerBodySize.getX(), lowerBodySize.getY(), lowerBodyPosition.getX(), lowerBodyPosition.getY());
    }

    private static Point2D calculateLowerBodySize(double printerWidth, double printerHeight) {
        return new Point2D(printerWidth, printerHeight * UPPER_TO_LOWER_BODY_PROPORTION);
    }

    private static Point2D calculateLowerBodyPosition(double positionX, double positionY, double lowerBodyHeight) {
        return new Point2D(positionX, positionY - lowerBodyHeight);
    }

    private void initializeUpperBody(double printerWidth, double printerHeight, double lowerBodyHeight) {
        Point2D upperBodySize = calculateUpperBodySize(printerWidth, printerHeight);
        Point2D upperBodyPosition = calculateUpperBodyPosition(positionX, positionY + HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder(), lowerBodyHeight, upperBodySize.getY());
        printerUpperBody = new PrinterUpperBody(upperBodySize.getX(), upperBodySize.getY(), upperBodyPosition.getX(), upperBodyPosition.getY());
    }

    private static Point2D calculateUpperBodySize(double printerWidth, double printerHeight) {
        return new Point2D(printerWidth, printerHeight - printerHeight * UPPER_TO_LOWER_BODY_PROPORTION);
    }

    private static Point2D calculateUpperBodyPosition(double printerPositionX, double printerPositionY, double lowerBodyHeight, double upperBodyHeight) {
        return new Point2D(printerPositionX, printerPositionY - lowerBodyHeight - upperBodyHeight);
    }

    private void initializeUpperSalver(double printerWidth, double printerHeight, double upperBodyPositionY) {
        Point2D salverSize = calculateUpperSalverSize(printerWidth, printerHeight);
        Point2D salverPosition = calculateUpperSalverPosition(positionX, upperBodyPositionY, printerWidth, printerUpperBody.getHeight(), salverSize.getX(), salverSize.getY());
        printerUpperSalver = new PrinterSalver(salverSize.getX(), salverSize.getY(), salverPosition.getX(), salverPosition.getY());
    }

    private static Point2D calculateUpperSalverSize(double printerWidth, double printerHeight) {
        return new Point2D(printerWidth * SALVER_TO_PRINTER_WIDTH_PROPORTION, printerHeight * SALVER_TO_PRINTER_HEIGHT_PROPORTION);
    }

    private static Point2D calculateUpperSalverPosition(double printerPositionX, double upperBodyPositionY,
                                                        double printerWidth, double upperBodyHeight, double salverWidth, double salverHeight) {
        double positionX = printerPositionX + printerWidth / 2 - salverWidth / 2;
        double positionY = upperBodyPositionY - salverHeight + upperBodyHeight * PrinterUpperBody.PRINT_HOLE_TO_PRINTER_PROPORTION;
        return new Point2D(positionX, positionY);
    }

    private void initializeDownSalver(double printerWidth, double printerHeight) {
        Point2D salverSize = calculateDownSalverSize(printerWidth, printerHeight);
        Point2D salverPosition = calculateDownSalverPosition(positionX, positionY + HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder(), width, printerLowerBody.getHeight(), salverSize.getX());
        printerDownSalver = new PrinterSalver(salverSize.getX(), salverSize.getY(), salverPosition.getX(), salverPosition.getY());
    }

    private static Point2D calculateDownSalverSize(double printerWidth, double printerHeight) {
        Point2D size = calculateUpperSalverSize(printerWidth, printerHeight);
        Point2D newSize = size.add(0, size.getY() / 2);
        return newSize;
    }

    private static Point2D calculateDownSalverPosition(double printerPositionX, double printerPositionY,
                                                       double printerWidth, double lowerBodyHeight, double salverWidth) {
        double positionX = printerPositionX + printerWidth / 2 - salverWidth / 2;
        double positionY = printerPositionY - PrinterLowerBody.PRINT_HOLE_TO_PRINTER_PROPORTION * lowerBodyHeight;
        return new Point2D(positionX, positionY);
    }

    private void initializeWhitePapersList() {
        for (int i = 0; i < printer.getAvailablePaperSheets(); i++)
            addNewWhitePaperSprite();
    }

    private Point2D calculateWhitePaperPosition() {
        Point2D paperPosition;
        if (whitePapersQueue.isEmpty() || (whitePapersQueue.size() == 1 && printer.isInPrintingTime()))
            paperPosition =
                    getWhitePaperPositionBySalver(PaperSprite.WIDTH_TO_FRAME_PROPORTION * manager.getRightFrameBorder(),
                            PaperSprite.HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder());
        else paperPosition = getWhitePaperPositionByOtherPaper(whitePapersQueue.getLast());

        return new Point2D(paperPosition.getX(), paperPosition.getY());
    }

    private Point2D getWhitePaperPositionBySalver(double paperWidth, double paperHeight) {
        double salverPositionX = printerUpperSalver.getPositionX();
        double salverPositionY = printerUpperSalver.getPositionY();
        double salverWidth = printerUpperSalver.getWidth();
        double salverHeight = printerUpperSalver.getHeight();
        double immersionDepthOfPaper = SPACE_BETWEEN_PAPERS_TO_FRAME_WIDTH_PROPORTION * manager.getRightFrameBorder() * printer.getMaxQtyOfAvailableSheets();
        double paperPositionX = salverPositionX + salverWidth / 2 - paperWidth / 2;
        double paperPositionY = salverPositionY + salverHeight - paperHeight + immersionDepthOfPaper;
        return new Point2D(paperPositionX, paperPositionY);
    }

    private Point2D getWhitePaperPositionByOtherPaper(IPaperGraphic lastPaper) {
        double paperPositionX = lastPaper.getPositionX();
        double paperPositionY = lastPaper.getPositionY() - SPACE_BETWEEN_PAPERS_TO_FRAME_WIDTH_PROPORTION * manager.getRightFrameBorder();
        return new Point2D(paperPositionX, paperPositionY);
    }

    private void addNewWhitePaperSprite() {
        Point2D paperPosition = calculateWhitePaperPosition();
        WhitePaperSprite newPaper = new WhitePaperSprite(paperPosition.getX(), paperPosition.getY(), DEFAULT_PRINTING_SPEED);
        addPaperEventHandler(newPaper, addingPaperEventHandler());
        whitePapersQueue.add(newPaper);
    }

    private void addUpperSalverEventHandler() {
        printerUpperSalver.addNewEventHandler(MouseEvent.MOUSE_CLICKED, addingPaperEventHandler());
    }

    private EventHandler addingPaperEventHandler() {
        return event -> {
            try {
                printer.refillAvailablePaper(1);
                addNewWhitePaperSprite();
            } catch (PrinterContainersException e) {
                if (e.getMessage().equals(ErrorKind.FULL_AVAILABLE_PAPER_STACK.errorCode))
                    notifyObservers(EventKind.FULL_AVAILABLE_PAPER_STACK);
                else Logger.logError(getClass(), "Unknown error: " + e.getMessage());
            }
        };
    }

    private void addAllPrintErrorsToMap() {
        for (ErrorKind e : logicPrinterGraphicErrors)
            logicPrinterErrors.put(e.errorCode, e);
    }

    @Override
    public void render() {
        renderPrinterComponents();
        renderInks();
    }

    private void renderPrinterComponents() {
        //ORDER OF INITIALIZATION IS VERY IMPORTANT!
        printerUpperSalver.render();
        printerDownSalver.render();
        renderPrintedPages();
        renderWhitePapers();
        printerUpperBody.render();
        printerLowerBody.render();
    }

    private void renderWhitePapers() {
        Iterator<IPaperGraphic> iterator = whitePapersQueue.descendingIterator();
        while (iterator.hasNext())
            iterator.next().render();
    }

    private void renderPrintedPages() {
        Iterator<IPaperGraphic> iterator = printedPapersQueue.descendingIterator();
        while (iterator.hasNext())
            iterator.next().render();
    }

    private void renderInks() {
        for (ISpriteGraphic colorInk : inksList)
            colorInk.render();
    }

    @Override
    public void update() {
        super.update();
        updatePrinterParts();
        updateWhitePapers();
        updatePrintedPapers();
        updateColorInks();
        updatePrinterState();

        //todo: tmp
//        System.out.println(printer.isInPrintingTime());
//        System.out.println("White: SPRITE: " + whitePapersQueue.size() + ", LOGIC: " + printer.getAvailablePaperSheets());
//        System.out.println("Printed: SPRITE: " + printedPapersQueue.size() + ", LOGIC: " + printer.getQtyOfPrintedPages());
//        System.out.println("\n\n\n\n\n\n\n");
    }

    private void updatePrinterParts() {
        printerUpperSalver.update();
        printerUpperBody.update();
        printerLowerBody.update();
        printerDownSalver.update();
    }

    private void updateWhitePapers() {
        for (IPaperGraphic paper : whitePapersQueue)
            paper.update();

        if (!whitePapersQueue.isEmpty())
            if (whitePapersQueue.peek().canBeDestroyedNow()) {
                whitePapersQueue.pop().removeNodeFromRoot();
            }
    }

    private void updatePrintedPapers() {
        for (IPaperGraphic paper : printedPapersQueue)
            paper.update();
    }

    private void updateColorInks() {
        for (ISpriteGraphic colorInk : inksList)
            colorInk.update();
    }

    private void updatePrinterState() {
        if (printer.isInPrintingTime()) {
            if (printedPapersQueue.peek().canBeDestroyedNow()) {
                printingSound.stopSound();
                printer.setInPrintingTime(false);
            }

            printerUpperBody.changeState(PrinterState.PRINTING);
        } else printerUpperBody.changeState(PrinterState.STANDBY);
    }

    public ErrorKind print(Image imageToPrint, boolean multicolor, int amountOfCopy) {
        if (!printer.isInPrintingTime()) {
            try {
                printer.printImage(imageToPrint, multicolor, amountOfCopy);
            } catch (PrinterException e) {
                ErrorKind errorKind = logicPrinterErrors.get(e.getMessage());
                return (errorKind == null) ? ErrorKind.UNKNOWN_ERROR : errorKind;
            }
            whitePapersQueue.peek().doAnimation();
            addPrintedPageSprite(imageToPrint);
            printer.setInPrintingTime(true);
            printingSound.playSound();
            return ErrorKind.NO_ERRORS;
        }

        return ErrorKind.PRINTER_IS_BUSY_ERROR;
    }

    private void addPrintedPageSprite(Image imageToPrint) {
        ImagePaperSprite newPaper = new ImagePaperSprite(whitePapersQueue.peek().getPositionX(), whitePapersQueue.peek().getPositionY(),
                DEFAULT_PRINTING_SPEED, imageToPrint, getPaperAnimationStopPositionY());
        initializePrintedPage(newPaper);
        printedPapersQueue.push(newPaper);
    }

    private double getPaperAnimationStopPositionY() {
        if (printedPapersQueue.isEmpty())
            return printerDownSalver.getPositionY();

        IPaperGraphic lastPrintedPaper = printedPapersQueue.peek();
        return lastPrintedPaper.getPositionY() - SPACE_BETWEEN_PAPERS_TO_FRAME_WIDTH_PROPORTION * manager.getRightFrameBorder(); //todo: dac mozliwosc printowania tylko jak calkoem skonczy siw wczesj9eisze
    }

    private void initializePrintedPage(IPaperGraphic newPrintedPage) {
        newPrintedPage.doAnimation();
        addPaperEventHandler(newPrintedPage, printedPageEventHandler());
    }

    private void addPaperEventHandler(IPaperGraphic paper, EventHandler eventHandler) {
        paper.addNewEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    private EventHandler printedPageEventHandler() {
        return event -> tryToTakePrintedPage();
    }

    private void tryToTakePrintedPage() {
        if (printedPapersQueue.peek().canBeDestroyedNow()) {
            printedPapersQueue.pop().removeNodeFromRoot();
            printer.takePrintedPages(1);
        }
    }

    @Override
    public void attachObserver(IEventKindObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(IEventKindObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(EventKind eventKind) {
        for (IEventKindObserver observer : observers)
            observer.updateObserver(eventKind);
    }
}
