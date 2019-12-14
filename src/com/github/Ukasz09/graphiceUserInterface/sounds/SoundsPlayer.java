package com.github.Ukasz09.graphiceUserInterface.sounds;

import com.github.Ukasz09.SoundResource;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class SoundsPlayer {
    private String soundPath;
    private MediaPlayer mediaPlayer;
    private double volume;
    private boolean inLoop;
    private boolean isPlayingNow;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer(String soundPath, double volume, boolean inLoop) {
        this.soundPath = soundPath;
        this.inLoop = inLoop;
        this.volume = volume;
        isPlayingNow = false;
        makeSound();
        addPlayingChecking();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void makeSound() {
        java.net.URL soundURL = SoundResource.class.getResource(soundPath);
        Media media = new Media(soundURL.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);

        if (inLoop)
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void playSound() {
        mediaPlayer.play();
        isPlayingNow = true;
    }

    public void stopSound() {
        mediaPlayer.stop();
    }

    private void addPlayingChecking() {
        mediaPlayer.setOnEndOfMedia(() -> isPlayingNow = false);
    }

    public boolean isPlayingNow() {
        return isPlayingNow;
    }
}
