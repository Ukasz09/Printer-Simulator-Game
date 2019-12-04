package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
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
    private final static Image DEFAULT_PRINTER_IMAGE = ImagesProperties.printerUpperBodySprite();
    private final static double DEFAULT_WIDTH = 280;
    private final static double DEFAULT_HEIGHT = 90;
    private final static double DEFAULT_SPACE_BETWEEN_INKS = 20;

    private Printer printer;

    private ArrayList<InkSprite> inkSpriteList;
    private ArrayList<WhitePaperSprite> availablePapersList;
    private ArrayList<ImagePaperSprite> printedImageList;

    private PrinterSalver printerUpSalver;
    private PrinterSalver printerDownSalver;
    private PrinterLowerBody printerLowerBody;

    private Image posterImage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterSprite() {
        super(DEFAULT_PRINTER_IMAGE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        printer = new Printer();
        printerUpSalver = new PrinterSalver(width, height);
        printerDownSalver = new PrinterSalver(width, height / 2);
        printerLowerBody = new PrinterLowerBody();
        inkSpriteList = new ArrayList<>();
        addInkSprites();
        setInkBoxesPosition(DEFAULT_SPACE_BETWEEN_INKS);
        availablePapersList = new ArrayList<>();
        printedImageList = new ArrayList<>();
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
//        super.render();
        renderInks();
        renderPrinterSalvers();
        renderLowerBody();
        renderSprite();//todo: tmp?? (=upperBody) zmienic
        renderPrintedPages();
        renderAvailableWhitePapers();
    }

//    private void renderBody(){
//        manager.getGraphicContext().drawImage(,);
//    }

    private void renderInks() {
        for (InkSprite colorInk : inkSpriteList)
            colorInk.render();
    }

    private void renderPrinterSalvers() {
        printerUpSalver.render();
        printerDownSalver.render();
    }

    private void renderLowerBody(){
        printerLowerBody.render();
    }

    private void renderAvailableWhitePapers() {
        ListIterator<WhitePaperSprite> iterator = availablePapersList.listIterator(availablePapersList.size());
        while (iterator.hasPrevious())
            iterator.previous().render();
    }

    private void renderPrintedPages() {
        for (ImagePaperSprite printerPaper : printedImageList)
            printerPaper.render();
    }

    @Override
    public void update() {
        super.update();
        updateColorInks();
        updatePrinterSalvers();
        updateAddedWhitePapers();
        updateAvailableWhitePapers();
        updateAddedPrintedPapers();
        updatePrintedPapers();

        System.out.println("Papers:" + availablePapersList.size());
        System.out.println("Printed: " + printedImageList.size());
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

    private void updateAddedWhitePapers() {
        if (amountOfAddedNewPapers() > 0)
            addNewWhitePapers();
    }

    private void addNewWhitePapers() {
        for (int i = 0; i < amountOfAddedNewPapers(); i++) {
            WhitePaperSprite newPaper = new WhitePaperSprite();
            setPositionOfPaper(newPaper);
            availablePapersList.add(newPaper);
            printer.refillAvailablePaper(1);
        }
        printerUpSalver.clearAmountOfNewPapers();
    }

    private int amountOfAddedNewPapers() {
        return printerUpSalver.getAmountOfAddedNewPapers();
    }

    private void updateAvailableWhitePapers() {
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

    private void updateAddedPrintedPapers() {
        while (printer.getNotTakenPrintedPages().size() > printedImageList.size()) {
            double positionYWhenStopAnimation = printerDownSalver.getPositionY() + printerDownSalver.getHeight(); //todo: tmp
            ImagePaperSprite printedSprite = new ImagePaperSprite(printer.getNotTakenPrintedPages().getFirst().getPrintedImage(), positionYWhenStopAnimation);
            setPositionOfPaper(printedSprite);
            printedSprite.usePaperInPrinter();
            printedImageList.add(printedSprite); //todo: tmp bo nie bedzie dzialac dal wielu kopi na raz
        }
    }


    private void updatePrintedPapers() {
        Iterator<ImagePaperSprite> iterator = printedImageList.iterator();
        while (iterator.hasNext()) {
            ImagePaperSprite paper = iterator.next();
            paper.update();
            if (paper.canBeRemoved()) {
//                printer.setInPrintingTime(false);
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
//
//    private void addPrintedImage(Image image) {
//        printedImageList.add(new ImagePaperSprite(image));
//    }

    @Override
    public void setPosition(double positionX, double positionY) {
        super.setPosition(positionX, positionY - printerLowerBody.getHeight());
        printerLowerBody.setPosition(positionX, positionY);
        printerUpSalver.setPosition(positionX, positionY-printerLowerBody.getHeight());
        printerDownSalver.setPosition(positionX, positionY + height);
        setPositionAvailablePapers();
    }

    private void setPositionAvailablePapers() {
        for (WhitePaperSprite paper : availablePapersList)
            setPositionOfPaper(paper);
    }

    //todo: zrobic inaczej
    private void setPositionOfPaper(PaperSprite paper) {
        paper.setPosition(printerUpSalver.getPositionX(), printerUpSalver.getPositionY(), printerUpSalver.getWidth(), printerUpSalver.getHeight());
    }

    public void updateActualPosterImage(Image posterImage) {
        this.posterImage = posterImage;
    }

    private void addEventHandler() {
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (posterImage != null)
                print(posterImage, true, 1); //todo: tmp
            else System.err.println("ERROR: poster=null");
        });
    }
}
