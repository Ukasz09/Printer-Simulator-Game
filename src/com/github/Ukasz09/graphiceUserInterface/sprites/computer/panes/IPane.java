package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.observerPattern.IObservable;
import com.github.Ukasz09.applicationLogic.observerPattern.IObserver;
import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.ILayout;
import javafx.scene.layout.Pane;

public interface IPane extends IDrawingGraphic, ILayout, IObservable, IObserver {
    Pane getPane();
}
