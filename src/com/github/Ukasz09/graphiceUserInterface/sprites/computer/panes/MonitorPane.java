package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IPrintOptionObservable;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IPrintOptionObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;

public class MonitorPane extends ComputerPaneWithGraphicContext implements IPrintOptionObservable {
    private final static Image DEFAULT_WALLPAPER = ImagesProperties.wallpaperImage();
    private static final double OTHER_PANE_TO_MONITOR_PANE_PROPORTION = 0.75;
    private static final double DEFAULT_BUTTON_SIZE = 75;

    private Image wallpaper = DEFAULT_WALLPAPER;
    private IPane taskbarPane;
    private IPane printerPane;
    private Set<IPrintOptionObserver> printOptionObservers;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MonitorPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        printOptionObservers = new HashSet<>();
        makePrinterPane(positionX, positionY, width, height);
        taskbarPane = new TaskbarPane(positionX, positionY + height - TaskbarPane.DEFAULT_HEIGHT, width, TaskbarPane.DEFAULT_HEIGHT, height);
        attachObserver(taskbarPane);
        taskbarPane.attachObserver(this);
        printerPane.attachObserver(taskbarPane);
        addMonitorEventHandler();

        addDefaultPrintingWays();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void makePrinterPane(double monitorX, double monitorY, double monitorWidth, double monitorHeight) {
        double printerPaneWidth = monitorWidth * OTHER_PANE_TO_MONITOR_PANE_PROPORTION;
        double printerPaneHeight = monitorHeight * OTHER_PANE_TO_MONITOR_PANE_PROPORTION;
        Point2D printerPanePosition = calculatePrinterPanePosition(monitorX, monitorY, monitorWidth, monitorHeight, printerPaneWidth, printerPaneHeight);
        printerPane = new PrinterPane(printerPanePosition.getX(), printerPanePosition.getY(), printerPaneWidth, printerPaneHeight);
        printerPane.getPane().setVisible(false);
    }

    //todo: tmp
    private void addDefaultPrintingWays() {
        addPrintingWayButton(ImagesProperties.printerIconImage(), "Sepia", PrintOption.SEPIA );
        addPrintingWayButton(ImagesProperties.printerIconImage(), "Czarno-bialy", PrintOption.BLACK_AND_WHITE);
    }

    private void addPrintingWayButton(Image buttonImage, String buttonText, PrintOption printOption) {
        Button printingWayButton;
        if (!buttonText.isEmpty())
            printingWayButton = makeButtonWithImageAndText(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE, buttonImage, buttonText);
        else
            printingWayButton = makeButtonWithImage(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE, buttonImage);

        printingWayButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(printOption));
        addPrintingWayButton(printingWayButton);
    }

    public void addPrintingWayButton(Button button) {
        System.out.println("Button posY" + button.getLayoutY());
        printerPane.getPane().getChildren().add(button);
    }

    private Point2D calculatePrinterPanePosition(
            double monitorPaneX, double monitorPaneY, double monitorWidth, double monitorHeight, double paneWidth, double paneHeight) {
        double positionX = monitorPaneX + monitorWidth / 2 - paneWidth / 2;
        double positionY = monitorPaneY + monitorHeight / 2 - paneHeight / 2;
        return new Point2D(positionX, positionY);
    }

    private void addMonitorEventHandler() {
        getPane().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(EventKind.MONITOR_PANE));
    }

    @Override
    public void render() {
        printerPane.render();
        taskbarPane.render();
        renderWallpaper();
    }

    private void renderWallpaper() {
        graphicContext.drawImage(wallpaper, 0, 0, getWidth(), getHeight());
    }

    @Override
    public void update() {
        taskbarPane.update();
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case PRINTER_BUTTON:
                printerPane.getPane().setVisible(true);
                break;
//
//            case PRINTER_PANE:
//                System.out.println("Powinnien zniknac StartPane");
//                break;

            default:
                Logger.logError(getClass(), "Unknown eventKind");
        }
    }

    @Override
    public void attachObserver(IPrintOptionObserver observer) {
        printOptionObservers.add(observer);
    }

    @Override
    public void detachObserver(IPrintOptionObserver observer) {
        printOptionObservers.remove(observer);
    }

    @Override
    public void notifyObservers(PrintOption printOption) {
        for (IPrintOptionObserver observer : printOptionObservers)
            observer.updateObserver(printOption);
    }
}
