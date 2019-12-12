package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class ContentPane extends ComputerPane {
    private static final double DEFAULT_SPACE_BETWEEN_BUTTONS = 10;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ContentPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Pane makePaneInstance() {
        HBox pane = new HBox(DEFAULT_SPACE_BETWEEN_BUTTONS / 2);
        pane.setPadding(new Insets(DEFAULT_SPACE_BETWEEN_BUTTONS));
//        setPanelColor(pane, "red");
        return pane;
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        //todo:
    }
}
