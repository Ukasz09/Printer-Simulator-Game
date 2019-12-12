package com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;

public interface IPrintOptionObservable {
    void attachObserver(IPrintOptionObserver observer);

    void detachObserver(IPrintOptionObserver observer);

    void notifyObservers(PrintOption printOption);
}
