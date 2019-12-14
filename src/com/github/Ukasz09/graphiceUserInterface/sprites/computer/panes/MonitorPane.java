package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.BasePrintDecorator;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IPrintOptionObservable;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IPrintOptionObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane.ErrorDialogWindow;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane.ErrorKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane.PrintErrorDialogWindow;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.WindowDialog;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.PrinterDialogWindow;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.StartTaskbar;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;

public class MonitorPane extends ComputerPaneWithGraphicContext implements IPrintOptionObservable {
    private final static Image[] DEFAULT_WALLPAPERS = ImagesProperties.wallpaperImages();
    private static final double OTHER_PANE_TO_MONITOR_PANE_WIDTH_PROPORTION = 0.78;
    private static final double OTHER_PANE_TO_MONITOR_PANE_HEIGHT_PROPORTION = 0.65;
    private static final double DEFAULT_BUTTON_WIDTH_TO_MONITOR_PROPORTION = 0.22;
    private static final double DEFAULT_BUTTON_HEIGHT_TO_MONITOR_PROPORTION = 0.5;

    private Image wallpaper = DEFAULT_WALLPAPERS[0];
    private StartTaskbar taskbarPane;
    private WindowDialog printerPane; //todo: dac pozniej na interfejsach
    private ErrorDialogWindow printErrorPane;
    private Set<IPrintOptionObserver> printOptionObservers;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MonitorPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        printOptionObservers = new HashSet<>();
        makePrinterPane(0, 0, width, height);
        printErrorPane = new PrintErrorDialogWindow(printerPane.getPositionX(), printerPane.getPositionY(), printerPane.getWidth(), printerPane.getHeight());
        taskbarPane = new StartTaskbar(0, 0 + height - StartTaskbar.DEFAULT_WINDOWS_BUTTON_HEIGHT*manager.getBottomFrameBorder(), width, StartTaskbar.DEFAULT_WINDOWS_BUTTON_HEIGHT*manager.getBottomFrameBorder(), height);
        attachObserver(taskbarPane);
        taskbarPane.attachObserver(this);
        printerPane.attachObserver(taskbarPane);
        printErrorPane.attachObserver(taskbarPane);
        addMonitorEventHandler();
        getPane().getChildren().addAll(taskbarPane.getPane(), printerPane.getPane(), printErrorPane.getPane());
        addDefaultPrintingWays();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void makePrinterPane(double monitorX, double monitorY, double monitorWidth, double monitorHeight) {
        double printerPaneWidth = monitorWidth * OTHER_PANE_TO_MONITOR_PANE_WIDTH_PROPORTION;
        double printerPaneHeight = monitorHeight * OTHER_PANE_TO_MONITOR_PANE_HEIGHT_PROPORTION;
        Point2D printerPanePosition = calculatePrinterPanePosition(monitorX, monitorY, monitorWidth, monitorHeight, printerPaneWidth, printerPaneHeight);
        printerPane = new PrinterDialogWindow(printerPanePosition.getX(), printerPanePosition.getY(), printerPaneWidth, printerPaneHeight);
        printerPane.getPane().setVisible(false);
    }

    //todo: tmp
    private void addDefaultPrintingWays() {
        Image thumbnailImage = ImagesProperties.thumbnailImage();
        addPrintingWayButton(thumbnailImage, "Sepia", PrintOption.SEPIA);
        addPrintingWayButton(thumbnailImage, "B-W", PrintOption.BLACK_AND_WHITE);
        addPrintingWayButton(thumbnailImage, "Barwiony", PrintOption.RAND_HUE);
        addPrintingWayButton(thumbnailImage, "Normalny", PrintOption.NORMAL);
    }

    private void addPrintingWayButton(Image imageWithoutEffects, String buttonText, PrintOption printOption) {
        Button printingWayButton = makePrintButton(imageWithoutEffects, buttonText, printOption);
        printingWayButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getPrintButtonEventHandler(printOption));
        printerPane.addNodeToContentPane(printingWayButton);
    }

    private EventHandler getPrintButtonEventHandler(PrintOption printOption) {
        return event -> {
            notifyPrintObservers(printOption);
            notifyObservers(EventKind.EXIT_BUTTON);
            printerPane.getPane().setVisible(false);
        };
    }

    private Button makePrintButton(Image imageWithoutEffects, String buttonText, PrintOption printOption) {
        Point2D btnSize = getPrintButtonSize();
        Point2D btnImageSize = getPrintButtonImageSize(btnSize);
        Pos contentPos = Pos.TOP_CENTER;
        Image btnImage = getImageWithEffect(printOption, imageWithoutEffects);
        Button button = makeButtonWithImageAndText(btnImageSize.getX(), btnImageSize.getY(), btnSize.getX(), btnSize.getY(), btnImage, buttonText, contentPos);
        button.setContentDisplay(ContentDisplay.TOP);
        return button;
    }

    private Point2D getPrintButtonSize() {
        double buttonWidth = printerPane.getWidth() * DEFAULT_BUTTON_WIDTH_TO_MONITOR_PROPORTION;
        double buttonHeight = printerPane.getHeight() * DEFAULT_BUTTON_HEIGHT_TO_MONITOR_PROPORTION;
        return new Point2D(buttonWidth, buttonHeight);
    }

    private Point2D getPrintButtonImageSize(Point2D buttonSize) {
        double imageWidth = buttonSize.getX() * 0.6;
        double imageHeight = buttonSize.getY() * 0.6;
        return new Point2D(imageWidth, imageHeight);
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
        getPane().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> notifyObservers(EventKind.TURN_OF_SLEEPMODE));
        getPane().addEventHandler(MouseEvent.MOUSE_EXITED, event -> notifyObservers(EventKind.TURN_ON_SLEEPMODE));
    }

    @Override
    public void render() {
        renderWallpaper();
        taskbarPane.render();
        printerPane.render();
        printErrorPane.render();
    }

    private void renderWallpaper() {
        graphicContext.drawImage(wallpaper, 0, 0, getWidth(), getHeight());
    }

    @Override
    public void update() {
        taskbarPane.update();
        printerPane.update();
        printErrorPane.update();
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case PRINTER_BUTTON:
                printerPane.getPane().setVisible(true);
                break;
            case WALLPAPER_CHANGE:
                setRandomWallpaper();
                break;
            default:
                Logger.logError(getClass(), "Unknown eventKind: " + eventKind.toString());
        }
    }

    private void setRandomWallpaper() {
        int randIndex = (int) (Math.random() * DEFAULT_WALLPAPERS.length);
        wallpaper = DEFAULT_WALLPAPERS[randIndex];
    }

    @Override
    public void attachPrintObserver(IPrintOptionObserver observer) {
        printOptionObservers.add(observer);
    }

    @Override
    public void detachPrintObserver(IPrintOptionObserver observer) {
        printOptionObservers.remove(observer);
    }

    @Override
    public void notifyPrintObservers(PrintOption printOption) {
        for (IPrintOptionObserver observer : printOptionObservers)
            observer.updateObserver(printOption);
    }

    public void showPrintErrorMessage(ErrorKind errorKind) {
        printErrorPane.updateErrorMessage(errorKind.errorImage, errorKind.errorText);
        printErrorPane.getPane().setVisible(true);
    }


    public WindowDialog getPrinterPane() {
        return printerPane;
    }
}
