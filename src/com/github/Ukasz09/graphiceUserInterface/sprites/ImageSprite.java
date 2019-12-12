package com.github.Ukasz09.graphiceUserInterface.sprites;

import javafx.scene.image.Image;

public abstract class ImageSprite extends SpriteWithEventHandler {
    private Image spriteImage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ImageSprite(Image spriteImage, double width, double height, double positionX, double positionY) {
        super(width, height, positionX, positionY);
        this.spriteImage = spriteImage;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render() {
        renderSprite();
    }

    private void renderSprite() {
        manager.getGraphicContext().drawImage(spriteImage, positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void setSpriteImage(Image spriteImage) {
        this.spriteImage = spriteImage;
    }

    public Image getSpriteImage() {
        return spriteImage;
    }
}
