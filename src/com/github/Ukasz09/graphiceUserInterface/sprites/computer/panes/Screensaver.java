package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.sprites.AnimatedSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObservable;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImageSheetProperty;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.SpritesProperties;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.DecorationState;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;

public class Screensaver extends AnimatedSprite implements IEventKindObservable {
    private static final ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.screenSaverProperty();
    private Set<IEventKindObserver> observers;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Screensaver(double width, double height, double positionX, double positionY) {
        super(width, height, positionX, positionY, DEFAULT_SHEET_PROPERTY, DEFAULT_SHEET_PROPERTY.getAction(DecorationState.STANDBY));
        observers = new HashSet<>();
        setImageViewVisable(false);
        addScreensaverEventHandler();
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

    private void addScreensaverEventHandler() {
        addNewEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            notifyObservers(EventKind.TURN_OF_SLEEPMODE);
        });
    }
}
