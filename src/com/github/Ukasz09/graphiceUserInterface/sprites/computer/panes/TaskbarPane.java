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

public class TaskbarPane extends ComputerPane implements IObserver {
    private static final Image DEFAULT_WINDOW_BUTTON = ImagesProperties.windowsLogoTaskbarImage();
    private static final double DEFAULT_WINDOW_BUTTON_WIDTH = 80;
    public static final double DEFAULT_HEIGHT = 20;

    private final Image windowButtonImage = DEFAULT_WINDOW_BUTTON;
    private StartPane startPane;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TaskbarPane(double positionX, double positionY, double width, double height, double monitorHeight) {
        super(positionX, positionY, width, height);
        addStartButton();
        addStartPane(width / 3, monitorHeight * 0.6);
        attachObserver(startPane);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addStartButton() {
        Button windowButton = makeButton(DEFAULT_WINDOW_BUTTON_WIDTH, getHeight());
        getPane().getChildren().add(windowButton);
    }

    private Button makeButton(double width, double height) {
        Button windowButton = new Button();
        windowButton.setMinSize(width, height);
        windowButton.setMaxSize(width, height);
        setWindowButtonImage(windowButton, windowButtonImage);
        addButtonEventHandler(windowButton);

        return windowButton;
    }

    private void setWindowButtonImage(Button button, Image windowButtonImage) {
        BackgroundRepeat noRepeat = BackgroundRepeat.NO_REPEAT;
        BackgroundPosition backgroundPosition = BackgroundPosition.CENTER;
        BackgroundSize backgroundSize = new BackgroundSize(button.getWidth(), button.getHeight(), false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(windowButtonImage, noRepeat, noRepeat, backgroundPosition, backgroundSize);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
    }

    private void addButtonEventHandler(Button button) {
        button.setOnMouseClicked(event -> {
            notifyObservers(EventKind.MENU_START_BUTTON);
        });
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
        setColor(Color.rgb(35, 93, 219));
        graphicContext.fillRect(0, 0, getWidth(), getHeight());
    }

    private void setColor(Color color) {
        graphicContext.setFill(color);
    }

    @Override
    public void update() {
        startPane.update();
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case MENU_START_BUTTON:
                startPane.getPane().setVisible(!startPane.getPane().isVisible());
                break;

            case MONITOR_PANE:
                startPane.getPane().setVisible(false);
                break;

            default:
                Logger.logError(getClass(), "Unknown eventKind");
        }
    }
}
