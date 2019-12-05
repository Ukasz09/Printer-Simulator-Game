package com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers;

public class WhitePaperSprite extends PaperSprite {

    public WhitePaperSprite(double positionX, double positionY, double animationSpeed) {
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
