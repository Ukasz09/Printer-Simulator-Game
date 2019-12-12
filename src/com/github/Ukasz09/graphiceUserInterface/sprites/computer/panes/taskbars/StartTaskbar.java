package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.StartDialogWindow;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class StartTaskbar extends Taskbar {
    private static final Image DEFAULT_WINDOWS_BUTTON_IMAGE = ImagesProperties.windowsLogoTaskbarImage();
    private static final double DEFAULT_WINDOWS_BUTTON_WIDTH = 80;
    public static final double DEFAULT_WINDOWS_BUTTON_HEIGHT = 20;
    public static final double DEFAULT_START_WINDOW_TO_TASKBAR_PROPORTION = 0.3;

    private final Image windowButtonImage = DEFAULT_WINDOWS_BUTTON_IMAGE;
    private StartDialogWindow startDialogWindow;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StartTaskbar(double positionX, double positionY, double width, double height, double monitorHeight) {
        super(positionX, positionY, width, height);
        addStartButton();
        addStartPane(width * DEFAULT_START_WINDOW_TO_TASKBAR_PROPORTION, monitorHeight * 0.6);
        attachObserver(startDialogWindow);
        startDialogWindow.attachObserver(this); //TODO: czy potrzebne ??
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addStartButton() {
        Button windowButton = makeButtonWithBackgroundAndEventHandler(DEFAULT_WINDOWS_BUTTON_WIDTH, getHeight(), windowButtonImage, EventKind.MENU_START_BUTTON);
        getPane().getChildren().add(windowButton);
    }

    private void addStartPane(double width, double height) {
        Point2D position = calculateStartPanePosition(height);
        startDialogWindow = new StartDialogWindow(position.getX(), position.getY(), width, height);
    }

    private Point2D calculateStartPanePosition(double paneHeight) {
        double positionX = getPositionX();
        double positionY = getPositionY() - paneHeight;
        return new Point2D(positionX, positionY);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case MONITOR_PANE:
            case PRINTER_PANE:
            case EXIT_BUTTON:
                startDialogWindow.getPane().setVisible(false);
                break;

            case PRINTER_BUTTON:
                notifyObservers(EventKind.PRINTER_BUTTON);
                startDialogWindow.getPane().setVisible(false);
                break;

            case WALLPAPER_CHANGE:
                notifyObservers(EventKind.WALLPAPER_CHANGE);
                break;

            default:
                Logger.logError(getClass(), "Unknown eventKind");
        }
    }
}
