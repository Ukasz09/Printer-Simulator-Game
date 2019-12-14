package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class HorizontalContentPane extends ContentPane {
    private static final double SPACE_BETWEEN_BUTTONS_TO_WIDTH_PROPORTION=0.006;//00625;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HorizontalContentPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Pane makePaneInstance() {
        HBox pane = new HBox(SPACE_BETWEEN_BUTTONS_TO_WIDTH_PROPORTION * manager.getRightFrameBorder() / 2);
        pane.setPadding(new Insets(SPACE_BETWEEN_BUTTONS_TO_WIDTH_PROPORTION));
        pane.setAlignment(Pos.CENTER);
        return pane;
    }
}
