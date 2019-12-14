package com.github.Ukasz09.graphiceUserInterface.sprites.printer.parts;

import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.PrinterState;

public class PrinterUpperBody extends AnimatedSprite {
    private final static ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.printerUpperBodyProperty();
    public final static double PRINT_HOLE_TO_PRINTER_PROPORTION = 0.35;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterUpperBody(double width, double height, double positionX, double positionY) {
        super(width, height,positionX, positionY,DEFAULT_SHEET_PROPERTY, DEFAULT_SHEET_PROPERTY.getAction(PrinterState.STANDBY));
        setImageViewVisible(false);
    }
}
