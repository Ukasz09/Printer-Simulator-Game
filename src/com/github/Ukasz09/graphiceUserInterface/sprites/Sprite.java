package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;
import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite implements IDrawingGraphic {
    protected ViewManager manager;
    protected double positionX, positionY;
    protected double width, height;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Sprite(double width, double height, double positionX, double positionY) {
        manager = ViewManager.getInstance();
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
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
