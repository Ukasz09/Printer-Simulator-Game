package com.github.Ukasz09.graphiceUserInterface.sprites;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ImageSprite extends Sprite implements IEventHandler {
    private Image spriteImage;
    private ImageView spriteImageView;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ImageSprite(Image spriteImage, double width, double height, double positionX, double positionY) {
        super(width, height, positionX, positionY);
        this.spriteImage = spriteImage;
        makeImageView();
        addImageViewToSceneRoot();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void update() {
        updateImageView();
    }

    private void updateImageView() {
        if (spriteImageView != null) {
            setImageViewPosition(spriteImageView, positionX, positionY);
            setImageViewSize(spriteImageView, width, height);
        }
    }

    @Override
    public void render() {
        renderSprite();
    }

    private void renderSprite() {
        manager.getGraphicContext().drawImage(spriteImage, positionX, positionY, width, height);
    }

    private void makeImageView() {
        spriteImageView = new ImageView(spriteImage);
        setSamePropertyToImageViewAsImage(spriteImageView);
    }

    private void setSamePropertyToImageViewAsImage(ImageView iv) {
        iv.setOpacity(1);  //TODO: tmp dla testow
        iv.setVisible(true);
        setImageViewPosition(iv, positionX, positionY);
        setImageViewSize(iv, width, height);
    }

    private void setImageViewPosition(ImageView iv, double positionX, double positionY) {
        iv.setX(positionX);
        iv.setY(positionY);
    }

    private void setImageViewSize(ImageView iv, double width, double height) {
        iv.setFitWidth(width);
        iv.setFitHeight(height);
    }

    private void addImageViewToSceneRoot() {
        manager.addImageViewAsNode(spriteImageView);
    }

    @Override
    public void addNewEventHandler(EventType eventType, EventHandler eventHandler) {
        spriteImageView.addEventHandler(eventType, eventHandler);
    }
}
