package com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;

public interface IPrintOptionObservable {
    void attachPrintObserver(IPrintOptionObserver observer);

    void detachPrintObserver(IPrintOptionObserver observer);

    void notifyPrintObservers(PrintOption printOption);
}
