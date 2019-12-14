package com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObservable;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class ZingsPosterSprite extends ImageSprite implements IEventKindObservable {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static double WIDTH_TO_FRAME_PROPORTION = 28.0 / 160;
    private final static double HEIGHT_TO_FRAME_PROPORTION = 35.0 / 90;
    public final static double FRAME_THICKNESS_TO_FRAME_WIDTH_PROPORTION = 1.0 / 160;

    private static final Image[] zingsImages = ImagesProperties.zingsPosterSprites();
    private Set<IEventKindObserver> observers;

    private List<Integer> indexes;
    private int actualOffsetOfIndexesList;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ZingsPosterSprite(double positionX, double positionY) {
        super(WIDTH_TO_FRAME_PROPORTION * ViewManager.getInstance().getRightFrameBorder(), HEIGHT_TO_FRAME_PROPORTION * ViewManager.getInstance().getBottomFrameBorder(), zingsImages[getRandomIndex()], positionX, positionY);
        observers = new HashSet<>();
        indexes = new ArrayList<>();
        actualOffsetOfIndexesList = 0;
        addNumbersToIndexed();
        addPosterEventHandler();
    }

    private static int getRandomIndex(){
        return (int) (Math.random() * zingsImages.length);
    }

    private void addNumbersToIndexed() {
        for (int i = 0; i < zingsImages.length; i++)
            indexes.add(i);
        shuffleIndexes();
    }

    private void shuffleIndexes() {
        Collections.shuffle(indexes);
    }


    private void addPosterEventHandler() {
        addNewEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setRandomZingsImage();
            notifyObservers(EventKind.POSTER_CHANGE);
        });
    }

    private void setRandomZingsImage() {
        setSpriteImage(zingsImages[getNextIndex()]);
    }

    private int getNextIndex() {
        actualOffsetOfIndexesList++;
        int maxIndex = zingsImages.length - 1;
        if (actualOffsetOfIndexesList > maxIndex) {
            shuffleIndexes();
            actualOffsetOfIndexesList = 0;
        }
        return indexes.get(actualOffsetOfIndexesList);
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
