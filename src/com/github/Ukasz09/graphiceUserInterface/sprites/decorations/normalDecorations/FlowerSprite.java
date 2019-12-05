package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class FlowerSprite extends ImageSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static Image DEFAULT_SPRITE_IMAGE = ImagesProperties.flowerSprite();
    private final static double DEFAULT_WIDTH = 292;
    private final static double DEFAULT_HEIGHT = 380;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FlowerSprite(double positionX, double positionY) {
        super(DEFAULT_SPRITE_IMAGE, DEFAULT_WIDTH, DEFAULT_HEIGHT, positionX, positionY);
    }
}
