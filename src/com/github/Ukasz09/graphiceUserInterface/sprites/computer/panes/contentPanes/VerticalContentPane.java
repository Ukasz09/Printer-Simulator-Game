package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VerticalContentPane extends ContentPane {
    private static final double DEFAULT_SPACE_BETWEEN_BUTTONS = 10;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public VerticalContentPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Pane makePaneInstance() {
        VBox pane = new VBox(DEFAULT_SPACE_BETWEEN_BUTTONS / 2);
        return pane;
    }
}
