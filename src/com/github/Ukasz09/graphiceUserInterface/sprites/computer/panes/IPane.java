package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObservable;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.ILayout;
import javafx.scene.layout.Pane;

public interface IPane extends IDrawingGraphic, ILayout, IEventKindObservable, IEventKindObserver {
    Pane getPane();
}
