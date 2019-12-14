package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class DeskSprite extends ImageSprite {
    private final static Image DEFAULT_SPRITE_IMAGE = ImagesProperties.deskSprite();
    public final static double WIDTH_TO_FRAME_PROPORTION = 0.682;
    public final static double HEIGHT_TO_FRAME_PROPORTION = 0.255;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DeskSprite(double positionX, double positionY) {
        super(getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION), getHeightAfterScaling(HEIGHT_TO_FRAME_PROPORTION), DEFAULT_SPRITE_IMAGE, positionX, positionY);
    }
}
