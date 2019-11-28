package com.github.Ukasz09.graphiceUserInterface.sounds;

public class SoundsProperties {

    public static SoundsPlayer room1(double volume) {
        String musicPath = "src/resources/music/room1.mp3";
        return new SoundsPlayer(musicPath, volume, true);
    }

}
