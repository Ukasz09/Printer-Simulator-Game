package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.sprites.properites.FrameStatePositions;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.IKindOfState;

public abstract class AnimatedSprite extends ImageSprite {
    private ImageSheetProperty spriteSheetProperty;
    private FrameStatePositions actualAnimationState;
    private double actualCooldownOnFrame;
    private double actualFramePositionX;
    private double actualFramePositionY;

    public AnimatedSprite(double width, double height, double positionX, double positionY,
                          ImageSheetProperty spriteSheetProperty, FrameStatePositions actualAnimationState) {
        super(width, height, ImagesProperties.schemeSpriteForImageView(), positionX, positionY);

        this.spriteSheetProperty = spriteSheetProperty;
        this.actualAnimationState = actualAnimationState;
        actualCooldownOnFrame = 0;
        actualFramePositionX = 0;
        actualFramePositionY = 0;
    }

    @Override
    public void update() {
        super.update();
        updateSpriteSheetFrame();
    }

    private void updateSpriteSheetFrame() {
        updateActualCooldownOnFrame();
        if (needToChangeFrame()) {
            double minPositionX = actualAnimationState.getMinX();
            double maxPositionX = actualAnimationState.getMaxX();
            double minPositionY = actualAnimationState.getMinY();
            double maxPositionY = actualAnimationState.getMaxY();
            setPositionOfNextFrame(minPositionX, maxPositionX, minPositionY, maxPositionY, spriteSheetProperty.getSheetWidth());
            restoreActualCooldown();
        }
    }

    private void updateActualCooldownOnFrame() {
        actualCooldownOnFrame -= 1;
    }

    private boolean needToChangeFrame() {
        return (actualCooldownOnFrame <= 0);
    }

    private void setPositionOfNextFrame(double minXPosition, double maxXPosition, double minYPosition, double maxYPosition, double sheetWidth) {
        //Finished one cycle
        actualFramePositionX += spriteSheetProperty.getWidthOfOneFrame();
        if (actualFramePositionX >= maxXPosition && actualFramePositionY >= maxYPosition) {
            actualFramePositionX = minXPosition;
            actualFramePositionY = minYPosition;
        }
        //Steped out of sheet
        else if (actualFramePositionX >= sheetWidth) {
            actualFramePositionX = 0;
            actualFramePositionY += spriteSheetProperty.getHeightOfOneFrame();
        }
    }

    private void restoreActualCooldown() {
        actualCooldownOnFrame = spriteSheetProperty.getTimeOnFrameInAnimation();
    }

    @Override
    public void render() {
        double widthOfOneFrame = spriteSheetProperty.getWidthOfOneFrame();
        double heightOfOneFrame = spriteSheetProperty.getHeightOfOneFrame();
        manager.getGraphicContext().drawImage(spriteSheetProperty.getSheet(), actualFramePositionX, actualFramePositionY,
                widthOfOneFrame, heightOfOneFrame, positionX, positionY, width, height);
    }

    public void changeState(IKindOfState state) {
        actualAnimationState = spriteSheetProperty.getAction(state);
    }
}
