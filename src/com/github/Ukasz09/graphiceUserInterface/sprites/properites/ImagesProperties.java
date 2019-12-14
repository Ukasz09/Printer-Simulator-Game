package com.github.Ukasz09.graphiceUserInterface.sprites.properites;

import javafx.scene.image.Image;

public class ImagesProperties {
    private static final int AMOUNT_OF_ZINGS_POSTERS = 18;
    private static final int AMOUNT_OF_WALLPAPERS = 5;

    //backgrounds
    public static Image roomBackground() {
        String imagePath = "resources/images/backgrounds/room1.jpg";
        return new Image(imagePath);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //decorations
    public static Image deskSprite() {
        String imagePath = "resources/images/decorations/desk.png";
        return new Image(imagePath);
    }

    public static Image[] zingsPosterSprites() {
        String zingsPrefixPath = "resources/images/superZings/zings";
        Image[] zingsImages = new Image[AMOUNT_OF_ZINGS_POSTERS];
        for (int i = 0; i < AMOUNT_OF_ZINGS_POSTERS; i++)
            zingsImages[i] = new Image(zingsPrefixPath + (i + 1) + ".png");
        return zingsImages;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //printer
    public static Image printerLowerBodySprite() {
        String imagePath = "resources/images/printer/printerLowerBody.png";
        return new Image(imagePath);
    }

    public static Image printerSalverSprite() {
        String imagePath = "resources/images/printer/salver.png";
        return new Image(imagePath);
    }

    //inks
    public static Image redInkSprite() {
        String imagePath = "resources/images/printer/redInk.png";
        return new Image(imagePath);
    }

    public static Image blueInkSprite() {
        String imagePath = "resources/images/printer/blueInk.png";
        return new Image(imagePath);
    }

    public static Image yellowInkSprite() {
        String imagePath = "resources/images/printer/yellowInk.png";
        return new Image(imagePath);
    }

    public static Image blackInkSprite() {
        String imagePath = "resources/images/printer/blackInk.png";
        return new Image(imagePath);
    }

    public static Image whiteInkSprite() {
        String imagePath = "resources/images/printer/whiteInk.png";
        return new Image(imagePath);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //computer
    public static Image monitorSprite() {
        String imagePath = "resources/images/computer/computer.png";
        return new Image(imagePath);
    }

    public static Image[] wallpaperImages() {
        String prefixPath = "resources/images/computer/wallpapers/wallpaper";
        Image[] wallpaperImages = new Image[AMOUNT_OF_WALLPAPERS];
        for (int i = 0; i < AMOUNT_OF_WALLPAPERS; i++)
            wallpaperImages[i] = new Image(prefixPath + (i + 1) + ".jpg");
        return wallpaperImages;
    }

    //icons
    public static Image printerIconImage() {
        String imagePath = "resources/images/computer/printerIcon.png";
        return new Image(imagePath);
    }

    public static Image windowsLogoTaskbarIcon() {
        String imagePath = "resources/images/computer/windowsLogo.png";
        return new Image(imagePath);
    }

    public static Image exitButtonIcon() {
        String imagePath = "resources/images/computer/exitButton.png";
        return new Image(imagePath);
    }

    public static Image userLogoIcon() {
        String imagePath = "resources/images/computer/userLogo.png";
        return new Image(imagePath);
    }

    public static Image wallpaperChangeIcon() {
        String imagePath = "resources/images/computer/wallpaperChange.png";
        return new Image(imagePath);
    }

    public static Image themeChangeIcon() {
        String imagePath = "resources/images/computer/themeChange.png";
        return new Image(imagePath);
    }

    //errors
    public static Image emptyInksError() {
        String imagePath = "resources/images/computer/errors/emptyInksError.png";
        return new Image(imagePath);
    }

    public static Image printerIsBusyError() {
        String imagePath = "resources/images/computer/errors/workInProgressError.png";
        return new Image(imagePath);
    }

    public static Image noPaperError() {
        String imagePath = "resources/images/computer/errors/runOutPaperError.png";
        return new Image(imagePath);
    }

    public static Image fullPrintedPagesStackError() {
        String imagePath = "resources/images/computer/errors/fullPaperStackError.png";
        return new Image(imagePath);
    }

    public static Image unknownError() {
        String imagePath = "resources/images/computer/errors/unknownError.png";
        return new Image(imagePath);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //other
    public static Image thumbnailImage() {
        String imagePath = "resources/images/computer/thumbnail.jpg";
        return new Image(imagePath);
    }

    public static Image schemeSpriteForImageView() {
        String imagePath = "resources/images/decorations/testedForImageView.png";
        return new Image(imagePath);
    }
}
