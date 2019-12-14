package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.ComputerPane;

public abstract class ContentPane extends ComputerPane {

    public ContentPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void updateObserver(EventKind eventKind) {
        //nothing to do
    }

}
