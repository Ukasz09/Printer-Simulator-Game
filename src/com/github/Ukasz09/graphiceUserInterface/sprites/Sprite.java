package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite {
    protected ViewManager manager;
    private Image spriteImage;
    protected ImageView spriteImageView;
    protected double positionX;
    protected double positionY;
    protected double width;
    protected double height;

    public Sprite(Image spriteImage, double width, double height) {
        this.spriteImage = spriteImage;
        this.width = width;
        this.height = height;
        positionX = 0;
        positionY = 0;
        manager = ViewManager.getInstance();
    }

    public void makeAndAddImageViewToRoot() {
        makeImageView();
        addImageViewToRoot();
    }

    protected void addEventHandler(EventType eventType, EventHandler eventHandler) {
        spriteImageView.addEventHandler(eventType, eventHandler);
    }

    protected void makeImageView() {
        spriteImageView = new ImageView(spriteImage);
        setImageViewPosition(positionX, positionY);
        setImageViewSize(width, height);
        spriteImageView.setVisible(true);
        spriteImageView.setOpacity(0);
    }

    protected void setImageViewPosition(double positionX, double positionY) {
        spriteImageView.setX(positionX);
        spriteImageView.setY(positionY);
    }

    protected void setImageViewSize(double width, double height) {
        spriteImageView.setFitWidth(width);
        spriteImageView.setFitHeight(height);
    }

    private void addImageViewToRoot() {
        manager.addImageViewAsNode(spriteImageView);
    }

    public void update() {
        updateImageView();
    }

    private void updateImageView() {
        if (spriteImageView != null) {
            setImageViewPosition(positionX, positionY);
            setImageViewSize(width, height);
        }
    }

    public void render() {
       renderSprite();
    }

    protected void renderSprite(){
        manager.getGraphicContext().drawImage(spriteImage, positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public double getWidth() {
        return width;
    }

    //todo: dac osobno body a wysokosc ustawic jako body+lower
    public double getHeight() {
        return height;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    protected void setSpriteImage(Image spriteImage) {
        this.spriteImage = spriteImage;
    }

    public Image getSpriteImage() {
        return spriteImage;
    }
}
