package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VerticalContentPane extends ContentPane {
    private static final double SPACE_BETWEEN_BUTTONS_TO_WIDTH_PROPORTION=0.006;//0.00625;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public VerticalContentPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Pane makePaneInstance() {
        VBox pane = new VBox(SPACE_BETWEEN_BUTTONS_TO_WIDTH_PROPORTION*manager.getRightFrameBorder() / 2);
        return pane;
    }
}
