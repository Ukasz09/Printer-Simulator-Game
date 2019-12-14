package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class FlowerSprite extends ImageSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static Image DEFAULT_SPRITE_IMAGE = ImagesProperties.flowerSprite();
    private final static double WIDTH_TO_FRAME_PROPORTION = 0.182;//292.0 / 1600;
    public final static double HEIGHT_TO_FRAME_PROPORTION = 0.422;//38.0 / 90;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FlowerSprite(double positionX, double positionY) {
        super(WIDTH_TO_FRAME_PROPORTION * ViewManager.getInstance().getRightFrameBorder(), HEIGHT_TO_FRAME_PROPORTION * ViewManager.getInstance().getBottomFrameBorder(),DEFAULT_SPRITE_IMAGE, positionX, positionY);
    }
}
