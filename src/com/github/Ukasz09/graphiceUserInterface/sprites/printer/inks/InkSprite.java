package com.github.Ukasz09.graphiceUserInterface.sprites.printer.inks;

import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class InkSprite extends ImageSprite {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public final static double WIDTH_TO_FRAME_PROPORTION = 64.0 / 1600;
    public final static double HEIGHT_TO_FRAME_PROPORTION = 128.0 / 900;

    private final ColorInk printerInk;
    private final CapacityStatusBar statusBar;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public InkSprite(Image inkImage, ColorInk printerInk, double positionX, double positionY) {
        super(WIDTH_TO_FRAME_PROPORTION * ViewManager.getInstance().getRightFrameBorder(), HEIGHT_TO_FRAME_PROPORTION * ViewManager.getInstance().getBottomFrameBorder(),inkImage, positionX, positionY);
        this.printerInk = printerInk;
        statusBar = new CapacityStatusBar(HEIGHT_TO_FRAME_PROPORTION*manager.getBottomFrameBorder(), positionX, positionY + height);
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
