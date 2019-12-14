package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;

public class XmasLamp extends AnimatedSprite {
        private final static ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.xmasLampProperty();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public XmasLamp(double positionX, double positionY, double width, double height) {
            super(width, height, positionX, positionY, DEFAULT_SHEET_PROPERTY, DEFAULT_SHEET_PROPERTY.getAction(DecorationState.STANDBY));

        }
    }