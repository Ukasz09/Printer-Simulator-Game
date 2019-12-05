package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite implements ISpriteGraphic {
    private static final Image DEFAULT_SCHEME_IMAGE = ImagesProperties.schemeSpriteForImageView();

    protected ViewManager manager;
    protected double positionX, positionY;
    protected double width, height;
    private ImageView spriteImageView;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Sprite(double width, double height, double positionX, double positionY) {
        manager = ViewManager.getInstance();
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;

        makeImageView();
        addImageViewToSceneRoot();
    }

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

    private void makeImageView() {
        spriteImageView = new ImageView(DEFAULT_SCHEME_IMAGE);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
}
