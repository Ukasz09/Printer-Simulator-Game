package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import javafx.scene.input.MouseEvent;

public class PrinterDialogWindow extends WindowDialogPane {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterDialogWindow(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        addPrinterPaneEventHandler();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected ContentPane makeContentPaneInstance() {
        return new ContentPane(0, 0, getWidth(), getHeight() / 2);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        //todo:
    }

    //todo: add tez z contentPana
    private void addPrinterPaneEventHandler() {
        getPane().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(EventKind.PRINTER_PANE));
    }
}
