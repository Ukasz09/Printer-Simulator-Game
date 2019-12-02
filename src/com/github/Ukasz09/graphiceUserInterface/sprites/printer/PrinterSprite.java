package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.applicationLogic.printer.paper.PrinterPaper;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class PrinterSprite extends Sprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static Image DEFAULT_PRINTER_IMAGE = ImagesProperties.printerBodySprite();
    private final static double DEFAULT_WIDTH = 280;
    private final static double DEFAULT_HEIGHT = 180;
    private final static double DEFAULT_SPACE_BETWEEN_INKS = 20;

    private Printer printer;

    private ArrayList<InkSprite> inkSpriteList;
    private ArrayList<WhitePaperSprite> availablePapersList;
    private PrinterSalver printerUpSalver;
    private PrinterSalver printerDownSalver;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterSprite() {
        super(DEFAULT_PRINTER_IMAGE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        printer = new Printer();
        printerUpSalver = new PrinterSalver(width, height);
        printerDownSalver = new PrinterSalver(width, height / 2);
        inkSpriteList = new ArrayList<>();
        addInkSprites();
        setInkBoxesPosition(DEFAULT_SPACE_BETWEEN_INKS);
        availablePapersList = new ArrayList<>();
        addAvailablePaperSprite();

        makeAndAddImageViewToRoot();
        addEventHandler();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addInkSprites() {
        printer.getPrinterIncs().forEach((k, v) -> {
            inkSpriteList.add(new InkSprite(getInkImageByColor(k), v));
        });
    }

    private void addAvailablePaperSprite() {
        for (int i = 0; i < printer.getAvailablePaperSheets(); i++)
            availablePapersList.add(new WhitePaperSprite());
    }

    private void setInkBoxesPosition(double spaceBetweenInks) {
        double positionX = manager.getRightFrameBorder();
        double positionY = 0;
        for (InkSprite inkSprite : inkSpriteList) {
            positionX -= (inkSprite.getWidth() + spaceBetweenInks);
            inkSprite.setPosition(positionX, positionY);
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

    @Override
    public void render() {
        super.render();
        renderInks();
        renderPrinterSalvers();
        renderAvailableWhitePapers();
    }

    private void renderInks() {
        for (InkSprite colorInk : inkSpriteList)
            colorInk.render();
    }

    private void renderPrinterSalvers() {
        printerUpSalver.render();
        printerDownSalver.render();
    }

    private void renderAvailableWhitePapers() {
        ListIterator<WhitePaperSprite> iterator = availablePapersList.listIterator(availablePapersList.size());
        while (iterator.hasPrevious())
            iterator.previous().render();
    }

//    private void renderPrintedPages(){
//        for (PrinterPaper printedImage)
//    }

    @Override
    public void update() {
        super.update();
        updateColorInks();
        updatePrinterSalvers();
        updateAddedNewPapers();
        updateAvailablePapers();

        System.out.println("Papers:" + availablePapersList.size());
    }

    private void updateColorInks() {
        for (InkSprite colorInk : inkSpriteList) {
            colorInk.update();
        }
    }

    private void updatePrinterSalvers() {
        printerUpSalver.update();
        printerDownSalver.update();
    }

    private void updateAddedNewPapers() {
        if (amountOfAddedNewPapers() > 0)
            addNewWhitePapers();
    }

    private void addNewWhitePapers() {
        for (int i = 0; i < amountOfAddedNewPapers(); i++) {
            WhitePaperSprite newPaper = new WhitePaperSprite();
            setPositionOfWhitePaper(newPaper);
            availablePapersList.add(newPaper);
            printer.refillAvailablePaper(1);
        }
        printerUpSalver.clearAmountOfNewPapers();
    }

    private int amountOfAddedNewPapers() {
        return printerUpSalver.getAmountOfAddedNewPapers();
    }

    private void updateAvailablePapers() {
        Iterator<WhitePaperSprite> iterator = availablePapersList.iterator();
        while (iterator.hasNext()) {
            WhitePaperSprite paper = iterator.next();
            paper.update();
            if (paper.canBeRemoved()) {
                printer.setInPrintingTime(false);
                iterator.remove();
            }
        }
    }

    public void print(Image image, boolean multicolor, int amountOfCopy) {
        if (!printer.isInPrintingTime())
            if (printer.printImage(image, multicolor, amountOfCopy)) {
                availablePapersList.get(0).usePaperInPrinter();
                printer.setInPrintingTime(true);
            }
    }

    @Override
    public void setPosition(double positionX, double positionY) {
        super.setPosition(positionX, positionY);
        printerUpSalver.setPosition(positionX, positionY);
        printerDownSalver.setPosition(positionX, positionY + height);
        setPositionAvailablePapers();
    }

    private void setPositionAvailablePapers() {
        for (WhitePaperSprite paper : availablePapersList)
            setPositionOfWhitePaper(paper);
    }

    private void setPositionOfWhitePaper(WhitePaperSprite paper) {
        paper.setPosition(printerUpSalver.getPositionX(), printerUpSalver.getPositionY(), printerUpSalver.getWidth(), printerUpSalver.getHeight());
    }

    private void addEventHandler() {
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            print(ImagesProperties.blackInkSprite(), true, 1); //todo: tmp
        });
    }
}
