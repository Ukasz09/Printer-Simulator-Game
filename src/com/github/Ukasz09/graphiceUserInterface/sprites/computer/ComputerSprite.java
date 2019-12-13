package com.github.Ukasz09.graphiceUserInterface.sprites.computer;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.computer.Computer;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterException;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IPrintOptionObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane.ErrorKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.PrinterSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

//todo: tmp na sprite with handler
public class ComputerSprite extends SpriteWithEventHandler implements IPrintOptionObserver, IEventKindObserver {
    public final static double DEFAULT_MONITOR_WIDTH = 440;
    public final static double DEFAULT_MONITOR_HEIGHT = 320;

    private MonitorSprite monitorSprite;
    private PrinterSprite printerSprite;

    private Computer computer;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ComputerSprite(double positionX, double positionY, PrinterSprite printerSprite, Image actualImageToPrint) {
        super(DEFAULT_MONITOR_WIDTH, DEFAULT_MONITOR_HEIGHT, positionX, positionY);
        initializeAllSprites();
        computer = new Computer(actualImageToPrint);
        this.printerSprite = printerSprite;
        monitorSprite.getMonitorPane().attachPrintObserver(this);
        monitorSprite.getMonitorPane().getPrinterPane().attachObserver(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initializeAllSprites() {
        initializeComputerBody();
    }

    private void initializeComputerBody() {
        monitorSprite = new MonitorSprite(width, height, positionX, positionY);
        monitorSprite.setImageViewVisable(false);
    }

    @Override
    public void render() {
        monitorSprite.render();
    }

    @Override
    public void update() {
        monitorSprite.update();
    }

    @Override
    public void updateObserver(PrintOption printOption) {
        computer.setPrintDecorator(printOption.setOptionDecorator(computer.getPrintDecorator()));
        print(printOption.multicolor, 1);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case EXIT_BUTTON:
                computer.resetPrintProperty();
                break;
            default:
                Logger.logError(getClass(), "unknown event");
        }
    }

    public void updatePrintImage(Image actualImage) {
        computer.setImageToPrint(actualImage);
    }

    private void print(boolean multicolor, int qtyOfCopy) {
        ErrorKind printerError = printerSprite.print(computer.getImageToPrint(), multicolor, qtyOfCopy);
        if (printerError != ErrorKind.NO_ERRORS)
            monitorSprite.getMonitorPane().showPrintErrorMessage(printerError);
        computer.resetPrintProperty();
    }
}
