package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class ZingsPosterSprite extends ImageSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static double DEFAULT_WIDTH = 280;
    private final static double DEFAULT_HEIGHT = 350;
    public final static double DEFAULT_FRAME_THICKNESS = 10;

    private static final Image[] zingsImages = ImagesProperties.zingsImages();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ZingsPosterSprite(double positionX, double positionY) {
        super(zingsImages[getRandomIndex()], DEFAULT_WIDTH, DEFAULT_HEIGHT, positionX, positionY);
        addPosterEventHandler();
    }

    private void addPosterEventHandler() {
        addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setRandomZingsImage();
        });
    }

    private void setRandomZingsImage() {
        setSpriteImage(zingsImages[getRandomIndex()]);
    }

    private static int getRandomIndex() {
        return (int) (Math.random() * zingsImages.length);
    }
}
