package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.applicationLogic.printer.printOption.*;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterContainersException;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterException;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsPlayer;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.ISpriteGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.inks.InkSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.IPaperGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.ImagePaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.PaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.WhitePaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterLowerBody;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterSalver;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterUpperBody;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class PrinterSprite extends SpriteWithEventHandler {
    public final static double DEFAULT_WIDTH = 280;
    public final static double DEFAULT_HEIGHT = 150;
    private final static double DEFAULT_PRINTING_SPEED = 4;
    private final static double UPPER_TO_LOWER_BODY_PROPORTION = 0.4;
    private final static double SALVER_TO_PRINTER_WIDTH_PROPORTION = 0.6;
    private final static double SALVER_TO_PRINTER_HEIGHT_PROPORTION = 0.4;
    private final static double DEFAULT_SPACE_BETWEEN_INKS = 20;
    private final static double DEFAULT_SPACE_BETWEEN_PAPERS = 3; //to made all papers visual in stack (avoid superimpose)
    private final static double DEFAULT_PRINTING_VOLUME = 0.9;

    private Printer printer;

    private Deque<IPaperGraphic> whitePapersQueue;
    private Deque<IPaperGraphic> printedPapersQueue;
    private List<ISpriteGraphic> inksList;

    private PrinterLowerBody printerLowerBody;
    private PrinterUpperBody printerUpperBody;
    private PrinterSalver printerUpperSalver;
    private PrinterSalver printerDownSalver;

    private SoundsPlayer printingSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterSprite(double positionX, double positionY) {
        super(DEFAULT_WIDTH, DEFAULT_HEIGHT, positionX, positionY);
        printer = new Printer();
        printingSound = SoundsProperties.printing(DEFAULT_PRINTING_VOLUME);
        initializeLists();
        initializeAllSprites();
        addUpperSalverEventHandler();

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

    private void initializeAllSprites() {
        //ORDER OF INITIALIZATION IS VERY IMPORTANT!
        initializeLowerBody();
        initializeUpperBody(printerLowerBody.getHeight());
        initializeUpperSalver(printerUpperBody.getPositionY());
        initializeDownSalver();
        initializeWhitePapersList();
        addInkSprites();
    }

    private void addInkSprites() {
        double positionX = manager.getRightFrameBorder();
        double positionY = 0;

        for (Map.Entry<ColorEnum, ColorInk> entry : printer.getPrinterInks().entrySet()) {
            positionX -= (InkSprite.DEFAULT_WIDTH + DEFAULT_SPACE_BETWEEN_INKS);
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

    private void initializeLowerBody() {
        Point2D lowerBodySize = calculateLowerBodySize(width, height);
        Point2D lowerBodyPosition = calculateLowerBodyPosition(positionX, positionY + DEFAULT_HEIGHT, lowerBodySize.getY());
        printerLowerBody = new PrinterLowerBody(lowerBodySize.getX(), lowerBodySize.getY(), lowerBodyPosition.getX(), lowerBodyPosition.getY());
    }

    private static Point2D calculateLowerBodySize(double printerWidth, double printerHeight) {
        return new Point2D(printerWidth, printerHeight * UPPER_TO_LOWER_BODY_PROPORTION);
    }

    private static Point2D calculateLowerBodyPosition(double positionX, double positionY, double lowerBodyHeight) {
        return new Point2D(positionX, positionY - lowerBodyHeight);
    }

    private void initializeUpperBody(double lowerBodyHeight) {
        Point2D upperBodySize = calculateUpperBodySize(width, height);
        Point2D upperBodyPosition = calculateUpperBodyPosition(positionX, positionY + DEFAULT_HEIGHT, lowerBodyHeight, upperBodySize.getY());
        printerUpperBody = new PrinterUpperBody(upperBodySize.getX(), upperBodySize.getY(), upperBodyPosition.getX(), upperBodyPosition.getY());
    }

    private static Point2D calculateUpperBodySize(double printerWidth, double printerHeight) {
        return new Point2D(printerWidth, printerHeight - printerHeight * UPPER_TO_LOWER_BODY_PROPORTION);
    }

    private static Point2D calculateUpperBodyPosition(double printerPositionX, double printerPositionY, double lowerBodyHeight, double upperBodyHeight) {
        return new Point2D(printerPositionX, printerPositionY - lowerBodyHeight - upperBodyHeight);
    }

    private void initializeUpperSalver(double upperBodyPositionY) {
        Point2D salverSize = calculateUpperSalverSize(width, height);
        Point2D salverPosition = calculateUpperSalverPosition(positionX, upperBodyPositionY, width, printerUpperBody.getHeight(), salverSize.getX(), salverSize.getY());
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

    private void initializeDownSalver() {
        Point2D salverSize = calculateDownSalverSize(width, height);
        Point2D salverPosition = calculateDownSalverPosition(positionX, positionY + DEFAULT_HEIGHT, width, printerLowerBody.getHeight(), salverSize.getX());
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
            paperPosition = getWhitePaperPositionBySalver(PaperSprite.DEFAULT_WIDTH, PaperSprite.DEFAULT_HEIGHT);
        else paperPosition = getWhitePaperPositionByOtherPaper(whitePapersQueue.getLast());

        return new Point2D(paperPosition.getX(), paperPosition.getY());
    }

    private Point2D getWhitePaperPositionBySalver(double paperWidth, double paperHeight) {
        double salverPositionX = printerUpperSalver.getPositionX();
        double salverPositionY = printerUpperSalver.getPositionY();
        double salverWidth = printerUpperSalver.getWidth();
        double salverHeight = printerUpperSalver.getHeight();
        double immersionDepthOfPaper = DEFAULT_SPACE_BETWEEN_PAPERS * printer.getMaxQtyOfAvailableSheets();
        double paperPositionX = salverPositionX + salverWidth / 2 - paperWidth / 2;
        double paperPositionY = salverPositionY + salverHeight - paperHeight + immersionDepthOfPaper;
        return new Point2D(paperPositionX, paperPositionY);
    }

    private Point2D getWhitePaperPositionByOtherPaper(IPaperGraphic lastPaper) {
        double paperPositionX = lastPaper.getPositionX();
        double paperPositionY = lastPaper.getPositionY() - DEFAULT_SPACE_BETWEEN_PAPERS;
        return new Point2D(paperPositionX, paperPositionY);
    }

    private void addNewWhitePaperSprite() {
        Point2D paperPosition = calculateWhitePaperPosition();
        WhitePaperSprite newPaper = new WhitePaperSprite(paperPosition.getX(), paperPosition.getY(), DEFAULT_PRINTING_SPEED);
        whitePapersQueue.add(newPaper);
    }

    private void addUpperSalverEventHandler() {
        printerUpperSalver.addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                printer.refillAvailablePaper(1);
                addNewWhitePaperSprite();
            } catch (PrinterContainersException e) {
                Logger.logError(getClass(), e.getMessage() + "cause: " + e.getCause().getMessage());
            }
        });
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
        }
    }

    public void print(Image imageToPrint, boolean multicolor, int amountOfCopy) throws PrinterException {
        if (!printer.isInPrintingTime()) {
            try {
                printer.printImage(imageToPrint, multicolor, amountOfCopy);
            } catch (PrinterException e) {
                throw e;
            }
            whitePapersQueue.peek().doAnimation();
            addPrintedPageSprite(imageToPrint);
            printer.setInPrintingTime(true);
            printingSound.playSound();
        }
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
        return lastPrintedPaper.getPositionY() - DEFAULT_SPACE_BETWEEN_PAPERS; //todo: dac mozliwosc printowania tylko jak calkoem skonczy siw wczesj9eisze
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
}
