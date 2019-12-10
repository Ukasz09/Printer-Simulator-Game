package com.github.Ukasz09.graphiceUserInterface.sprites.computer;

import com.github.Ukasz09.applicationLogic.computer.Computer;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

//todo: tmp na sprite with handler
public class ComputerSprite extends SpriteWithEventHandler {
    public final static double DEFAULT_MONITOR_WIDTH = 440;
    public final static double DEFAULT_MONITOR_HEIGHT = 320;

    private MonitorSprite monitorSprite;
    private Computer computer;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ComputerSprite(double positionX, double positionY) {
        super(DEFAULT_MONITOR_WIDTH, DEFAULT_MONITOR_HEIGHT, positionX, positionY);
        initializeAllSprites();
        computer = new Computer();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initializeAllSprites() {
        initializeComputerBody();
    }

    private void initializeComputerBody() {
        monitorSprite = new MonitorSprite(width, height, positionX, positionY);
        monitorSprite.setImageViewVisable(false);
    }

    @Override
    public void render() {
        monitorSprite.render();
    }
}
