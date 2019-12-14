package com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import javafx.scene.paint.Color;

public abstract class PaperSprite extends SpriteWithEventHandler implements IPaperGraphic {
    public static final double WIDTH_TO_FRAME_PROPORTION =0.062;//0.0625// 1.0 / 16;
    public static final double HEIGHT_TO_FRAME_PROPORTION = 0.111;//1.0 / 9;
    private static final double STROKE_WIDTH_TO_FRAME_PROPORTION = 0.002;//3.0/1600;
    private static final Color DEFAULT_STROKE_COLOR = Color.BLACK;
    private static final Color DEFAULT_PAPER_COLOR = Color.WHITE;
    private static final double DEFAULT_TIME_ON_ONE_FRAME_IN_ANIMATION = 5;

    private double strokeWidth;
    private Color strokeColor;
    private Color paperColor;

    protected boolean doingAnimationNow;
    private double timeOnFrameInAnimation;
    private double actualCooldownOnFrame;  //cooldown to make animation slower (stop on frame for qty of clock ticks)
    protected double animationSpeed;
    protected boolean canBeDestroyedNow;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PaperSprite(double positionX, double positionY, double animationSpeed) {
        super(WIDTH_TO_FRAME_PROPORTION * ViewManager.getInstance().getRightFrameBorder(), HEIGHT_TO_FRAME_PROPORTION * ViewManager.getInstance().getBottomFrameBorder(),positionX, positionY);
//        manager = ViewManager.getInstance();
        strokeWidth = STROKE_WIDTH_TO_FRAME_PROPORTION*manager.getRightFrameBorder();
        strokeColor = DEFAULT_STROKE_COLOR;
        paperColor = DEFAULT_PAPER_COLOR;
        actualCooldownOnFrame = timeOnFrameInAnimation = DEFAULT_TIME_ON_ONE_FRAME_IN_ANIMATION;

        doingAnimationNow = false;
        canBeDestroyedNow = false;
        this.animationSpeed = animationSpeed;
    }

    @Override
    public void update() {
        super.update();
        if (doingAnimationNow) {
            if (needToChangeFrame()) {
                nextFrameOfAnimation();
                restoreAnimationTimer();
            } else reduceAnimationTimer();
        }

        if (needToStopAnimation() && !canBeDestroyedNow)
            actionWhenAnimationStopped();
    }

    private boolean needToChangeFrame() {
        return actualCooldownOnFrame <= 0;
    }

    protected abstract void nextFrameOfAnimation();

    private void restoreAnimationTimer() {
        actualCooldownOnFrame = timeOnFrameInAnimation;
    }

    private void reduceAnimationTimer() {
        actualCooldownOnFrame -= 1;
    }

    protected abstract boolean needToStopAnimation();

    private void actionWhenAnimationStopped() {
        doingAnimationNow = false;
        canBeDestroyedNow = true;
    }

    protected void drawStrokeRect() {
        setColor(strokeColor);
        setLineWidth(strokeWidth);
        manager.getGraphicContext().strokeRect(positionX, positionY, width, height);
    }

    private void setColor(Color color) {
        manager.setFillColor(color);
    }

    private void setLineWidth(double lineWidth) {
        if (lineWidth < 1)
            manager.getGraphicContext().setLineWidth(STROKE_WIDTH_TO_FRAME_PROPORTION*manager.getRightFrameBorder());
        else manager.getGraphicContext().setLineWidth(lineWidth);
    }

    protected void drawFillRect() {
        setColor(paperColor);
        setLineWidth(strokeWidth);
        manager.getGraphicContext().fillRect(positionX, positionY, width, height);
    }

    @Override
    public void doAnimation() {
        doingAnimationNow = true;
    }

    @Override
    public boolean canBeDestroyedNow() {
        return canBeDestroyedNow;
    }
}
