package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator;

import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface IPrintDecorator {

    Image getImageWithAddedEffect(ImageView imageView);

    Effect addedEffects();

}
