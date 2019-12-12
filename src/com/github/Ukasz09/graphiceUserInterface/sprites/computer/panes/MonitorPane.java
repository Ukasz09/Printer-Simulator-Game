package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.BasePrintDecorator;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IPrintOptionObservable;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IPrintOptionObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.StartTaskbar;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;

public class MonitorPane extends ComputerPaneWithGraphicContext implements IPrintOptionObservable {
    private final static Image DEFAULT_WALLPAPER = ImagesProperties.wallpaperImage();
    private static final double OTHER_PANE_TO_MONITOR_PANE_PROPORTION = 0.75;
    private static final double DEFAULT_BUTTON_SIZE = 67;

    private Image wallpaper = DEFAULT_WALLPAPER;
    private StartTaskbar taskbarPane;
    private DialogPane printerPane; //todo: dac pozniej na interfejsach
    private Set<IPrintOptionObserver> printOptionObservers;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MonitorPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        printOptionObservers = new HashSet<>();
        makePrinterPane(positionX, positionY, width, height);
        taskbarPane = new StartTaskbar(positionX, positionY + height - StartTaskbar.DEFAULT_WINDOWS_BUTTON_HEIGHT, width, StartTaskbar.DEFAULT_WINDOWS_BUTTON_HEIGHT, height);
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
        printerPane = new PrinterDialogWindow(printerPanePosition.getX(), printerPanePosition.getY(), printerPaneWidth, printerPaneHeight);
        printerPane.getPane().setVisible(false);
    }

    //todo: tmp
    private void addDefaultPrintingWays() {
        Image thumbnailImage = ImagesProperties.thumbnailImage();
        addPrintingWayButton(thumbnailImage, "Sepia", PrintOption.SEPIA);
        addPrintingWayButton(thumbnailImage, "Czarno-bialy", PrintOption.BLACK_AND_WHITE);
        addPrintingWayButton(thumbnailImage, "Sepia", PrintOption.SEPIA);
        addPrintingWayButton(thumbnailImage, "Czarno-bialy", PrintOption.BLACK_AND_WHITE);
    }


    public void addPrintingWayButton(Image imageWithoutEffects, String buttonText, PrintOption printOption) {
        Button printingWayButton;
        if (!buttonText.isEmpty())
            printingWayButton = makeButtonWithImageAndText(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE, getImageWithEffect(printOption, imageWithoutEffects), buttonText);
        else
            printingWayButton = makeButtonWithImage(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE, getImageWithEffect(printOption, imageWithoutEffects));

        printingWayButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> notifyObservers(printOption));
        printerPane.addButtonToContentPane(printingWayButton);
    }

    private Image getImageWithEffect(PrintOption printEffect, Image image) {
        return printEffect.setOptionDecorator(new BasePrintDecorator()).getImageWithAddedEffect(new ImageView(image));
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
//        taskbarPane.render();
        renderWallpaper();
    }

    private void renderWallpaper() {
        graphicContext.drawImage(wallpaper, 0, 0, getWidth(), getHeight());
    }

    @Override
    public void update() {
//        taskbarPane.update();
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
