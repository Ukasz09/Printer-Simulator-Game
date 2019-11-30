package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import javafx.scene.paint.Color;

public class CapacityStatusBar {
    private static final double DEFAULT_BAR_WIDTH = 25;

    private final ViewManager manager;
    private final double maxBarHeight;
    private final double barWidth;
    private double positionX;
    private double positionY;

    private double actualBarHeight;
    private Color actualColor;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CapacityStatusBar(double barHeight) {
        manager = ViewManager.getInstance();
        maxBarHeight = barHeight;
        actualBarHeight = maxBarHeight;
        this.barWidth = DEFAULT_BAR_WIDTH;
        actualColor = Color.RED;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    //todo: poprawic
    public void update(double percents) {
        double r = 187;
        double g = 6;
        double b = 6;

        actualBarHeight = maxBarHeight * percents / 100;

        if (percents > 50) {
            r = 6 + ((100 - percents) * 3.5);
            if (r > 186)
                r = 186;
            g = 187;
        } else {
            r = 186;
            g = -20 + percents * 2.8;
            if (g < 6)
                g = 6;
        }
        b = 6;
        actualColor = Color.rgb((int) r, (int) g, (int) b, 0.9);
    }

    public void render() {
        renderStatusBar();
    }

    private void renderStatusBar() {
        if (actualBarHeight > 0) {
            manager.getGraphicContext().setFill(actualColor);
            manager.getGraphicContext().fillRect(positionX, positionY, barWidth, actualBarHeight);
        } else {
            manager.getGraphicContext().setFill(Color.RED);
            manager.getGraphicContext().fillRect(positionX, positionY, barWidth, maxBarHeight);
        }
    }

    public double getBarWidth() {
        return barWidth;
    }
}
