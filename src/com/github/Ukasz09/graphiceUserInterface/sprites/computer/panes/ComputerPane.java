package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class ComputerPane implements IPane {
    protected GraphicsContext graphicContext;
    private final ViewManager manager;
    private Pane pane;

    private double positionX;
    private double positionY;
    private double width;
    private double height;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ComputerPane(double positionX, double positionY, double width, double height) {
        manager = ViewManager.getInstance();
        setPosition(positionX, positionY);
        setSize(width, height);
        makeMonitorPane();
        makeGraphicContext(width, height);
        manager.addNode(pane);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    private void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    private void makeMonitorPane() {
        pane = new AnchorPane();
        setPaneProperties();
    }

    private void setPaneProperties() {
        pane.setPrefSize(width, height);
//        pane.setStyle("-fx-background-color: red;");
        pane.setLayoutX(positionX);
        pane.setLayoutY(positionY);
    }

    private void makeGraphicContext(double paneWidth, double paneHeight) {
        Canvas canvas = new Canvas(paneWidth, paneHeight);
        graphicContext = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);
    }

    @Override
    public void addNodeToPane(Node node) {
        pane.getChildren().add(node);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public double getPositionX() {
        return positionX;
    }

    @Override
    public double getPositionY() {
        return positionY;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }
}
