package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import javafx.scene.paint.Color;

public class WhitePaperSprite extends PaperSprite {
    private static final Color PAPER_COLOR = new Color(1, 1, 1, 1);
    private static final double DEFAULT_SPEED_OF_CONSUMING = 1; //todo: tmp -> dac do drukarki

    private double speedOfPrinterConsuming;

    public WhitePaperSprite() {
        super();
        speedOfPrinterConsuming = DEFAULT_SPEED_OF_CONSUMING;
    }

    @Override
    public void render() {
        drawStrokeRect();
        drawFillRect();
    }

    @Override
    protected void nextFrameOfAnimation() {
        height -= speedOfPrinterConsuming;
        positionY += speedOfPrinterConsuming;
    }

    private void drawFillRect() {
        setColor(PAPER_COLOR);
        setLineWidth(DEFAULT_STROKE_PAPER_WIDTH); //todo: tmp
        manager.getGraphicContext().fillRect(positionX, positionY, width, height);
    }
}
