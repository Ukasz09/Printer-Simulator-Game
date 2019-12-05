package com.github.Ukasz09.graphiceUserInterface.sprites;

import javafx.event.EventHandler;
import javafx.event.EventType;

public interface IEventHandler {

    void addNewEventHandler(EventType eventType, EventHandler eventHandler);

}
