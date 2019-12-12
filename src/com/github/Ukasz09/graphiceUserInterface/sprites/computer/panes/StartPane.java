package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class StartPane extends ComputerPaneWithGraphicContext {
    private static final Image DEFAULT_PRINTER_ICON_IMAGE = ImagesProperties.printerIconImage();
    private static final Image DEFAULT_STARTPANE_IMAGE = ImagesProperties.windowsStartPaneImage();
    private static final double DEFAULT_UPPERTASKBAR_TO_STARTPANE_PROPORTION =0.13;

    private Image printerIconImage = DEFAULT_PRINTER_ICON_IMAGE;

    public StartPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        getPane().setVisible(false);
        addStartPaneButtons();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addStartPaneButtons() {
        addPrinterButton(getWidth() / 4, getWidth() / 4, printerIconImage);
    }

    private void addPrinterButton(double width, double height, Image buttonImage) {
        Button printerButton = makeButtonWithImageAndEventHandler(width, height, buttonImage,EventKind.PRINTER_BUTTON);
        AnchorPane.setTopAnchor(printerButton,getHeight()*DEFAULT_UPPERTASKBAR_TO_STARTPANE_PROPORTION);
        getPane().getChildren().add(printerButton);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case MENU_START_BUTTON:
                getPane().setVisible(!getPane().isVisible());
                break;

            default:
                Logger.logError(getClass(), "Unknown eventKind");
        }
    }

    @Override
    public void update() {
        //nothing to do
    }

    //todo: tmp
    @Override
    public void render() {
        setFillColor(Color.rgb(45, 10, 190));
        graphicContext.drawImage(DEFAULT_STARTPANE_IMAGE, 0, 0, getWidth(), getHeight());
    }
}
