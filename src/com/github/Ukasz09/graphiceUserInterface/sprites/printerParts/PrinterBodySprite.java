package com.github.Ukasz09.graphiceUserInterface.sprites.printerParts;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpritesProperites;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.PrinterState;

public class PrinterBodySprite extends Sprite implements PartDrawing {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static ImageSheetProperty DEFAULT_IMAGE_PROPERTY = SpritesProperites.printerSheetProperty();
    private final static double DEFAULT_WIDTH = 320;
    private final static double DEFAULT_HEIGHT = 320;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterBodySprite() {
        super(DEFAULT_IMAGE_PROPERTY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_IMAGE_PROPERTY.getAction(PrinterState.STANDBY));
        setPosition(getDefaultPositionX(),getDefaultPositionY());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                   Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TODO: tmp: potem dac osobno przedmioty do background i ustalac pozycje na podstawie przedmiotow
    private double getDefaultPositionX() {
        return (getManager().getRightFrameBorder() -getManager().getRightFrameBorder()/ 2.5);
    }

    private double getDefaultPositionY() {
        return (getManager().getBottomFrameBorder() - getManager().getBottomFrameBorder()/1.7);
    }

    @Override
    public void update() {
        //todo:
    }
}
