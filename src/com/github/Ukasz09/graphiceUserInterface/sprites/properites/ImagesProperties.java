package com.github.Ukasz09.graphiceUserInterface.sprites.properites;

import javafx.scene.image.Image;

public class ImagesProperties {
    private static final int AMOUNT_OF_ZINGS_POSTERS = 4;

    public static Image roomBackground() {
        String imagePath = "\\resources\\images\\backgrounds\\room1.jpg";
        return new Image(imagePath);
    }

    public static Image deskSprite() {
        String imagePath = "\\resources\\images\\decorations\\desk.png";
        return new Image(imagePath);
    }

    public static Image printerUpperBodySprite() {
        String imagePath = "\\resources\\images\\printer\\printerUpperBody.png";
        return new Image(imagePath);
    }

    public static Image printerLowerBodySprite() {
        String imagePath = "\\resources\\images\\printer\\printerLowerBody.png";
        return new Image(imagePath);
    }

    public static Image printerSalverSprite() {
        String imagePath = "\\resources\\images\\printer\\salver.png";
        return new Image(imagePath);
    }

    public static Image flowerSprite() {
        String imagePath = "\\resources\\images\\decorations\\flower.png";
        return new Image(imagePath);
    }

    public static Image redInkSprite() {
        String imagePath = "\\resources\\images\\printer\\redInk.png";
        return new Image(imagePath);
    }

    public static Image blueInkSprite() {
        String imagePath = "\\resources\\images\\printer\\blueInk.png";
        return new Image(imagePath);
    }

    public static Image yellowInkSprite() {
        String imagePath = "\\resources\\images\\printer\\yellowInk.png";
        return new Image(imagePath);
    }

    public static Image blackInkSprite() {
        String imagePath = "\\resources\\images\\printer\\blackInk.png";
        return new Image(imagePath);
    }

    public static Image whiteInkSprite() {
        String imagePath = "\\resources\\images\\printer\\whiteInk.png";
        return new Image(imagePath);
    }

    public static Image schemeSpriteForImageView() {
        String imagePath = "\\resources\\images\\decorations\\testedForImageView.png";
        return new Image(imagePath);
    }

    public static Image[] zingsImages() {
        String zingsPrefixPath = "\\resources\\images\\superZings\\zings";
        Image[] zingsImages = new Image[AMOUNT_OF_ZINGS_POSTERS];
        for (int i = 0; i < AMOUNT_OF_ZINGS_POSTERS; i++)
            zingsImages[i] = new Image(zingsPrefixPath + (i + 1) + ".png");
        return zingsImages;
    }
}
