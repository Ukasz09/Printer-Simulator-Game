package com.github.Ukasz09.graphiceUserInterface.sprites.decorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperites;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;
import javafx.event.Event;

public class GlobeSprite extends AnimatedSprite {
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
    }
}