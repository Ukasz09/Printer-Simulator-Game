package com.github.Ukasz09.applicationLogic.printer.printOption;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface IPrintDecorator {

    Image getImageWithAddedEffect(ImageView imageView);
}
