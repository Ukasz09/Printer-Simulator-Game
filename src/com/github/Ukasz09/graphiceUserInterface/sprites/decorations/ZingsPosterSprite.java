package com.github.Ukasz09.graphiceUserInterface.sprites.decorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class ZingsPosterSprite extends Sprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static double DEFAULT_WIDTH = 280;
    private final static double DEFAULT_HEIGHT = 350;
    public final static double FRAME_THICKNESS = 10;

    private static final Image[] zingsImages = ImagesProperties.zingsImages();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ZingsPosterSprite() {
        super(zingsImages[0], DEFAULT_WIDTH, DEFAULT_HEIGHT);
        makeAndAddImageViewToRoot();
        addEventHandler();
    }

    private void addEventHandler() {
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setRandomZingsImage();
        });
    }

    private void setRandomZingsImage() {
        setSpriteImage(zingsImages[getRandomIndex()]);
    }

    private int getRandomIndex() {
        return (int) (Math.random() * zingsImages.length);
    }
}
