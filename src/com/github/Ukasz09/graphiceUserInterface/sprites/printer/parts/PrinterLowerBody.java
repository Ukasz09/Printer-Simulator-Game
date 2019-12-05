package com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts;

import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class PrinterLowerBody extends ImageSprite {
    private final static Image DEFAULT_IMAGE = ImagesProperties.printerLowerBodySprite();
    public final static double PRINT_HOLE_TO_PRINTER_PROPORTION = 0.71;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterLowerBody(double width, double height, double positionX, double positionY) {
        super(DEFAULT_IMAGE, width, height, positionX, positionY);
    }
}
