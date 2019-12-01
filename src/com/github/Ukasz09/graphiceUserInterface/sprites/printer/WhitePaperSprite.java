package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import javafx.scene.paint.Color;

public class WhitePaperSprite {
    private static final double DEFAULT_WIDTH = 100;
    private static final double DEFAULT_HEIGHT = 100;
    private static final double DEFAULT_STROKE_PAPER_WIDTH = 3;
    private static final double DEFAULT_PRINTING_TIMER = 50;
    private static final double DEFAULT_TIMER_REDUCER = 10;

    private ViewManager manager;
    private double width;
    private double height;
    private double positionX;
    private double positionY;
    private double printerTimer;
    private boolean isInPrintingTime;
    private boolean canBeRemoved;

    public WhitePaperSprite() {
        manager = ViewManager.getInstance();
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        printerTimer = DEFAULT_PRINTING_TIMER;
        isInPrintingTime = false;
        canBeRemoved = false;
    }

    public void render() {
        drawPaper();
    }

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

    private void nextFrameOfAnimation() {
        height -= 1;
        positionY += 1;
    }

    private void restoreTimerDuration() {
        printerTimer = DEFAULT_PRINTING_TIMER;
    }

    private void drawPaper() {
        setColor(new Color(0, 0, 0, 1));
        setLineWidth(DEFAULT_STROKE_PAPER_WIDTH);
        manager.getGraphicContext().strokeRect(positionX, positionY, width, height);
        setColor(new Color(1, 1, 1, 1));
        setLineWidth(1);
        manager.getGraphicContext().fillRect(positionX, positionY, width, height);
    }

    private void setColor(Color color) {
        manager.setFillColor(color);
    }

    private void setLineWidth(double lineWidth) {
        if (lineWidth < 1)
            manager.getGraphicContext().setLineWidth(1);
        else manager.getGraphicContext().setLineWidth(lineWidth);
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
