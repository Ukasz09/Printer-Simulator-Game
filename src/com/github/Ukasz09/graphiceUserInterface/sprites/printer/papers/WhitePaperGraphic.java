package com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers;

public class WhitePaperGraphic extends PaperGraphic {

    public WhitePaperGraphic(double positionX, double positionY, double animationSpeed) {
        super(positionX, positionY, animationSpeed);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render() {
        drawStrokeRect();
        drawFillRect();
    }

    @Override
    protected void nextFrameOfAnimation() {
        height -= animationSpeed;
        positionY += animationSpeed;
    }

    @Override
    protected boolean needToStopAnimation() {
        return height <= 0;
    }

    @Override
    protected void actionWhenAnimationStopped() {
        canBeDestroyedNow = true;
    }
}
