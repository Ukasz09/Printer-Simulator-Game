package com.github.Ukasz09.graphiceUserInterface.sprites.printer;

import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class InkSprite extends Sprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static double DEFAULT_WIDTH = 64;
    private final static double DEFAULT_HEIGHT = 128;

    private final ColorInk colorInk;
    private final CapacityStatusBar statusBar;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public InkSprite(Image inkImage, ColorInk colorInk) {
        super(inkImage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.colorInk = colorInk;
        statusBar = new CapacityStatusBar(DEFAULT_HEIGHT);
        makeAndAddImageViewToRoot();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void update() {
        super.update();
        updateStatusBar();
    }

    private void updateStatusBar() {
        statusBar.update(colorInk.getPercentageLevel());
    }

    @Override
    public void render() {
        super.render();
        statusBar.render();
    }

    @Override
    public void setPosition(double positionX, double positionY) {
        super.setPosition(positionX, positionY);
        setStatusBarPosition();
    }

    private void setStatusBarPosition() {
        double positionX = this.positionX;
        double positionY = this.positionY + height;
        statusBar.setPosition(positionX, positionY);
    }

     void addEventHandler() {
      addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
         colorInk.shrinkActualInkCapacity();
      });
    }
}
