package com.github.Ukasz09.graphiceUserInterface.sprites.printer.inks;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import javafx.scene.paint.Color;

public class CapacityStatusBar extends Sprite {
    private static final double BAR_WIDTH_TO_FRAME_PROPORTION = 0.016;//0.0156//25.0 / 1600;

    private final double maxBarHeight;

    private Color actualColor;
    private double actualCapacityPercents;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CapacityStatusBar(double maxBarHeight, double positionX, double positionY) {
        super(BAR_WIDTH_TO_FRAME_PROPORTION * ViewManager.getInstance().getRightFrameBorder(), maxBarHeight,positionX, positionY);
        this.maxBarHeight = maxBarHeight;
        actualColor = Color.GREEN;
        actualCapacityPercents = 100;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void update() {
        updateColorOfBar();
        updateBarHeight();
    }

    //todo: poprawic
    private void updateColorOfBar() {
        if (height > 0) {
            double r = 187;
            double g = 6;
            double b = 6;

            height = maxBarHeight * actualCapacityPercents / 100;
            if (actualCapacityPercents > 50) {
                r = 6 + ((100 - actualCapacityPercents) * 3.5);
                if (r > 186)
                    r = 186;
                g = 187;
            } else {
                r = 186;
                g = -20 + actualCapacityPercents * 2.8;
                if (g < 6)
                    g = 6;
            }
            b = 6;
            actualColor = Color.rgb((int) r, (int) g, (int) b, 0.9);
        } else actualColor = Color.RED;
    }

    private void updateBarHeight() {
        if (height <= 0)
            height = maxBarHeight;
    }

    @Override
    public void render() {
        renderStatusBar();
    }

    private void renderStatusBar() {
        changeColorToActual();
        drawFillRect();
    }

    private void changeColorToActual() {
        manager.getGraphicContext().setFill(actualColor);
    }

    private void drawFillRect() {
        manager.getGraphicContext().fillRect(positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setActualCapacityPercents(double actualCapacityPercent) {
        this.actualCapacityPercents = actualCapacityPercent;
    }
}
