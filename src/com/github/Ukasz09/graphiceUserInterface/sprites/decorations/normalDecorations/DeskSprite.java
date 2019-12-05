package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class DeskSprite extends ImageSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static Image DEFAULT_SPRITE_IMAGE = ImagesProperties.deskSprite();
    private final static double DEFAULT_WIDTH = 1092;
    private final static double DEFAULT_HEIGHT = 230;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DeskSprite(double positionX, double positionY) {
        super(DEFAULT_SPRITE_IMAGE,DEFAULT_WIDTH,DEFAULT_HEIGHT,positionX,positionY);
    }
}
