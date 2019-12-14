package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations;

import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsPlayer;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;
import javafx.scene.input.MouseEvent;

public class CatSprite extends AnimatedSprite {
    private final static ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.catSheetProperty();
    private final static double DEFAULT_CAT_SOUND_VOLUME = 0.2;
    private final static SoundsPlayer DEFAULT_CAT_SOUND = SoundsProperties.catMeow(DEFAULT_CAT_SOUND_VOLUME);
    public final static double WIDTH_TO_FRAME_PROPORTION = 219.0 / 1600;
    public final static double HEIGHT_TO_FRAME_PROPORTION = 3.0 / 9;

    private SoundsPlayer catMeowSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CatSprite(double positionX, double positionY) {
        super(getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION), getHeightAfterScaling(HEIGHT_TO_FRAME_PROPORTION), positionX,
                positionY, DEFAULT_SHEET_PROPERTY, DEFAULT_SHEET_PROPERTY.getAction(DecorationState.STANDBY));
        catMeowSound = DEFAULT_CAT_SOUND;
        addCatEventHandler();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addCatEventHandler() {
        addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!catMeowSound.isPlayingNow()) {
                catMeowSound = SoundsProperties.catMeow(DEFAULT_CAT_SOUND_VOLUME);
                catMeowSound.playSound();
            }
        });
    }
}
