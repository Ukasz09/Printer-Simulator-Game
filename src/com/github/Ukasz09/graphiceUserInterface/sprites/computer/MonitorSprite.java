package com.github.Ukasz09.graphiceUserInterface.sprites.computer;

import com.github.Ukasz09.IMonitor;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.MonitorPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.Screensaver;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane.ErrorKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class MonitorSprite extends ImageSprite implements IEventKindObserver, IMonitor {
    private final static Image DEFAULT_IMAGE = ImagesProperties.monitorSprite();
    private final static double FRAME_THICKNESS_TO_FRAME_WIDTH_PROPORTION = 15.0 / 1600;
    private final static double DEFAULT_DISPLAY_TO_MONITOR_PROPORTION = 0.68;
    private static final double SCREENSAVER_COOLDOWN = 300;

    private final double frameThickness;
    private final double displayToMonitorProportion;

    private MonitorPane monitorPane;
    private Screensaver screenSaver;
    private double screensaverCooldown;
    private boolean sleepModeWasActivated;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MonitorSprite(double width, double height, double positionX, double positionY) {
        super(width, height, DEFAULT_IMAGE, positionX, positionY);
        frameThickness = getWidthAfterScaling(FRAME_THICKNESS_TO_FRAME_WIDTH_PROPORTION);
        displayToMonitorProportion = DEFAULT_DISPLAY_TO_MONITOR_PROPORTION;
        initializeMonitorPane();
        addScreensaver();
        monitorPane.attachObserver(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initializeMonitorPane() {
        Point2D panePosition = calculateDisplayPanePosition();
        Point2D paneSize = calculateDisplayPaneSize();
        monitorPane = new MonitorPane(panePosition.getX(), panePosition.getY(), paneSize.getX(), paneSize.getY());
    }

    private Point2D calculateDisplayPaneSize() {
        double paneSizeX = width - frameThickness * 2;
        double paneSizeY = height * displayToMonitorProportion;
        return new Point2D(paneSizeX, paneSizeY);
    }

    private Point2D calculateDisplayPanePosition() {
        double panePositionX = positionX + frameThickness;
        double panePositionY = positionY + frameThickness;
        return new Point2D(panePositionX, panePositionY);
    }

    private void addScreensaver() {
        screenSaver = new Screensaver(monitorPane.getWidth(), monitorPane.getHeight(), monitorPane.getPositionX(), monitorPane.getPositionY());
        turnOnScreensaver();
        sleepModeWasActivated = true;
        screenSaver.attachObserver(this);
        screensaverCooldown = SCREENSAVER_COOLDOWN;
    }

    @Override
    public void render() {
        super.render();
        monitorPane.render();
        screenSaver.render();
    }

    @Override
    public void update() {
        super.update();
        monitorPane.update();
        screenSaver.update();
        updateScreensaver();
    }

    private void updateScreensaver() {
        if (canTurnOnScreensaver())
            turnOnScreensaver();
        else if (sleepModeWasActivated)
            reduceScreensaverCooldown();
        else {
            turnOffScreensaver();
            restoreScreensaverCooldown();
        }
    }

    private void turnOnScreensaver() {
        monitorPane.getPane().setVisible(false);
        screenSaver.setImageViewVisible(true);
    }

    private void turnOffScreensaver() {
        monitorPane.getPane().setVisible(true);
        screenSaver.setImageViewVisible(false);
    }

    private boolean canTurnOnScreensaver() {
        return (screensaverCooldown <= 0 && sleepModeWasActivated);
    }

    private void reduceScreensaverCooldown() {
        screensaverCooldown -= 1;
        if (screensaverCooldown < 0)
            screensaverCooldown = 0;
    }

    private void restoreScreensaverCooldown() {
        screensaverCooldown = SCREENSAVER_COOLDOWN;
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case TURN_OF_SLEEPMODE:
                sleepModeWasActivated = false;
                break;
            case TURN_ON_SLEEPMODE:
                sleepModeWasActivated = true;
                break;
        }
    }

    public MonitorPane getMonitorPane() {
        return monitorPane;
    }

    @Override
    public void showPrintErrorMessage(ErrorKind errorKind) {
        monitorPane.showPrintErrorMessage(errorKind);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
