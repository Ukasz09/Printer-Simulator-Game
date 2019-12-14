package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.HashSet;
import java.util.Set;

public abstract class ComputerPane implements IPane {
    protected final ViewManager manager;
    private Pane pane;

    private Set<IEventKindObserver> observers;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ComputerPane(double positionX, double positionY, double width, double height) {
        manager = ViewManager.getInstance();
        setPosition(positionX, positionY);
        setSize(width, height);
        makePane();
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

    /**
     * Use only to create instance of Pane. This method is using to choose a layouts for pane
     */
    protected abstract Pane makePaneInstance();

    private void makePane() {
        pane = makePaneInstance();
        if (pane == null) {
            pane = new AnchorPane();
            Logger.logError(getClass(), " pane from makePaneInstance is null. Made pane by AnchorPane");
        }
        setPaneProperties();
    }

    private void setPaneProperties() {
        pane.setPrefSize(width, height);
        pane.setLayoutX(positionX);
        pane.setLayoutY(positionY);
    }

    public void setPanelColor(String hexColorWithHash) {
        setPanelColor(pane, hexColorWithHash);
    }

    protected void setPanelColor(Pane pane, String hexColorWithHash) {
        pane.setStyle("-fx-background-color: " + hexColorWithHash);
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void attachObserver(IEventKindObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(IEventKindObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(EventKind eventKind) {
        for (IEventKindObserver observer : observers)
            observer.updateObserver(eventKind);
    }

    protected Button makeButtonWithBackgroundAndEventHandler(double width, double height, Image buttonImage, EventKind eventKind) {
        Button button = makeButtonWithBackground(width, height, buttonImage);
        addButtonEventHandler(button, eventKind);
        return button;
    }

    protected Button makeButtonWithBackground(double width, double height, Image buttonImage) {
        Button button = new Button();
        setPropertyForButtonWithBackground(button, width, height, buttonImage);
        return button;
    }

    private void setPropertyForButtonWithBackground(Button button, double width, double height, Image buttonImage) {
        setNormalButtonProperty(button, width, height);
        setBackgroundToButton(button, buttonImage);
    }

    private void setNormalButtonProperty(Button windowButton, double width, double height) {
        windowButton.setMinSize(width, height);
        windowButton.setMaxSize(width, height);
    }

    private void addButtonEventHandler(Button button, EventKind eventKind) {
        button.setOnMouseClicked(event -> {
            notifyObservers(eventKind);
        });
    }

    private void setBackgroundToButton(Button button, Image buttonImage) {
        BackgroundRepeat noRepeat = BackgroundRepeat.NO_REPEAT;
        BackgroundPosition backgroundPosition = BackgroundPosition.CENTER;
        BackgroundSize backgroundSize = new BackgroundSize(button.getWidth(), button.getHeight(), false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(buttonImage, noRepeat, noRepeat, backgroundPosition, backgroundSize);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
    }

    protected Button makeButtonWithImageAndEventHandler(double width, double height, Image buttonImage, EventKind eventKind) {
        Button button = makeButtonWithImage(width, height, buttonImage);
        addButtonEventHandler(button, eventKind);
        return button;
    }

    protected Button makeButtonWithImage(double width, double height, Image buttonImage) {
        Button button = new Button("", getImageViewForButton(width, height, buttonImage));
        setNormalButtonProperty(button, width, height);
        return button;
    }

    private ImageView getImageViewForButton(double width, double height, Image buttonImage) {
        ImageView imageView = new ImageView(buttonImage);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    protected Button makeButtonWithImageTextAndEventHandler
            (double widthOfImageInButton, double heightOfImageInButton, double widthOfButton, double heightOfButton, Image buttonImage, String buttonText, EventKind eventKind, Pos alignment) {
        Button button = makeButtonWithImageAndText(widthOfImageInButton, heightOfImageInButton, widthOfButton, heightOfButton, buttonImage, buttonText, alignment);
        addButtonEventHandler(button, eventKind);
        return button;
    }

    protected Button makeButtonWithImageAndText(double widthOfImageInButton, double heightOfImageInButton, double widthOfButton, double heightOfButton, Image buttonImage, String buttonText, Pos alignment) {
        Button printerButton = new Button(buttonText);
        printerButton.setGraphic(getImageViewForButton(widthOfImageInButton, heightOfImageInButton, buttonImage));
        printerButton.setAlignment(alignment);
        setNormalButtonProperty(printerButton, widthOfButton, heightOfButton);

        printerButton.setStyle(String.format("-fx-font-size: %dpx;", (int) (printerButton.getMaxWidth() / 11)));
        return printerButton;
    }

    public void addNodeToPane(Node node) {
        pane.getChildren().add(node);
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