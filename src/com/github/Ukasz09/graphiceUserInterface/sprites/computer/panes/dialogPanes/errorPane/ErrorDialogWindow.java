package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes.ContentPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes.HorizontalContentPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.WindowDialogWithExitTaskbar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public abstract class ErrorDialogWindow extends WindowDialogWithExitTaskbar {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ErrorDialogWindow(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        addPaneEventHandler();
        getPane().setVisible(false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected ContentPane makeContentPaneInstance() {
        return new HorizontalContentPane(0, 0, getWidth(), getHeight() - getWindowTaskbarHeight());
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        if (eventKind == EventKind.EXIT_BUTTON) {
            getPane().setVisible(false);
            notifyObservers(EventKind.EXIT_BUTTON);
        }
    }

    private void addPaneEventHandler() {
        getPane().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(EventKind.ERROR_PANE));
    }

    public abstract void updateErrorMessage(Image errorImage, String errorText);
}
