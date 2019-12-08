package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public class MonitorPane extends ComputerPane {
    private final static Image DEFAULT_WALLPAPER = ImagesProperties.wallpaperImage();

    private Image wallpaper = DEFAULT_WALLPAPER;
    private IPane taskBarPane;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MonitorPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        taskBarPane = new TaskbarPane(positionX, positionY + height - TaskbarPane.DEFAULT_HEIGHT, width, TaskbarPane.DEFAULT_HEIGHT);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render() {
        renderWallpaper();
        taskBarPane.render();
    }

    private void renderWallpaper() {
        graphicContext.drawImage(wallpaper, 0, 0, getWidth(), getHeight());
    }

    @Override
    public void update() {
        taskBarPane.update();
    }
}
