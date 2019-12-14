package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.ComputerPaneWithGraphicContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class Taskbar extends ComputerPaneWithGraphicContext {
    private static final String taskbarHexColor = "#235ddb";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Taskbar(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        setPanelColor(taskbarHexColor);
    }

    @Override
    protected Pane makePaneInstance() {
        return new AnchorPane();
    }

    @Override
    public void render() {
        //nothing to do
    }

    @Override
    public void update() {
        //nothing to do
    }
}
