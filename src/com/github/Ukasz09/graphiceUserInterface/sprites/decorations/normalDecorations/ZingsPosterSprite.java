package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations;

import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObservable;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;

public class ZingsPosterSprite extends ImageSprite implements IEventKindObservable {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public final static double DEFAULT_WIDTH = 280;
    public final static double DEFAULT_HEIGHT = 350;
    public final static double DEFAULT_FRAME_THICKNESS = 10;

    private static final Image[] zingsImages = ImagesProperties.zingsPosterSprites();
    private Set<IEventKindObserver> observers;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ZingsPosterSprite(double positionX, double positionY) {
        super(zingsImages[getRandomIndex()], DEFAULT_WIDTH, DEFAULT_HEIGHT, positionX, positionY);
        observers = new HashSet<>();
        addPosterEventHandler();
    }

    private void addPosterEventHandler() {
        addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setRandomZingsImage();
            notifyObservers(EventKind.POSTER_CHANGE);
        });
    }

    private void setRandomZingsImage() {
        setSpriteImage(zingsImages[getRandomIndex()]);
    }

    private static int getRandomIndex() {
        return (int) (Math.random() * zingsImages.length);
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

}
