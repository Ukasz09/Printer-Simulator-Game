package com.github.Ukasz09.graphiceUserInterface.backgrounds;

import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsPlayer;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsProperties;
import javafx.scene.image.Image;

public class RoomBackground extends Background {
    private static final double DEFAULT_VOLUME = 0.5;
    private static final Image DEFAULT_IMAGE = BackgroundProperties.room1();
    private static final SoundsPlayer DEFAULT_MUSIC = SoundsProperties.room1(DEFAULT_VOLUME);

    public RoomBackground() {
        super(DEFAULT_IMAGE, DEFAULT_MUSIC);
    }

}
