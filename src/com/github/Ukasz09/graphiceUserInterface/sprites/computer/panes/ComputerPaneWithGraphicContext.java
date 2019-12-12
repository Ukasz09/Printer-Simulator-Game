package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class ComputerPaneWithGraphicContext extends ComputerPane implements IDrawingGraphic {
    protected GraphicsContext graphicContext;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ComputerPaneWithGraphicContext(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        makeGraphicContext(width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Pane makePaneInstance() {
        return new AnchorPane();
    }

    private void makeGraphicContext(double paneWidth, double paneHeight) {
        Canvas canvas = new Canvas(paneWidth, paneHeight);
        graphicContext = canvas.getGraphicsContext2D();
        getPane().getChildren().add(canvas);
    }

    protected void setFillColor(Color color) {
        graphicContext.setFill(color);
    }
}
