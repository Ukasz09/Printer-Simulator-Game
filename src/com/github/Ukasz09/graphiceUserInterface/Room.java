package com.github.Ukasz09.graphiceUserInterface;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.backgrounds.Background;
import com.github.Ukasz09.graphiceUserInterface.backgrounds.RoomBackground;
import com.github.Ukasz09.graphiceUserInterface.sprites.ISpriteGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.ComputerSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.observerPattern.IEventKindObserver;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.*;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations.*;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations.DeskSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations.ZingsPosterSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.PrinterSprite;
import javafx.geometry.Point2D;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.github.Ukasz09.graphiceUserInterface.sprites.decorations.DecorationsEnum.*;

public class Room implements IRoomGraphic, IEventKindObserver {
    private ViewManager manager;
    private Background background;
    private Map<DecorationsEnum, ISpriteGraphic> decorations;
    private PrinterSprite printerSprite;
    private ComputerSprite computerSprite;
    private ZingsPosterSprite posterSprite;

    public Room() {
        manager = ViewManager.getInstance();
        background = new RoomBackground();
        decorations = new LinkedHashMap<>();
        addDefaultDecorations();
        addPoster();
        addPrinter();
        addComputer(printerSprite);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addDefaultDecorations() {
        addDesk();
        ISpriteGraphic desk = decorations.get(DESK);
        addXmasChain(desk.getPositionX(), desk.getPositionY(), desk.getWidth(), desk.getHeight());
        addGlobe(desk.getPositionX(), desk.getPositionY());
        addCat();
        addXmasTree();
        addLamps();
    }

    private void addLamps(){
        XmasLamp lamp1=new XmasLamp(0,0,manager.getRightFrameBorder()/4,manager.getBottomFrameBorder()/9);
        XmasLamp lamp2=new XmasLamp(lamp1.getPositionX()+lamp1.getWidth(),0,manager.getRightFrameBorder()/4,manager.getBottomFrameBorder()/9);
        XmasLamp lamp3=new XmasLamp(lamp2.getPositionX()+lamp2.getWidth(),0,manager.getRightFrameBorder()/4,manager.getBottomFrameBorder()/9);
        XmasLamp lamp4=new XmasLamp(lamp3.getPositionX()+lamp3.getWidth(),0,manager.getRightFrameBorder()/4,manager.getBottomFrameBorder()/9);
        decorations.put(LAMP1,lamp1);
        decorations.put(LAMP2,lamp2);
        decorations.put(LAMP3,lamp3);
        decorations.put(LAMP4,lamp4);
    }

    private void addDesk() {
        Point2D position = calculateDeskPosition();
        DeskSprite deskSprite = new DeskSprite(position.getX(), position.getY());
        decorations.put(DESK, deskSprite);
    }

    private Point2D calculateDeskPosition() {
        double positionX = manager.getRightFrameBorder() / 2 - DeskSprite.WIDTH_TO_FRAME_PROPORTION * manager.getRightFrameBorder() / 2;
        double positionY = manager.getBottomFrameBorder() - DeskSprite.HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder() - background.getFloorHeight();
        return new Point2D(positionX, positionY);
    }

    private void addXmasChain(double deskPositionX, double deskPositionY, double deskWidth, double deskHeight) {
        XmasChain xmasChain1 = new XmasChain(deskPositionX, deskPositionY, deskWidth / 2, deskHeight / 2);
        XmasChain xmasChain2 = new XmasChain(xmasChain1.getPositionX() + xmasChain1.getWidth(), deskPositionY, deskWidth / 2, deskHeight / 2);
        decorations.put(XMAS_CHAIN1, xmasChain1);
        decorations.put(XMAS_CHAIN2, xmasChain2);
    }

    private void addGlobe(double deskPositionX, double deskPositionY) {
        Point2D position = calculateGlobePosition(deskPositionX, deskPositionY);
        decorations.put(DecorationsEnum.GLOBE, new GlobeSprite(position.getX(), position.getY()));
    }

    private Point2D calculateGlobePosition(double deskPositionX, double deskPositionY) {
        double positionX = deskPositionX;
        double positionY = deskPositionY - GlobeSprite.HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder();
        return new Point2D(positionX, positionY);
    }

    private void addCat() {
        Point2D position = calculateCatPosition();
        decorations.put(DecorationsEnum.CAT, new CatSprite(position.getX(), position.getY()));
    }

    private Point2D calculateCatPosition() {
        double positionX = manager.getRightFrameBorder() - CatSprite.WIDTH_TO_FRAME_PROPORTION * manager.getRightFrameBorder();
        double positionY = manager.getBottomFrameBorder() - background.getFloorHeight() - CatSprite.HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder();
        return new Point2D(positionX, positionY);
    }

    private void addXmasTree() {
        Point2D position = calculateXmasTreePosition();
        decorations.put(DecorationsEnum.XMAS_TREE, new XmasTreeSprite(position.getX(), position.getY()));
    }

    private Point2D calculateXmasTreePosition() {
        double positionX = 0;
        double positionY = manager.getBottomFrameBorder() - background.getFloorHeight() - XmasTreeSprite.HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder();
        return new Point2D(positionX, positionY);
    }

    //todo: dodac go jako osobny interfejs mozee
    private void addPoster() {
        Point2D position = calculatePosterPosition();
        posterSprite = new ZingsPosterSprite(position.getX(), position.getY());
        posterSprite.attachObserver(this);
    }

    private Point2D calculatePosterPosition() {
        double positionX, positionY;
        positionX = positionY = ZingsPosterSprite.FRAME_THICKNESS_TO_FRAME_WIDTH_PROPORTION * manager.getRightFrameBorder() * 2;
        return new Point2D(positionX, positionY);
    }

    private void addPrinter() {
        ISpriteGraphic desk = decorations.get(DESK);
        Point2D position = calculatePrinterPosition(desk.getPositionX(), desk.getPositionY(), desk.getWidth());
        ISpriteGraphic lamp=decorations.get(LAMP1);
        printerSprite = new PrinterSprite(position.getX(), position.getY(),lamp.getPositionY()+lamp.getHeight());
    }

    private void addComputer(PrinterSprite printerSprite) {
        ISpriteGraphic globe = decorations.get(GLOBE);
        ISpriteGraphic desk = decorations.get(DESK);
        Point2D position = calculateComputerPosition(globe.getPositionX(), globe.getWidth(), desk.getPositionY());
        computerSprite = new ComputerSprite(position.getX(), position.getY(), printerSprite, posterSprite.getSpriteImage());
        printerSprite.attachObserver(computerSprite);
    }

    private Point2D calculatePrinterPosition(double deskPositionX, double deskPositionY, double deskWidth) {
        double positionX = deskPositionX + deskWidth * 0.95 - PrinterSprite.WIDTH_TO_FRAME_PROPORTION * manager.getRightFrameBorder();
        double positionY = deskPositionY - PrinterSprite.HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder();
        return new Point2D(positionX, positionY);
    }

    private Point2D calculateComputerPosition(double globePositionX, double globeWidth, double deskPositionY) {
        double positionX = globePositionX + globeWidth;
        double positionY = deskPositionY - ComputerSprite.MONITOR_HEIGHT_TO_FRAME_PROPORTION * manager.getBottomFrameBorder();
        return new Point2D(positionX, positionY);
    }

    @Override
    public void update() {
        updateDecorations();
        printerSprite.update();
        computerSprite.update();
        posterSprite.update();
    }

    private void updateDecorations() {
        decorations.forEach((k, v) -> v.update());
    }

    @Override
    public void render() {
        background.render();
        posterSprite.render();
        renderDecorations();
        printerSprite.render();
        computerSprite.render();
    }

    private void renderDecorations() {
        decorations.forEach((k, v) -> v.render());
    }

    @Override
    public void playBackgroundTheme() {
        background.playBackgroundSound();
    }

    @Override
    public void stopBackgroundTheme() {
        background.stopBackgroundSound();
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case POSTER_CHANGE:
                computerSprite.updatePrintImage(posterSprite.getSpriteImage());
                break;

            default:
                Logger.logError(getClass(), " unknown event");
        }
    }
}
