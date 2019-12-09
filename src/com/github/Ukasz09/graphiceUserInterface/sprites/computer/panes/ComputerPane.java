package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.observerPattern.IObservable;
import com.github.Ukasz09.applicationLogic.observerPattern.IObserver;
import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Set;

public abstract class ComputerPane implements IPane, IObservable {
    protected GraphicsContext graphicContext;
    private final ViewManager manager;
    private Pane pane;

    private Set<IObserver> observers;
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
        observers = new HashSet<>();
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
    public Pane getPane() {
        return pane;
    }

    @Override
    public void attachObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(EventKind eventKind) {
        for (IObserver observer : observers)
            observer.updateObserver(eventKind);
    }

    //todo: moze usunac pozniej
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
