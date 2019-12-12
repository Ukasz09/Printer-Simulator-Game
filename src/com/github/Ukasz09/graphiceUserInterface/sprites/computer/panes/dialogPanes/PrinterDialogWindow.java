package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes.HorizontalContentPane;
import javafx.scene.input.MouseEvent;

public class PrinterDialogWindow extends WindowDialogWithExitTaskbar {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterDialogWindow(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        addPrinterPaneEventHandler();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected HorizontalContentPane makeContentPaneInstance() {
        return new HorizontalContentPane(0, 0, getWidth(), getHeight() - getWindowTaskbarHeight());
    }

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

    private void addPrinterPaneEventHandler() {
        getPane().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(EventKind.PRINTER_PANE));
    }
}
