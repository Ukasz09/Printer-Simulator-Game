package com.github.Ukasz09.graphiceUserInterface.sprites.printer.inks;

import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


public class InkSprite extends ImageSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public final static double DEFAULT_WIDTH = 64;
    public final static double DEFAULT_HEIGHT = 128;

    private final ColorInk printerInk;
    private final CapacityStatusBar statusBar;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public InkSprite(Image inkImage, ColorInk printerInk, double positionX, double positionY) {
        super(inkImage, DEFAULT_WIDTH, DEFAULT_HEIGHT, positionX, positionY);
        this.printerInk = printerInk;
        statusBar = new CapacityStatusBar(DEFAULT_HEIGHT, positionX, positionY + height);
        addInkEventHandler();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void update() {
        super.update();
        updateStatusBar();
    }

    private void updateStatusBar() {
        double actualInkPercentageLevel = printerInk.getPercentageLevel();
        statusBar.setActualCapacityPercents(actualInkPercentageLevel);
        statusBar.update();
    }

    @Override
    public void render() {
        super.render();
        statusBar.render();
    }

    private void addInkEventHandler() {
        addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> printerInk.refillInc());
    }
}
