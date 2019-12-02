package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import javafx.scene.paint.Color;

public abstract class PaperSprite {
    private static final double DEFAULT_WIDTH = 100;
    private static final double DEFAULT_HEIGHT = 100;
    protected static final double DEFAULT_STROKE_PAPER_WIDTH = 3;
    private static final double DEFAULT_PRINTING_TIMER = 50;
    private static final double DEFAULT_TIMER_REDUCER = 10;
    private static final Color DEFAULT_STROKE_COLOR = new Color(0, 0, 0, 1);

    protected ViewManager manager;
    protected double width;
    protected double height;
    protected double positionX;
    protected double positionY;
    private double printerTimer;
    private boolean isInPrintingTime;
    private boolean canBeRemoved;

    public PaperSprite() {
        manager = ViewManager.getInstance();
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        printerTimer = DEFAULT_PRINTING_TIMER;
        isInPrintingTime = false;
        canBeRemoved = false;
    }


    public abstract void render();

    public void update() {
        if (needToDoPaperAbsorptionAnimation()) {
            if (canDoNextFrameAnimation()) {
                nextFrameOfAnimation();
                restoreTimerDuration();
            } else reduceTimerDuration();
        } else {
            restoreTimerDuration();
            if (finishedPrinting())
                canBeRemoved = true;
        }
    }

    private boolean finishedPrinting() {
        return height <= 0;
    }

    private boolean needToDoPaperAbsorptionAnimation() {
        return (height > 0 && isInPrintingTime);
    }

    private boolean canDoNextFrameAnimation() {
        return printerTimer <= 0;
    }

    private void reduceTimerDuration() {
        printerTimer -= DEFAULT_TIMER_REDUCER;
    }

    protected abstract void nextFrameOfAnimation();

    private void restoreTimerDuration() {
        printerTimer = DEFAULT_PRINTING_TIMER;
    }

    protected void setLineWidth(double lineWidth) {
        if (lineWidth < 1)
            manager.getGraphicContext().setLineWidth(1);
        else manager.getGraphicContext().setLineWidth(lineWidth);
    }

    protected void drawStrokeRect() {
        setColor(DEFAULT_STROKE_COLOR);
        setLineWidth(DEFAULT_STROKE_PAPER_WIDTH);
        manager.getGraphicContext().strokeRect(positionX, positionY, width, height);
    }

    protected void setColor(Color color) {
        manager.setFillColor(color);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void setPosition(double salverPositionX, double salverPositionY, double salverWidth, double salverHeight) {
        this.positionX = salverPositionX + salverWidth / 2 - width / 2;
        this.positionY = salverPositionY + salverHeight - height;
    }

    public void usePaperInPrinter() {
        isInPrintingTime = true;
    }

    public boolean canBeRemoved() {
        return canBeRemoved;
    }
}
