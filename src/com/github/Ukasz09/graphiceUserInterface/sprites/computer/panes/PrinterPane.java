package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class PrinterPane extends ComputerPane {
    private static final String BACKGROUND_COLOR = "#303030";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        addPrinterPaneEventHandler();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Pane makePaneInstance() {
        FlowPane pane = new FlowPane();
        setPanelColor(pane);
        return pane;
    }

    private void setPanelColor(Pane pane) {
        pane.setStyle("-fx-background-color: " + BACKGROUND_COLOR);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        //todo:
    }

    @Override
    public void render() {
        //nothing to do
    }

    @Override
    public void update() {
        //todo:
    }

    private void addPrinterPaneEventHandler() {
        getPane().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(EventKind.PRINTER_PANE));
    }
}
