package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.sprites.states.PrinterState;

public class SpritesProperites {
    public static ImageSheetProperty printerSheetProperty() {
        String spritePath = "\\resources\\sprites\\printer\\printer.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(10)
                .withAddActionState(PrinterState.STANDBY, 0, 2)
                .build();
        return sheetProperty;
    }

}
