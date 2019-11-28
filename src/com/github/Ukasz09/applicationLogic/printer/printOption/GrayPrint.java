package com.github.Ukasz09.applicationLogic.printer.printOption;

import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class GrayPrint implements PrintOption {
    private static final String DEFAULT_BUTTON_NAME = "COLOR: GRAY";
    private String buttonName;

    public GrayPrint() {
        buttonName = DEFAULT_BUTTON_NAME;
    }

    @Override
    public Image applyPropertiesToImage(Image image) {
        ImageView iv = new ImageView(image);
        ColorAdjust grayEffect = new ColorAdjust();
        grayEffect.setSaturation(-1);
        iv.setEffect(grayEffect);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return iv.snapshot(params, null);
    }
}
