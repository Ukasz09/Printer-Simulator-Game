package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.IPaperGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.ImagePaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers.WhitePaperSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterLowerBody;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterSalver;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts.PrinterUpperBody;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class PrinterSprite extends SpriteWithEventHandler {

    //TODO: przestalo dzialac drukowanie
    private final static double DEFAULT_WIDTH = 280;
    private final static double DEFAULT_HEIGHT = 150;
    private final static double DEFAULT_PRINTING_SPEED = 1;
    private final static double UPPER_TO_LOWER_BODY_PROPORTION = 0.4;
    private final static double SALVER_TO_PRINTER_WIDTH_PROPORTION = 0.6;
    private final static double SALVER_TO_PRINTER_HEIGHT_PROPORTION = 0.4;

    private Printer printer;
    private Deque<IPaperGraphic> whitePapersList;
    private Deque<IPaperGraphic> printedPapersList;

    private PrinterLowerBody printerLowerBody;
    private PrinterUpperBody printerUpperBody;
    private PrinterSalver printerUpperSalver;
    private PrinterSalver printerDownSalver;

    private Image posterImage = ImagesProperties.flowerSprite(); //TODO: tmp, dopoki nie ma komputera

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterSprite(double positionX, double positionY) {
        super(DEFAULT_WIDTH, DEFAULT_HEIGHT, positionX, positionY);
        printer = new Printer();
        whitePapersList = new LinkedList<>();
        printedPapersList = new LinkedList<>();

        //ORDER OF INITIALIZATION IS VERY IMPORTANT!
        initializeLowerBody();
        initializeUpperBody(printerLowerBody.getHeight());
        initializeUpperSalver(printerUpperBody.getPositionY());
        initializeDownSalver();
        addWhitePaperSprite();

        addUpperSalverEventHandler();
        addPrinterEventHandler(); //todo: tmp dopoki nie ma komuptera

        //TODO: tmp dopoki nie ma komputera zeby nie zaslanialy inne czesci eventHandlera na drukowanie
        printerLowerBody.setImageViewVisable(false);
        printerUpperBody.setImageViewVisable(false);
        printerDownSalver.setImageViewVisable(false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
            whitePapersList.push(new WhitePaperSprite(paperPosition.getX(), paperPosition.getY(), DEFAULT_PRINTING_SPEED));
    }

    private Point2D calculatePaperPosition() {
        double paperPositionX = printerUpperSalver.getPositionX() + printerUpperSalver.getWidth() / 2 - WhitePaperSprite.getDefaultWidth() / 2;
        double paperPositionY = printerUpperSalver.getPositionY() + printerUpperSalver.getHeight() - WhitePaperSprite.getDefaultHeight();
        return new Point2D(paperPositionX, paperPositionY);
    }

    private void addUpperSalverEventHandler() {
        printerUpperSalver.addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> addNewWhitePaper());
    }

    private void addNewWhitePaper() {
        Point2D paperPosition = calculatePaperPosition();
        WhitePaperSprite newPaper = new WhitePaperSprite(paperPosition.getX(), paperPosition.getY(), DEFAULT_PRINTING_SPEED);
        whitePapersList.add(newPaper);
        printer.refillAvailablePaper(1);
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
        //ORDER OF INITIALIZATION IS VERY IMPORTANT!
        printerUpperSalver.render();
        printerDownSalver.render();
        renderPrintedPages();
        renderWhitePapers();
        printerUpperBody.render();
        printerLowerBody.render();
    }

    private void renderWhitePapers() {
        Iterator<IPaperGraphic> iterator = whitePapersList.descendingIterator();
        while (iterator.hasNext())
            iterator.next().render();
    }

    private void renderPrintedPages() {
        Iterator<IPaperGraphic> iterator = printedPapersList.descendingIterator();
        while (iterator.hasNext())
            iterator.next().render();
    }

    @Override
    public void update() {
        super.update();
        updatePrinterParts();
        updateWhitePapers();
        updatePrintedPapers();

//        //TODO: testowo
        System.out.println("Papers:" + whitePapersList.size());
        System.out.println("Printed: " + printedPapersList.size());
    }

    private void updatePrinterParts() {
        printerUpperSalver.update();
        printerUpperBody.update();
        printerLowerBody.update();
        printerDownSalver.update();
    }

    private void updateWhitePapers() {
        for (IPaperGraphic paper : whitePapersList)
            paper.update();

        if (!whitePapersList.isEmpty())
            if (whitePapersList.peek().canBeDestroyedNow()) {
                printer.setInPrintingTime(false);
                whitePapersList.pop();
            }
    }

    private void updatePrintedPapers() {
        for (IPaperGraphic paper : printedPapersList)
            paper.update();

        if (!printedPapersList.isEmpty())
            if (printedPapersList.peek().canBeDestroyedNow())
                printedPapersList.pop();
    }

    public void print(Image image, boolean multicolor, int amountOfCopy) {
        if (!printer.isInPrintingTime())
            if (printer.printImage(image, multicolor, amountOfCopy)) {
                whitePapersList.peek().doAnimation();
                addPrintedPageSprite();
                printer.setInPrintingTime(true);
            }
    }

    private void addPrintedPageSprite() {
        Point2D paperPosition = calculatePaperPosition();
        double stopAnimationYPosition = printerDownSalver.getPositionY();
        ImagePaperSprite newPaper = new ImagePaperSprite(paperPosition.getX(), paperPosition.getY(), DEFAULT_PRINTING_SPEED, posterImage, stopAnimationYPosition);
        newPaper.doAnimation();
        printedPapersList.push(newPaper);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public static double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
}
