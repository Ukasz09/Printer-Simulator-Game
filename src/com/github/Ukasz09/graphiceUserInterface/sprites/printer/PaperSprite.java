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
    private static final double DEFAULT_SPEED_OF_CONSUMING = 1; //todo: tmp -> dac do drukarki
    private static final Color PAPER_COLOR = new Color(1, 1, 1, 1);

    protected ViewManager manager;
    protected double width;
    protected double height;
    protected double positionX;
    protected double positionY;
    private double printerTimer;
    protected boolean isInPrintingTime;
    private boolean canBeRemoved;

    protected double speedOfPrinterConsuming; //todo: ustawic odpowiednie modyfikatory dostepu

    public PaperSprite() {
        manager = ViewManager.getInstance();
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        printerTimer = DEFAULT_PRINTING_TIMER;
        isInPrintingTime = false;
        canBeRemoved = false;
        speedOfPrinterConsuming = DEFAULT_SPEED_OF_CONSUMING;
    }


    public abstract void render();

    public void update() {
        if (needToDoPrintingAnimation()) {
            if (canDoNextFrameAnimation()) {
                nextFrameOfAnimation();
                restoreTimerDuration();
            } else reduceTimerDuration();
        }

        if (finishedPrinting())
            actionWhenFinishedPrinting();


//        else {
//            restoreTimerDuration();
////            if (finishedPrinting()){
//            actionWhenFinishedPrinting();
////            }
//        }
    }


    public abstract void actionWhenFinishedPrinting();

    protected abstract boolean finishedPrinting();

    protected abstract boolean needToDoPrintingAnimation() ;

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

    protected void drawFillRect() {
        setColor(PAPER_COLOR);
        setLineWidth(DEFAULT_STROKE_PAPER_WIDTH); //todo: tmp
        manager.getGraphicContext().fillRect(positionX, positionY, width, height);
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

    public void setCanBeRemoved(boolean canBeRemoved) {
        this.canBeRemoved = canBeRemoved;
    }
}
