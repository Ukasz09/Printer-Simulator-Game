package com.github.Ukasz09.graphiceUserInterface.sprites.properites;

import javafx.scene.image.Image;

public class ImagesProperties {
    public static Image roomBackground() {
        String imagePath = "\\resources\\images\\backgrounds\\room1.jpg";
        return new Image(imagePath);
    }

    public static Image deskSprite() {
        String imagePath = "\\resources\\images\\decorations\\desk.png";
        return new Image(imagePath);
    }

//    public static Image printerFullSprite() {
//        String imagePath = "\\resources\\images\\printer\\printerFull.png";
//        return new Image(imagePath);
//    }

    public static Image printerUpperBodySprite() {
        String imagePath = "\\resources\\images\\printer\\printerUpperBody.png";
        return new Image(imagePath);
    }


    public static Image printerLowerBodySprite() {
        String imagePath = "\\resources\\images\\printer\\printerLowerBody.png";
        return new Image(imagePath);
    }

    public static Image printerSalverSprite(){
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

    public static Image schemeSpriteForAnimatedImageView() {
        String imagePath = "\\resources\\images\\decorations\\testedForImageView.png";
        return new Image(imagePath);
    }

    public static Image[] zingsImages() {
        String zingsPrefixPath = "\\resources\\images\\superZings\\zings";
        Image[] zingsImages = new Image[4];
        //todo: tmp
        for (int i = 0; i < zingsImages.length; i++ )
            zingsImages[i] = new Image(zingsPrefixPath + (i + 1) + ".png");
        return zingsImages;
    }
}
