package com.github.Ukasz09.graphiceUserInterface.backgrounds;

import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsPlayer;
import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import javafx.scene.image.Image;

public abstract class Background {
    private Image backgroundImage;
    private SoundsPlayer backgroundSound;
    private boolean backgroundSoundIsPlaying;
    private ViewManager manager;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Background(Image backgroundImage, SoundsPlayer backgroundSound) {
        this.backgroundImage = backgroundImage;
        this.backgroundSound = backgroundSound;
        manager = ViewManager.getInstance();
        backgroundSoundIsPlaying = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void render() {
        drawBackground();
    }

    public void playBackgroundSound() {
        backgroundSound.playSound();
        backgroundSoundIsPlaying = true;
    }

    public void stopBackgroundSound() {
        if (backgroundSoundIsPlaying) {
            backgroundSound.stopSound();
            backgroundSoundIsPlaying = false;
        }
    }

    private void drawBackground() {
        manager.getGraphicContext().drawImage(backgroundImage, 0, 0);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
