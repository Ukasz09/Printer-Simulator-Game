package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
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
    private final static double WIDTH_TO_FRAME_PROPORTION = 0.175;//28.0 / 160;
    private final static double HEIGHT_TO_FRAME_PROPORTION =0.389;// 35.0 / 90;
    public final static double FRAME_THICKNESS_TO_FRAME_WIDTH_PROPORTION = 0.006;//0.00625;///1.0 / 160;

    private static final Image[] zingsImages = ImagesProperties.zingsPosterSprites();
    private Set<IEventKindObserver> observers;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ZingsPosterSprite(double positionX, double positionY) {
        super(WIDTH_TO_FRAME_PROPORTION * ViewManager.getInstance().getRightFrameBorder(), HEIGHT_TO_FRAME_PROPORTION * ViewManager.getInstance().getBottomFrameBorder(),zingsImages[getRandomIndex()], positionX, positionY);
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
