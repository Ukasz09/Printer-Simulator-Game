package com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;

public interface IPrintOptionObserver {
    void updateObserver(PrintOption printOption);
}
