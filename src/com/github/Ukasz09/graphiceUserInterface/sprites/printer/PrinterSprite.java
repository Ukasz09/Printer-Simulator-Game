package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.event.Event;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class PrinterSprite extends Sprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static Image DEFAULT_PRINTER_IMAGE = ImagesProperties.printerFullSprite();
    private final static double DEFAULT_WIDTH = 280;
    private final static double DEFAULT_HEIGHT = 212;
    private final static double DEFAULT_SPACE_BETWEEN_INKS = 20;

    private Printer printer;
    private ArrayList<InkSprite> inkSpriteList;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterSprite() {
        super(DEFAULT_PRINTER_IMAGE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        printer = new Printer();
        inkSpriteList = new ArrayList<>();
        addInkSprites();
        setInkBoxesPosition(DEFAULT_SPACE_BETWEEN_INKS);
        addEventHandlersToInks();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addInkSprites() {
        printer.getPrinterIncs().forEach((k, v) -> {
            inkSpriteList.add(new InkSprite(getInkImageByColor(k), v));
        });
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

    //todo tmp
    private void addEventHandlersToInks() {
        for (InkSprite inkSprite : inkSpriteList)
            inkSprite.addEventHandler();
    }

    @Override
    public void render() {
        super.render();
        renderInks();
    }

    private void renderInks() {
        for (InkSprite colorInk : inkSpriteList)
            colorInk.render();
    }

    @Override
    public void update() {
        super.update();
        updateColorInks();

//        //todo: tmp
//        printer.printImage(ImagesProperties.deskSprite(), true, 1);
    }

    private void updateColorInks() {
        for (InkSprite colorInk : inkSpriteList) {
            colorInk.update();
        }
    }
}
