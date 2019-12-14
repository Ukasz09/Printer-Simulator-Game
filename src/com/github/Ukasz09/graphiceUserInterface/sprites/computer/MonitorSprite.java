package com.github.Ukasz09.graphiceUserInterface.sprites.computer;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.ImageSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.MonitorPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.Screensaver;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class MonitorSprite extends ImageSprite implements IEventKindObserver {
    private final static Image DEFAULT_IMAGE = ImagesProperties.monitorSprite();
    private final static double DEFAULT_MONITOR_FRAME_THICKNESS = 15;
    private final static double DEFAULT_DISPLAY_TO_MONITOR_PROPORTION = 0.68;
    private static final double SCREENSAVER_COOLDOWN = 200;

    private final double frameThickness;
    private final double displayToMonitorProportion;

    private MonitorPane monitorPane;
    private Screensaver screenSaver;
    private double screensaverCooldown;
    private boolean sleepModeWasActivated;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MonitorSprite(double width, double height, double positionX, double positionY) {
        super(DEFAULT_IMAGE, width, height, positionX, positionY);
        frameThickness = DEFAULT_MONITOR_FRAME_THICKNESS;
        displayToMonitorProportion = DEFAULT_DISPLAY_TO_MONITOR_PROPORTION;
        initializeMonitorPane();

        //todo: test
        screenSaver = new Screensaver(monitorPane.getWidth(), monitorPane.getHeight(), monitorPane.getPositionX(), monitorPane.getPositionY());
        screenSaver.attachObserver(this);
        monitorPane.getPane().setVisible(false);
        screenSaver.setImageViewVisable(true);

        screensaverCooldown = SCREENSAVER_COOLDOWN;
        monitorPane.attachObserver(this);
        sleepModeWasActivated = true;
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
        screenSaver.setImageViewVisable(true);
    }

    private void turnOffScreensaver() {
        monitorPane.getPane().setVisible(true);
        screenSaver.setImageViewVisable(false);
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
            //todo: tmp
            case TURN_OF_SLEEPMODE:
                sleepModeWasActivated = false;
                break;
            case TURN_ON_SLEEPMODE:
                sleepModeWasActivated = true;
                break;
            default:
                System.out.println("Sth other");
        }
    }

    public MonitorPane getMonitorPane() {
        return monitorPane;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
