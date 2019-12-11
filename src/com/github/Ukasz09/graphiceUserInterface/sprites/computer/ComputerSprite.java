package com.github.Ukasz09.graphiceUserInterface.sprites.computer;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.applicationLogic.computer.Computer;
import com.github.Ukasz09.applicationLogic.observerPattern.IObserver;
import com.github.Ukasz09.applicationLogic.printer.printOption.SepiaColorDecorator;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterException;
import com.github.Ukasz09.graphiceUserInterface.sprites.SpriteWithEventHandler;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.PrinterSprite;
import javafx.scene.image.Image;

//todo: tmp na sprite with handler
public class ComputerSprite extends SpriteWithEventHandler implements IObserver {
    public final static double DEFAULT_MONITOR_WIDTH = 440;
    public final static double DEFAULT_MONITOR_HEIGHT = 320;

    private MonitorSprite monitorSprite;
    private Computer computer;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ComputerSprite(double positionX, double positionY) {
        super(DEFAULT_MONITOR_WIDTH, DEFAULT_MONITOR_HEIGHT, positionX, positionY);
        initializeAllSprites();
        computer = new Computer();

        //todo: tmp
        monitorSprite.getMonitorPane().attachObserver(this);
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
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case SEPIA_BUTTON:
                computer.setPrintDecorator(new SepiaColorDecorator(computer.getPrintDecorator()));
                break;
            default:
                System.out.println("Computer dont know what to do");
        }
    }

    //todo: pomyslec czy nie zrobic inaczej - testowo
    public void print(PrinterSprite printerSprite, Image image) {
        computer.setPrintingOption(true, 1, image);
        try {
            printerSprite.print(computer.getImageToPrint(), computer.isMulticolor(), computer.getQtyOfCopy());
        } catch (PrinterException e) {
            Logger.logError(getClass(), "Powinnien pojawic sie komunikat o tym ze printowanie sie nie udalo. " + e.getMessage());
        }
    }

    //todo: tmp
    public void reset() {
        computer.resetComputer();
    }

}
