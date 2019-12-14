package com.github.Ukasz09.graphiceUserInterface.backgrounds;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sounds.SoundsPlayer;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Background {
    private Image backgroundImage;
    private SoundsPlayer backgroundSound;
    private boolean backgroundSoundIsPlaying;
    protected ViewManager manager;
    private double floorHeight;
    private Color floorColor;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Background(double floorHeight, Image backgroundImage, Color floorColor, SoundsPlayer backgroundSound) {
        this.backgroundImage = backgroundImage;
        this.backgroundSound = backgroundSound;
        this.floorColor = floorColor;
        manager = ViewManager.getInstance();
        backgroundSoundIsPlaying = false;
        this.floorHeight = floorHeight;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void render() {
        drawBackground();
        drawFloor();
    }

    private void drawBackground() {
        manager.getGraphicContext().drawImage(backgroundImage, 0, 0, manager.getRightFrameBorder(), manager.getBottomFrameBorder());
    }

    private void drawFloor() {
        manager.setFillColor(floorColor);
        manager.getGraphicContext().fillRect(0, manager.getBottomFrameBorder() - floorHeight, manager.getRightFrameBorder(), floorHeight);
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

    public double getFloorHeight() {
        return floorHeight;
    }

    protected static double getWidthAfterScaling(double widthBeforeScaling) {
        return widthBeforeScaling * ViewManager.getInstance().getRightFrameBorder();
    }

    protected static double getHeightAfterScaling(double heightBeforeScaling) {
        return heightBeforeScaling * ViewManager.getInstance().getBottomFrameBorder();
    }
}
