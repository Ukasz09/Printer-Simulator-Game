package com.github.Ukasz09.graphiceUserInterface.sprites.decorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class DeskSprite extends Sprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static Image DEFAULT_SPRITE = ImagesProperties.deskSprite();
    private final static double DEFAULT_WIDTH = 1092;
    private final static double DEFAULT_HEIGHT = 230;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DeskSprite() {
        super(DEFAULT_SPRITE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
