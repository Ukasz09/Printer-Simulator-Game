package com.github.Ukasz09.graphiceUserInterface.sprites.properites;


import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;

public class SpritesProperites {

    public static ImageSheetProperty globeSheetProperty() {
        String spritePath = "\\resources\\sprites\\decorations\\globe.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(7)
                .withAddActionState(DecorationState.STANDBY, 0, 21)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty catSheetProperty() {
        String spritePath = "\\resources\\sprites\\decorations\\cat.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.bulider()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 350)
                .withDefaultDurationPerOneFrame(6)
                .withAddActionState(DecorationState.STANDBY, 0, 146)
                .build();
        return sheetProperty;
    }

}
