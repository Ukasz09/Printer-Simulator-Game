package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.ContentPane;
import javafx.scene.input.MouseEvent;

public class PrinterDialogWindow extends WindowDialogWithExitTaskbar {

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

    //todo: dac tez reset computer przy exicie ?
    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case EXIT_BUTTON: {
                getPane().setVisible(false);
                notifyObservers(EventKind.EXIT_BUTTON);
            }
            break;
            default:
                Logger.logError(getClass(), "unknown event kind");
        }
    }

    //todo: add tez z contentPana
    private void addPrinterPaneEventHandler() {
        getPane().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(EventKind.PRINTER_PANE));
    }
}
