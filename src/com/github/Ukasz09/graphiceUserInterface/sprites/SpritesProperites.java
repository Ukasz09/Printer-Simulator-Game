package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.GlobeSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.PrinterState;

public class SpritesProperites {
    public static ImageSheetProperty printerSheetProperty() {
        String spritePath = "\\resources\\sprites\\printer\\printer.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(20)
                .withAddActionState(PrinterState.STANDBY, 0, 2)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty globeSheetProperty() {
        String spritePath = "\\resources\\sprites\\decorations\\globe.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(5)
                .withAddActionState(DecorationState.STANDBY, 0, 21)
                .build();
        return sheetProperty;
    }


}
