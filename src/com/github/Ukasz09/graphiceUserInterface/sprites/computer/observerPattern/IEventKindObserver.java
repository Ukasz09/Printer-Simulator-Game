package com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;

public interface IEventKindObserver {
    void updateObserver(EventKind eventKind);
}
