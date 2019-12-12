package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.ContentPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.StartDialogWindowTaskbar;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.Taskbar;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class StartDialogWindow extends WindowDialog {
    private static final double DEFAULT_TASKBAR_TO_WINDOW_PROPORTION = 0.2;
    private static final double DEFAULT_ICONS_TO_WINDOW_PROPORTION = 0.2;
    private static final Image DEFAULT_PRINTER_ICON_IMAGE = ImagesProperties.printerIconImage();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StartDialogWindow(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height, 1);
        getPane().setVisible(false);
        addStartPaneButtons();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected ContentPane makeContentPaneInstance() {
        double taskbarHeight = getHeight() * DEFAULT_TASKBAR_TO_WINDOW_PROPORTION;
        ContentPane contentPane = new ContentPane(0, 0, getWidth(), getHeight() - taskbarHeight);
        return contentPane;
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
    protected Taskbar makeWindowTaskbarPane() {
        double taskbarHeight = getHeight() * DEFAULT_TASKBAR_TO_WINDOW_PROPORTION;
        Taskbar startPaneTaskbar = new StartDialogWindowTaskbar(0, 0, getWidth(), taskbarHeight);
        return startPaneTaskbar;
    }

    private void addStartPaneButtons() {
        double sizeOfImageInButton = getWidth() * DEFAULT_ICONS_TO_WINDOW_PROPORTION;
        addPrinterButton(sizeOfImageInButton, DEFAULT_PRINTER_ICON_IMAGE, "Drukowanie");
    }

    private void addPrinterButton(double sizeOfImageInButton, Image buttonImage, String buttonTxt) {
        EventKind buttonEvent = EventKind.PRINTER_BUTTON;
        Pos alignment = Pos.BASELINE_LEFT;
        Button printerButton = makeButtonWithImageTextAndEventHandler(sizeOfImageInButton, sizeOfImageInButton, getWidth(), sizeOfImageInButton, buttonImage, buttonTxt, buttonEvent, alignment);
        AnchorPane.setTopAnchor(printerButton, getHeight() * DEFAULT_TASKBAR_TO_WINDOW_PROPORTION);
        getPane().getChildren().add(printerButton);
    }
}
