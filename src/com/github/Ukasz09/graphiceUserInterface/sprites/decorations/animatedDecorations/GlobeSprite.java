package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;

public class GlobeSprite extends AnimatedSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.globeSheetProperty();
    private final static double WIDTH_TO_FRAME_PROPORTION = 192.0 / 1600;
    public final static double HEIGHT_TO_FRAME_PROPORTION = 192.0 / 900;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GlobeSprite(double positionX, double positionY) {
        super(WIDTH_TO_FRAME_PROPORTION* ViewManager.getInstance().getRightFrameBorder(),HEIGHT_TO_FRAME_PROPORTION*ViewManager.getInstance().getBottomFrameBorder(),positionX, positionY, DEFAULT_SHEET_PROPERTY, DEFAULT_SHEET_PROPERTY.getAction(DecorationState.STANDBY));
    }
}
