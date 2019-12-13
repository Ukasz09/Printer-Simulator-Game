package com.github.Ukasz09.graphiceUserInterface.sounds;

import com.github.Ukasz09.ImageResource;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SoundsPlayer {
    private String soundPath;
    private MediaPlayer mediaPlayer;
    private double volume;
    private boolean inLoop;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SoundsPlayer(String soundPath, double volume, boolean inLoop) {
        this.soundPath = soundPath;
        this.inLoop = inLoop;
        this.volume = volume;
        makeSound();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void makeSound() {
//        String resource = new File(soundPath).toURI().toString();

        java.net.URL soundURL = ImageResource.class.getResource(soundPath);
        Media media = new Media(soundURL.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);

        if (inLoop)
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void playSound() {
        mediaPlayer.play();
    }

    public void stopSound() {
        mediaPlayer.stop();
    }
}
