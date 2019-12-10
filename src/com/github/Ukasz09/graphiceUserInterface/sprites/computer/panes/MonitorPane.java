package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class MonitorPane extends ComputerPane {
    private final static Image DEFAULT_WALLPAPER = ImagesProperties.wallpaperImage();

    private Image wallpaper = DEFAULT_WALLPAPER;
    private IPane taskbarPane;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MonitorPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        taskbarPane = new TaskbarPane(positionX, positionY + height - TaskbarPane.DEFAULT_HEIGHT, width, TaskbarPane.DEFAULT_HEIGHT, height);
        attachObserver(taskbarPane);
        taskbarPane.attachObserver(this);
        addMonitorEventHandler();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addMonitorEventHandler() {
        getPane().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(EventKind.MONITOR_PANE));
    }

    @Override
    public void render() {
        taskbarPane.render();
        renderWallpaper();
    }

    private void renderWallpaper() {
        graphicContext.drawImage(wallpaper, 0, 0, getWidth(), getHeight());
    }

    @Override
    public void update() {
        taskbarPane.update();
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case PRINTER_BUTTON:
                System.out.println("Pokazac okno z drukiem");
                break;

            default:
                Logger.logError(getClass(), "Unknown eventKind");
        }
    }
}
