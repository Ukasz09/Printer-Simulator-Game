package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.FrameStatePositions;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.ImageView;

public abstract class AnimatedSprite extends Sprite {
    private ImageSheetProperty spriteSheetProperties;
    private double remainingTimeOnActualFrame;
    private double actualFramePositionY;
    private double actualFramePositionX;
    private FrameStatePositions actualState;

    //todo: dac bulidera pozniej
    public AnimatedSprite(ImageSheetProperty spriteSheetProperties, double width, double height, FrameStatePositions actualState) {
        super(spriteSheetProperties.getSheet(), width, height);
        this.spriteSheetProperties = spriteSheetProperties;
        this.actualState = actualState;
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

    @Override
    public void update() {
        super.update();
        updateSpriteSheetFrame();
    }

    @Override
    public void render() {
        double widthOfOneFrame = spriteSheetProperties.getWidthOfOneFrame();
        double heightOfOneFrame = spriteSheetProperties.getHeightOfOneFrame();
        manager.getGraphicContext().drawImage(spriteSheetProperties.getSheet(), actualFramePositionX, actualFramePositionY,
                widthOfOneFrame, heightOfOneFrame, positionX, positionY, width, height);
    }

    @Override
    protected void makeImageView() {
        spriteImageView = new ImageView(ImagesProperties.schemeSpriteForAnimatedImageView());
        spriteImageView.setOpacity(0);
        setImageViewPosition(positionX, positionY);
        setImageViewSize(spriteSheetProperties.getWidthOfOneFrame(), spriteSheetProperties.getHeightOfOneFrame());
        spriteImageView.setVisible(true);
    }
}
