package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

public class WhitePaperSprite extends PaperSprite {


    public WhitePaperSprite() {
        super();
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

    @Override
    protected boolean finishedPrinting() {
        return height <= 0;
    }


    @Override
    protected boolean needToDoPrintingAnimation() {
        return (height > 0 && isInPrintingTime);
    }

    @Override
    public void actionWhenFinishedPrinting() {
        setCanBeRemoved(true);
    }
}
