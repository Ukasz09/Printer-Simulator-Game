package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class HorizontalContentPane extends ContentPane {
    private static final double DEFAULT_SPACE_BETWEEN_BUTTONS = 10;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HorizontalContentPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Pane makePaneInstance() {
        HBox pane = new HBox(DEFAULT_SPACE_BETWEEN_BUTTONS / 2);
        pane.setPadding(new Insets(DEFAULT_SPACE_BETWEEN_BUTTONS));
        pane.setAlignment(Pos.CENTER);
        return pane;
    }
}
