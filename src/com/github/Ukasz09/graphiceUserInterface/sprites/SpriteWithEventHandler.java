package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class SpriteWithEventHandler extends Sprite implements ISpriteGraphic{
    private static final Image DEFAULT_SCHEME_IMAGE = ImagesProperties.schemeSpriteForImageView();

    private ImageView spriteImageView;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SpriteWithEventHandler(double width, double height, double positionX, double positionY) {
        super(width, height, positionX, positionY);
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

    private void makeImageView() {
        spriteImageView = new ImageView(DEFAULT_SCHEME_IMAGE);
        setSamePropertyToImageViewAsImage(spriteImageView);
    }

    private void setSamePropertyToImageViewAsImage(ImageView iv) {
        iv.setOpacity(0);
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
    //todo: dopoki nie ma komputera
    public void setImageViewVisable(boolean visable){
        spriteImageView.setVisible(visable);
    }
}
