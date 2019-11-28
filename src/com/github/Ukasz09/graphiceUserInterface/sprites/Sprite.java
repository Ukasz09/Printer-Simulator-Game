package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;

public abstract class Sprite {
    private ViewManager manager;

    private double positionX;
    private double positionY;
    private double width;
    private double height;

    private ImageSheetProperty spriteSheetProperties;
    private double remainingTimeOnActualFrame;
    private double actualFramePositionY;
    private double actualFramePositionX;
    private FrameStatePositions actualState;

    //todo: dac bulidera pozniej
    public Sprite(ImageSheetProperty spriteSheetProperties, double width, double height, FrameStatePositions actualState) {
        this.spriteSheetProperties = spriteSheetProperties;
        this.actualState = actualState;
        this.width = width;
        this.height = height;
        positionX = 0;
        positionY = 0;
        manager = ViewManager.getInstance();
        remainingTimeOnActualFrame = 0;
        actualFramePositionX = 0;
        actualFramePositionY = 0;
    }

    private void updateSpriteSheetFrame() {
        updateRemainingTimeOnFrame();
        if (needToChangeFrame()) {
            setPositionOfNextFrame(actualState.getMinX(), actualState.getMaxX(), actualState.getMinY(), actualState.getMaxY(), spriteSheetProperties.getSheetWidth()); //todo: tmp
            restoreRemainingTimeOnFrame();
        }
    }

    private void updateRemainingTimeOnFrame() {
        remainingTimeOnActualFrame -= 1;
    }

    private boolean needToChangeFrame() {
        return (remainingTimeOnActualFrame <= 0);
    }

    private void restoreRemainingTimeOnFrame() {
        remainingTimeOnActualFrame = spriteSheetProperties.getDurationPerFrame();
    }

    private void setPositionOfNextFrame(double minXPosition, double maxXPosition, double minYPosition, double maxYPosition, double sheetWidth) {
        //Finished one cycle
        actualFramePositionX += spriteSheetProperties.getWidthOfOneFrame();
        if (actualFramePositionX >= maxXPosition && actualFramePositionY >= maxYPosition) {
            actualFramePositionX = minXPosition;
            actualFramePositionY = minYPosition;
        }
        //Steped out of sheet
        else if (actualFramePositionX >= sheetWidth) {
            actualFramePositionX = 0;
            actualFramePositionY += spriteSheetProperties.getHeightOfOneFrame();
        }
    }

    public void update() {
        updateSpriteSheetFrame();
    }

    public void render() {
        double widthOfOneFrame = spriteSheetProperties.getWidthOfOneFrame();
        double heightOfOneFrame = spriteSheetProperties.getHeightOfOneFrame();
        manager.getGraphicContext().drawImage(spriteSheetProperties.getSheet(), actualFramePositionX, actualFramePositionY,
                widthOfOneFrame, heightOfOneFrame, positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public ViewManager getManager() {
        return manager;
    }
}
