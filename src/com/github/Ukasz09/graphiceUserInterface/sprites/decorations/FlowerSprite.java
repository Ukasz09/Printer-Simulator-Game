package com.github.Ukasz09.graphiceUserInterface.sprites.decorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.event.Event;
import javafx.scene.image.Image;

public class FlowerSprite extends Sprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static Image DEFAULT_IMAGE_PROPERTY = ImagesProperties.flowerSprite();
    private final static double DEFAULT_WIDTH = 292;
    private final static double DEFAULT_HEIGHT = 380;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FlowerSprite() {
        super(DEFAULT_IMAGE_PROPERTY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
