package com.github.Ukasz09.graphiceUserInterface.sprites.properites;


import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.PrinterState;

public class SpritesProperties {

    public static ImageSheetProperty globeSheetProperty() {
        String spritePath = "resources/sprites/decorations/globe.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 256)
                .withDefaultDurationPerOneFrame(7)
                .withAddActionState(DecorationState.STANDBY, 0, 21)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty catSheetProperty() {
        String spritePath = "resources/sprites/decorations/cat.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(256, 350)
                .withDefaultDurationPerOneFrame(6)
                .withAddActionState(DecorationState.STANDBY, 0, 146)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty printerUpperBodyProperty() {
        String spritePath = "resources/sprites/computer/printerUpperBody.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(666, 238)
                .withDefaultDurationPerOneFrame(6)
                .withAddActionState(PrinterState.STANDBY, 3, 1)
                .withAddActionState(PrinterState.PRINTING, 0, 3)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty screenSaverProperty() {
        String spritePath = "resources/sprites/computer/screensaver.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(336, 252)
                .withDefaultDurationPerOneFrame(3)
                .withAddActionState(DecorationState.STANDBY, 0, 184)
                .build();
        return sheetProperty;
    }

//    public static ImageSheetProperty xmasTreeProperty() {
//        String spritePath = "resources/sprites/decorations/xmasTree.png";
//        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
//                .withImagePath(spritePath)
//                .withSizeOfOneFrame(329, 340)
//                .withDefaultDurationPerOneFrame(10)
//                .withAddActionState(DecorationState.STANDBY, 0, 4)
//                .build();
//        return sheetProperty;
//    }

    public static ImageSheetProperty xmasTreeProperty() {
        String spritePath = "resources/sprites/decorations/xmasTree.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(232, 352)
                .withDefaultDurationPerOneFrame(20)
                .withAddActionState(DecorationState.STANDBY, 0, 3)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty xmasChainProperty() {
        String spritePath = "resources/sprites/decorations/xmasChain.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(718, 240)
                .withDefaultDurationPerOneFrame(12)
                .withAddActionState(DecorationState.STANDBY, 0, 3)
                .build();
        return sheetProperty;
    }

    public static ImageSheetProperty xmasLampProperty() {
        String spritePath = "resources/sprites/decorations/xmasLamp.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(700, 183)
                .withDefaultDurationPerOneFrame(12)
                .withAddActionState(DecorationState.STANDBY, 0, 3)
                .build();
        return sheetProperty;
    }
}
