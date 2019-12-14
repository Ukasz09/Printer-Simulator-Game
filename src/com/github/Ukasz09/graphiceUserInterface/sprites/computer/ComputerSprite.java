package com.github.Ukasz09.graphiceUserInterface.sprites.computer;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.computer.Computer;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;
import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IPrintOptionObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane.ErrorKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.PrinterSprite;
import javafx.scene.image.Image;

//todo: tmp na sprite with handler
public class ComputerSprite extends SpriteWithEventHandler implements IPrintOptionObserver, IEventKindObserver {
    public final static double MONITOR_WIDTH_TO_FRAME_PROPORTION = 44.0/160;
    public final static double MONITOR_HEIGHT_TO_FRAME_PROPORTION = 32.0/90;

    private MonitorSprite monitorSprite;
    private PrinterSprite printerSprite;

    private Computer computer;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ComputerSprite(double positionX, double positionY, PrinterSprite printerSprite, Image actualImageToPrint) {
        super(MONITOR_WIDTH_TO_FRAME_PROPORTION * ViewManager.getInstance().getRightFrameBorder(), MONITOR_HEIGHT_TO_FRAME_PROPORTION * ViewManager.getInstance().getBottomFrameBorder(), positionX, positionY);
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
        monitorSprite.setImageViewVisible(false);
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
            case FULL_AVAILABLE_PAPER_STACK:
                monitorSprite.getMonitorPane().showPrintErrorMessage(ErrorKind.FULL_AVAILABLE_PAPER_STACK);
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
