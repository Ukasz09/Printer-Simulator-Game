package com.github.Ukasz09.applicationLogic.observerPattern;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;

public interface IObserver {
    void updateObserver(EventKind eventKind);
}
