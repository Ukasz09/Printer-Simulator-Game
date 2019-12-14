package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.StartDialogWindow;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StartTaskbar extends Taskbar {
    private static final Image DEFAULT_WINDOWS_BUTTON_IMAGE = ImagesProperties.windowsLogoTaskbarIcon();
    private static final double DEFAULT_WINDOWS_BUTTON_WIDTH = 0.05;
    public static final double DEFAULT_WINDOWS_BUTTON_HEIGHT = 0.022; //0.0223
    public static final double DEFAULT_START_WINDOW_TO_TASKBAR_PROPORTION = 0.3;

    private final Image windowButtonImage = DEFAULT_WINDOWS_BUTTON_IMAGE;
    private StartDialogWindow startDialogWindow;

    private SimpleDateFormat timeFormatter;
    private Text timeText;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StartTaskbar(double positionX, double positionY, double width, double height, double monitorHeight) {
        super(positionX, positionY, width, height);
        timeFormatter = new SimpleDateFormat("HH:mm:ss");
        addStartButton();
        addStartPane(width * DEFAULT_START_WINDOW_TO_TASKBAR_PROPORTION, monitorHeight * 0.6);
        attachObserver(startDialogWindow);
        startDialogWindow.attachObserver(this); //TODO: czy potrzebne ??

        addTimePane();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addStartButton() {
        Button windowButton = makeButtonWithBackgroundAndEventHandler(DEFAULT_WINDOWS_BUTTON_WIDTH * manager.getRightFrameBorder(), getHeight(), windowButtonImage, EventKind.MENU_START_BUTTON);
        getPane().getChildren().add(windowButton);
    }

    private void addTimePane() {
        FlowPane pane = new FlowPane();
        setTimePaneProperty(pane);
        AnchorPane.setRightAnchor(pane, pane.getMaxWidth() / 10);
        getPane().getChildren().add(pane);
    }

    private void setTimePaneProperty(FlowPane pane) {
        pane.setMaxSize(getWidth() / 8, getHeight());
        pane.setMinSize(getWidth() / 8, getHeight());
        pane.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor(pane, 0.0);
        makeTimeText(pane.getMaxWidth());
        pane.getChildren().add(timeText);
    }

    private void makeTimeText(double paneMaxWidth) {
        timeText = new Text(timeFormatter.format(Calendar.getInstance().getTime()));
        timeText.setFill(Color.WHITE);
        timeText.setStyle(String.format("-fx-font-size: %dpx;", (int) (paneMaxWidth / 3.5)));
    }

    private void addStartPane(double width, double height) {
        startDialogWindow = new StartDialogWindow(0, -height, width, height);
        getPane().getChildren().add(startDialogWindow.getPane());
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case MONITOR_PANE:
            case PRINTER_PANE:
            case EXIT_BUTTON:
            case ERROR_PANE:
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
                Logger.logError(getClass(), "Unknown eventKind:" + eventKind.toString());
        }
    }

    @Override
    public void update() {
        super.update();
        startDialogWindow.update();
        updateTime();
    }

    private void updateTime() {
        timeText.setText(timeFormatter.format(Calendar.getInstance().getTime()));
    }

    @Override
    public void render() {
        super.render();
        startDialogWindow.render();
    }
}
