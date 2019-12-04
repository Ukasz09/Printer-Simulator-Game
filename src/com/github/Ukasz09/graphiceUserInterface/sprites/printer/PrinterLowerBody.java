package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class PrinterLowerBody extends Sprite {

    private final static Image DEFAULT_IMAGE = ImagesProperties.printerLowerBodySprite();
    private final static double DEFAULT_WIDTH = 280;
    private final static double DEFAULT_HEIGHT = 80;

    public PrinterLowerBody() {
        super(DEFAULT_IMAGE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}
