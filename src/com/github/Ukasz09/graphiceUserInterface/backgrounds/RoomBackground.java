package com.github.Ukasz09.graphiceUserInterface.backgrounds;

import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsPlayer;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class RoomBackground extends Background {
    private static final double DEFAULT_VOLUME = 0.5;
    private static final Image DEFAULT_IMAGE = ImagesProperties.roomBackground();
    private static final double FLOOR_HEIGHT_TO_FRAME_PROPERTY = 6.0 / 90;
    private static final Color DEFAULT_FLOOR_COLOR = Color.rgb(52, 52, 52);
    private static final SoundsPlayer DEFAULT_MUSIC = SoundsProperties.room1(DEFAULT_VOLUME);

    public RoomBackground() {
        super(getHeightAfterScaling(FLOOR_HEIGHT_TO_FRAME_PROPERTY), DEFAULT_IMAGE, DEFAULT_FLOOR_COLOR, DEFAULT_MUSIC);
    }
}
