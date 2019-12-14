package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;
import javafx.scene.image.Image;

public class XmasTreeSprite extends AnimatedSprite {
    private final static ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.xmasTreeProperty();
    private final static double WIDTH_TO_FRAME_PROPORTION = 262.0 / 1600;
    public final static double HEIGHT_TO_FRAME_PROPORTION = 38.0 / 90;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmasTreeSprite(double positionX, double positionY) {
        super(getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION), getHeightAfterScaling(HEIGHT_TO_FRAME_PROPORTION), positionX, positionY,
                DEFAULT_SHEET_PROPERTY, DEFAULT_SHEET_PROPERTY.getAction(DecorationState.STANDBY));

    }
}
