package com.github.Ukasz09.graphiceUserInterface.sounds;

public class SoundsProperties {
//
//    public static SoundsPlayer room1(double volume) {
//        String musicPath = "resources/music/room1.mp3";
//        return new SoundsPlayer(musicPath, volume, true);
//    }


    public static SoundsPlayer room1(double volume) {
        String musicPath = "/resources/music/room1.mp3";
        return new SoundsPlayer(musicPath, volume, true);
    }

    public static SoundsPlayer catMeow(double volume) {
        String musicPath = "/resources/music/catMeow.mp3";
        return new SoundsPlayer(musicPath, volume, false);
    }

    public static SoundsPlayer printing(double volume) {
        String musicPath = "/resources/music/printerSound.wav";
        return new SoundsPlayer(musicPath, volume, true);
    }
}
