package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.ILayout;
import javafx.scene.Node;

public interface IPane extends ILayout, IDrawingGraphic {
     void addNodeToPane(Node node);
}
