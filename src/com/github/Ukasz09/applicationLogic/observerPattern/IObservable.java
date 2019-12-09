package com.github.Ukasz09.applicationLogic.observerPattern;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;

public interface IObservable {
    void attachObserver(IObserver observer);

    void detachObserver(IObserver observer);

    void notifyObservers(EventKind eventKind);
}
