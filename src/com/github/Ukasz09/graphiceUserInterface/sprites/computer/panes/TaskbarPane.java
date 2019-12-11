package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.observerPattern.IObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TaskbarPane extends ComputerPaneWithGraphicContext {
    private static final Image DEFAULT_WINDOW_BUTTON = ImagesProperties.windowsLogoTaskbarImage();
    private static final double DEFAULT_WINDOW_BUTTON_WIDTH = 80;
    public static final double DEFAULT_HEIGHT = 20;

    private final Image windowButtonImage = DEFAULT_WINDOW_BUTTON;
    private IPane startPane;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TaskbarPane(double positionX, double positionY, double width, double height, double monitorHeight) {
        super(positionX, positionY, width, height);
        addStartButton();
        addStartPane(width / 3, monitorHeight * 0.6);
        attachObserver(startPane);
        startPane.attachObserver(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addStartButton() {
        Button windowButton = makeButtonWithBackground(DEFAULT_WINDOW_BUTTON_WIDTH, getHeight(), windowButtonImage, EventKind.MENU_START_BUTTON);
        getPane().getChildren().add(windowButton);
    }

    private void addStartPane(double width, double height) {
        Point2D position = calculateStartPanePosition(height);
        startPane = new StartPane(position.getX(), position.getY(), width, height);
    }

    private Point2D calculateStartPanePosition(double paneHeight) {
        double positionX = getPositionX();
        double positionY = getPositionY() - paneHeight;
        return new Point2D(positionX, positionY);
    }

    @Override
    public void render() {
        renderTaskBar();
        startPane.render();
    }

    private void renderTaskBar() {
        setFillColor(Color.rgb(35, 93, 219));
        graphicContext.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void update() {
        startPane.update();
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case MONITOR_PANE:
            case PRINTER_PANE:
                startPane.getPane().setVisible(false);
                break;

            case PRINTER_BUTTON:
                notifyObservers(EventKind.PRINTER_BUTTON);
                startPane.getPane().setVisible(false);
                break;

            default:
                Logger.logError(getClass(), "Unknown eventKind");
        }
    }
}
