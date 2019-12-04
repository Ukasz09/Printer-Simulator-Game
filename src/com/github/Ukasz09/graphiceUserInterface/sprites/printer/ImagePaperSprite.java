package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import javafx.scene.image.Image;

public class ImagePaperSprite extends PaperSprite {

    private Image imageToPrint;
    private double positionYWhenStopAnimation;

    public ImagePaperSprite(Image imageToPrint, double positionYWhenStopAnimation) {
        super();
//        height+=height/4;  //todo: ustawianie pozycji z uwzglednieniem przesuniecia na wysokosc
        this.imageToPrint = imageToPrint;
        this.positionYWhenStopAnimation = positionYWhenStopAnimation;
    }

    @Override
    public void render() {
        //todo: tmp wylaczony render
        drawStrokeRect();
        drawFillRect();
        renderImage();
    }

    private void renderImage() {
        manager.getGraphicContext().drawImage(imageToPrint, positionX, positionY, width, height);
    }

    @Override
    protected void nextFrameOfAnimation() {
        positionY += speedOfPrinterConsuming;
    }

    @Override
    protected boolean needToDoPrintingAnimation() {
        return (isInPrintingTime && positionY <= positionYWhenStopAnimation);
    }

    @Override
    public void actionWhenFinishedPrinting() {
        isInPrintingTime = false;
    }

    @Override
    protected boolean finishedPrinting() {
        return positionY+height >= positionYWhenStopAnimation;
    }
}
