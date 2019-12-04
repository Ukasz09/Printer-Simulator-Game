package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class PrinterSalver extends Sprite {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static Image DEFAULT_SPRITE = ImagesProperties.printerSalverSprite();
    private final static double WIDTH_MULTIPLIER = 0.6;
    private final static double HEIGHT_MULTIPLIER = 0.40;

    private double printerSpriteWidth;
    private int amountOfAddedNewPapers;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterSalver(double printerSpriteWidth, double printerHeight) {
        super(DEFAULT_SPRITE, printerSpriteWidth * WIDTH_MULTIPLIER, printerHeight * HEIGHT_MULTIPLIER);
        this.printerSpriteWidth = printerSpriteWidth;
        amountOfAddedNewPapers = 0;
        makeAndAddImageViewToRoot();
        addEventHandler();
    }

    public void setPosition(double printerPositionX, double printerPositionY) {
        double positionX = printerPositionX + printerSpriteWidth / 2 - width / 2;
        double positionY = printerPositionY;
        super.setPosition(positionX, positionY);
    }

    //todo: nie moze byc tak bo wowczas nacisniecie na dolnego salver dodaje kartki tez !!
    private void addEventHandler() {
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            amountOfAddedNewPapers++;
        });
    }

    public int getAmountOfAddedNewPapers() {
        return amountOfAddedNewPapers;
    }

    public void clearAmountOfNewPapers(){
        amountOfAddedNewPapers=0;
    }
}
