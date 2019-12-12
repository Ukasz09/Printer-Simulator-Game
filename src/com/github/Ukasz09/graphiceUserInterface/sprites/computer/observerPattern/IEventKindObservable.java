package com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;

public interface IEventKindObservable {
    void attachObserver(IEventKindObserver observer);

    void detachObserver(IEventKindObserver observer);

    void notifyObservers(EventKind eventKind);
}
