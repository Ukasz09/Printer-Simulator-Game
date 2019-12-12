package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator;

import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class BasePrintDecorator implements IPrintDecorator {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected Effect addedEffects() {
        return new ColorAdjust();
    }

    public Image getImageWithAddedEffect(ImageView imageView) {
        imageView.setEffect(addedEffects());
        return getImageFromImageView(imageView);
    }

    private Image getImageFromImageView(ImageView imageView) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return imageView.snapshot(params, null);
    }
}
