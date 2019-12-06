package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;

public class GlobeSprite extends AnimatedSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.globeSheetProperty();
    public final static double DEFAULT_WIDTH = 192;
    public final static double DEFAULT_HEIGHT = 192;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GlobeSprite(double positionX, double positionY) {
        super(DEFAULT_WIDTH, DEFAULT_HEIGHT, positionX, positionY, DEFAULT_SHEET_PROPERTY, DEFAULT_SHEET_PROPERTY.getAction(DecorationState.STANDBY));
    }
}
