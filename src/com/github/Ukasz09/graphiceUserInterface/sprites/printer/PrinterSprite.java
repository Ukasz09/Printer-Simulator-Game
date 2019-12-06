package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.graphiceUserInterface.sprites.ISpriteGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.inks.InkSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.IPaperGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.ImagePaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.WhitePaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterLowerBody;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterSalver;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterUpperBody;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class PrinterSprite extends SpriteWithEventHandler {
    private final static double DEFAULT_WIDTH = 280;
    private final static double DEFAULT_HEIGHT = 150;
    private final static double DEFAULT_PRINTING_SPEED = 1;
    private final static double UPPER_TO_LOWER_BODY_PROPORTION = 0.4;
    private final static double SALVER_TO_PRINTER_WIDTH_PROPORTION = 0.6;
    private final static double SALVER_TO_PRINTER_HEIGHT_PROPORTION = 0.4;
    private final static double DEFAULT_SPACE_BETWEEN_INKS = 20;

    private Printer printer;

    private Deque<IPaperGraphic> whitePapersQueue;
    private Deque<IPaperGraphic> printedPapersQueue;
    private List<ISpriteGraphic> inksList;

    private PrinterLowerBody printerLowerBody;
    private PrinterUpperBody printerUpperBody;
    private PrinterSalver printerUpperSalver;
    private PrinterSalver printerDownSalver;

    private Image posterImage = ImagesProperties.flowerSprite(); //TODO: tmp, dopoki nie ma komputera

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterSprite(double positionX, double positionY) {
        super(DEFAULT_WIDTH, DEFAULT_HEIGHT, positionX, positionY);
        printer = new Printer();
        initializeLists();
        initializeAllSprites();
        addUpperSalverEventHandler();
        addPrinterEventHandler();

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
        addWhitePaperSprite();
        addInkSprites();
    }

    private void addInkSprites() {
        double positionX = manager.getRightFrameBorder();
        double positionY = 0;

        for (Map.Entry<ColorEnum, ColorInk> entry : printer.getPrinterIncs().entrySet()) {
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

    private void addWhitePaperSprite() {
        Point2D paperPosition = calculatePaperPosition();
        for (int i = 0; i < printer.getAvailablePaperSheets(); i++)
            whitePapersQueue.push(new WhitePaperSprite(paperPosition.getX(), paperPosition.getY(), DEFAULT_PRINTING_SPEED));
    }

    private Point2D calculatePaperPosition() {
        double paperPositionX = printerUpperSalver.getPositionX() + printerUpperSalver.getWidth() / 2 - WhitePaperSprite.DEFAULT_WIDTH / 2;
        double paperPositionY = printerUpperSalver.getPositionY() + printerUpperSalver.getHeight() - WhitePaperSprite.DEFAULT_HEIGHT;
        return new Point2D(paperPositionX, paperPositionY);
    }

    private void addNewWhitePaper() {
        Point2D paperPosition = calculatePaperPosition();
        WhitePaperSprite newPaper = new WhitePaperSprite(paperPosition.getX(), paperPosition.getY(), DEFAULT_PRINTING_SPEED);
        whitePapersQueue.add(newPaper);
        printer.refillAvailablePaper(1);
    }

    private void addUpperSalverEventHandler() {
        printerUpperSalver.addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> addNewWhitePaper());
    }


    //todo: tmp dopoki nie ma komputera
    private void addPrinterEventHandler() {
        addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (posterImage != null) {
                print(posterImage, true, 1);
            } else System.err.println("ERROR: poster=null");
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

//        TODO: testowo
        System.out.println("Papers:" + whitePapersQueue.size());
        System.out.println("Printed: " + printedPapersQueue.size());
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
                printer.setInPrintingTime(false);
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

    public void print(Image image, boolean multicolor, int amountOfCopy) {
        if (!printer.isInPrintingTime())
            if (printer.printImage(image, multicolor, amountOfCopy)) {
                whitePapersQueue.peek().doAnimation();
                addPrintedPageSprite();
                printer.setInPrintingTime(true);
            }
    }

    private void addPrintedPageSprite() {
        Point2D paperPosition = calculatePaperPosition();
        double stopAnimationYPosition = printerDownSalver.getPositionY();
        ImagePaperSprite newPaper = new ImagePaperSprite(paperPosition.getX(), paperPosition.getY(), DEFAULT_PRINTING_SPEED, posterImage, stopAnimationYPosition);
        newPaper.doAnimation();
        addPaperEventHandler(newPaper, printedPageEventHandler());
        printedPapersQueue.push(newPaper);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public static double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
}
