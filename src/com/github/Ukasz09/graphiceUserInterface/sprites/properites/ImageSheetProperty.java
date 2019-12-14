package com.github.Ukasz09.graphiceUserInterface.sprites.properites;

import com.github.Ukasz09.graphiceUserInterface.sprites.states.IKindOfState;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class ImageSheetProperty {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final class Builder {
        private Image imageSheet;
        private Map<IKindOfState, FrameStatePositions> actionStates;
        private double widthOfOneFrame;
        private double heightOfOneFrame;
        private int maxAmountOfFramesInRow;
        private double timeOnFrameInAnimation;

        private Builder(int amountOfFramesInRow) {
            maxAmountOfFramesInRow = amountOfFramesInRow;
            timeOnFrameInAnimation = DEFAULT_DURATION_PER_FRAME;
            actionStates = new HashMap<>();
        }

        Builder withImagePath(String imagePath) {
            this.imageSheet = new Image(imagePath);
            return this;
        }

        Builder withSizeOfOneFrame(double widthOfOneFrame, double heightOfOneFrame) {
            this.widthOfOneFrame = widthOfOneFrame;
            this.heightOfOneFrame = heightOfOneFrame;
            return this;
        }

        Builder withDefaultDurationPerOneFrame(double defaultDurationPerOneFrame) {
            this.timeOnFrameInAnimation = defaultDurationPerOneFrame;
            return this;
        }

        Builder withAddActionState(IKindOfState state, int startedIndex, int amountOfFrames) {
            this.actionStates.put(state, getFrameState(startedIndex, amountOfFrames));
            return this;
        }

        private FrameStatePositions getFrameState(int startedIndex, int amountOfFrames) {
            Point2D startPosOfFirstFrame = getPositionOfIndex(startedIndex);
            Point2D startPositionOfLastFrame = getPositionOfIndex(startedIndex + amountOfFrames - 1);
            FrameStatePositions frameState = new FrameStatePositions(startPosOfFirstFrame.getX(), startPositionOfLastFrame.getX() + widthOfOneFrame, startPosOfFirstFrame.getY(), startPositionOfLastFrame.getY());
            frameState.setIndexes(startedIndex, startedIndex + amountOfFrames - 1);
            return frameState;
        }

        private Point2D getPositionOfIndex(int index) {
            return ImageSheetProperty.getPositionOfIndex(index, maxAmountOfFramesInRow, widthOfOneFrame, heightOfOneFrame);
        }

        ImageSheetProperty build() {
            ImageSheetProperty imageSheetProperty = new ImageSheetProperty();
            imageSheetProperty.imageSheet = this.imageSheet;
            imageSheetProperty.widthOfOneFrame = this.widthOfOneFrame;
            imageSheetProperty.heightOfOneFrame = this.heightOfOneFrame;
            imageSheetProperty.timeOnFrameInAnimation = this.timeOnFrameInAnimation;
            imageSheetProperty.actionStates = this.actionStates;
            imageSheetProperty.maxAmountOfFramesInRow = this.maxAmountOfFramesInRow;
            return imageSheetProperty;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static final int DEFAULT_FRAMES_IN_ROW = 10;
    private static final double DEFAULT_DURATION_PER_FRAME = 3;

    private Image imageSheet;
    private Map<IKindOfState, FrameStatePositions> actionStates;
    private double widthOfOneFrame;
    private double heightOfOneFrame;
    private double timeOnFrameInAnimation;
    private int maxAmountOfFramesInRow;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ImageSheetProperty() {
        //Nothing to do...
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static Builder builder(int amountOfFramesInRow) {
        return new Builder(amountOfFramesInRow);
    }

    static Builder builder() {
        return new Builder(DEFAULT_FRAMES_IN_ROW);
    }

    public Point2D getPositionOfIndex(int index) {
        return getPositionOfIndex(index, maxAmountOfFramesInRow, widthOfOneFrame, heightOfOneFrame);
    }

    private static Point2D getPositionOfIndex(int index, int maxAmountOfFramesInRow, double widthOfOneFrame, double heightOfOneFrame) {
        if (index < 0)
            throw new InvalidParameterException();
        if (index == 0) return new Point2D(0, 0);

        double maxXOffset, maxYOffset;
        int rowsOffset;
        int columnsOffset;

        if (index >= maxAmountOfFramesInRow)
            rowsOffset = index / maxAmountOfFramesInRow;
        else rowsOffset = 0;

        int restOfFrames = index % maxAmountOfFramesInRow;
        int freeFramesOnStartedRow = maxAmountOfFramesInRow;
        if (restOfFrames > freeFramesOnStartedRow) {
            rowsOffset++;
            columnsOffset = -(restOfFrames - freeFramesOnStartedRow);
        } else columnsOffset = restOfFrames;

        maxXOffset = widthOfOneFrame * columnsOffset;
        maxYOffset = heightOfOneFrame * rowsOffset;

        return new Point2D(maxXOffset, maxYOffset);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Image getSheet() {
        return imageSheet;
    }

    public double getTimeOnFrameInAnimation() {
        return timeOnFrameInAnimation;
    }

    public FrameStatePositions getAction(IKindOfState kindOfAction) {
        return actionStates.get(kindOfAction);
    }

    public double getWidthOfOneFrame() {
        return widthOfOneFrame;
    }

    public double getHeightOfOneFrame() {
        return heightOfOneFrame;
    }

    public double getSheetWidth() {
        return imageSheet.getWidth();
    }
}
