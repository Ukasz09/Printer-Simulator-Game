package com.github.Ukasz09.graphiceUserInterface.sprites.decorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.PartDrawing;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpritesProperites;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;

public class GlobeSprite extends Sprite implements PartDrawing {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static ImageSheetProperty DEFAULT_IMAGE_PROPERTY = SpritesProperites.globeSheetProperty();
    private final static double DEFAULT_WIDTH = 192;
    private final static double DEFAULT_HEIGHT = 192;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GlobeSprite() {
        super(DEFAULT_IMAGE_PROPERTY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_IMAGE_PROPERTY.getAction(DecorationState.STANDBY));
        setPosition(getDefaultPositionX(),getDefaultPositionY());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                   Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TODO: tmp: potem dac osobno przedmioty do background i ustalac pozycje na podstawie przedmiotow
    private double getDefaultPositionX() {
        return (getManager().getLeftFrameBorder() +getManager().getRightFrameBorder()/6.8);
    }

    private double getDefaultPositionY() {
        return (getManager().getBottomFrameBorder() - getManager().getBottomFrameBorder()/1.88);
    }
}
