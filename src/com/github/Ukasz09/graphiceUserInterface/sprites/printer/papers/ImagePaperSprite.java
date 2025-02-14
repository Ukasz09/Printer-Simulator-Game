package com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers;

import javafx.scene.image.Image;

public class ImagePaperSprite extends PaperSprite {
    private Image imageToPrint;
    private double positionYWhenStopAnimation;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ImagePaperSprite(double positionX, double positionY, double animationSpeed, Image imageToPrint, double stopAnimationAtYPosition) {
        super(positionX, positionY, animationSpeed);
        height += height / 4;
        this.imageToPrint = imageToPrint;
        this.positionYWhenStopAnimation = stopAnimationAtYPosition;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render() {
        drawStrokeRect();
        drawFillRect();
        drawImage();
    }

    private void drawImage() {
        manager.getGraphicContext().drawImage(imageToPrint, positionX, positionY, width, height);
    }

    @Override
    protected void nextFrameOfAnimation() {
        positionY += animationSpeed;
    }

    @Override
    protected boolean needToStopAnimation() {
        return positionY >= positionYWhenStopAnimation;
    }
}
