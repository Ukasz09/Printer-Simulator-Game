package com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts;

import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class PrinterUpperSalver extends ImageSprite {
    private final static Image DEFAULT_IMAGE = ImagesProperties.printerSalverSprite();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterUpperSalver(double width, double height, double positionX, double positionY) {
        super(width, height,DEFAULT_IMAGE, positionX, positionY);
    }
}
