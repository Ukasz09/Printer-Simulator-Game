package com.github.Ukasz09.graphiceUserInterface.sprites.decorations;

import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsPlayer;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperites;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;
import javafx.scene.input.MouseEvent;

public class CatSprite extends AnimatedSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static ImageSheetProperty DEFAULT_IMAGE_PROPERTY = SpritesProperites.catSheetProperty();
    private final static double DEFAULT_CAT_MEOW_VOLUME = 0.5;
    private final static SoundsPlayer DEFAULT_CAT_MEOW_SOUND = SoundsProperties.catMeow(DEFAULT_CAT_MEOW_VOLUME);
    private final static double DEFAULT_WIDTH = 219;
    private final static double DEFAULT_HEIGHT = 300;

    private SoundsPlayer catMeowSound;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CatSprite() {
        super(DEFAULT_IMAGE_PROPERTY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_IMAGE_PROPERTY.getAction(DecorationState.STANDBY));
        catMeowSound = DEFAULT_CAT_MEOW_SOUND;
        makeAndAddImageViewToRoot();
        addEventHandler();
    }

    private void addEventHandler() {
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            catMeowSound = SoundsProperties.catMeow(DEFAULT_CAT_MEOW_VOLUME);
            catMeowSound.playSound();
            System.out.println("s");
        });
    }
}
