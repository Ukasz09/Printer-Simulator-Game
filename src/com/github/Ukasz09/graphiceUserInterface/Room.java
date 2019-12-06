package com.github.Ukasz09.graphiceUserInterface;

import com.github.Ukasz09.graphiceUserInterface.backgrounds.Background;
import com.github.Ukasz09.graphiceUserInterface.backgrounds.RoomBackground;
import com.github.Ukasz09.graphiceUserInterface.sprites.ISpriteGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.*;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations.CatSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations.GlobeSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations.DeskSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations.FlowerSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations.ZingsPosterSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.PrinterSprite;
import javafx.geometry.Point2D;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.github.Ukasz09.graphiceUserInterface.sprites.decorations.DecorationsEnum.DESK;

public class Room implements IRoomGraphic {
    private ViewManager manager;
    private Background background;
    private Map<DecorationsEnum, ISpriteGraphic> decorations;
    private PrinterSprite printerSprite;

    public Room() {
        manager = ViewManager.getInstance();
        background = new RoomBackground();
        decorations = new LinkedHashMap<>();
        addDefaultDecorations();
        addPrinter();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addDefaultDecorations() {
        addDesk();
        ISpriteGraphic desk = decorations.get(DESK);
        addGlobe(desk.getPositionX(), desk.getPositionY());
        addCat();
        addFlower();
        addPoster();
    }

    private void addDesk() {
        Point2D position = calculateDeskPosition();
        decorations.put(DESK, new DeskSprite(position.getX(), position.getY()));
    }

    private Point2D calculateDeskPosition() {
        double positionX = manager.getRightFrameBorder() / 2 - DeskSprite.DEFAULT_WIDTH / 2;
        double positionY = manager.getBottomFrameBorder() - DeskSprite.DEFAULT_HEIGHT - background.getFloorHeight();
        return new Point2D(positionX, positionY);
    }

    private void addGlobe(double deskPositionX, double deskPositionY) {
        Point2D position = calculateGlobePosition(deskPositionX, deskPositionY);
        decorations.put(DecorationsEnum.GLOBE, new GlobeSprite(position.getX(), position.getY()));
    }

    private Point2D calculateGlobePosition(double deskPositionX, double deskPositionY) {
        double positionX = deskPositionX;
        double positionY = deskPositionY - GlobeSprite.DEFAULT_HEIGHT;
        return new Point2D(positionX, positionY);
    }

    private void addCat() {
        Point2D position = calculateCatPosition();
        decorations.put(DecorationsEnum.CAT, new CatSprite(position.getX(), position.getY()));
    }

    private Point2D calculateCatPosition() {
        double positionX = manager.getRightFrameBorder() - CatSprite.DEFAULT_WIDTH;
        double positionY = manager.getBottomFrameBorder() - background.getFloorHeight() - CatSprite.DEFAULT_HEIGHT;
        return new Point2D(positionX, positionY);
    }

    private void addFlower() {
        Point2D position = calculateFlowerPosition();
        decorations.put(DecorationsEnum.FLOWER, new FlowerSprite(position.getX(), position.getY()));
    }

    private Point2D calculateFlowerPosition() {
        double positionX = 0;
        double positionY = manager.getBottomFrameBorder() - background.getFloorHeight() - FlowerSprite.DEFAULT_HEIGHT;
        return new Point2D(positionX, positionY);
    }

    private void addPoster() {
        Point2D position = calculatePosterPosition();
        decorations.put(DecorationsEnum.POSTER, new ZingsPosterSprite(position.getX(), position.getY()));
    }

    private Point2D calculatePosterPosition() {
        double positionX, positionY;
        positionX = positionY = ZingsPosterSprite.DEFAULT_FRAME_THICKNESS * 2;
        return new Point2D(positionX, positionY);
    }

    private void addPrinter() {
        ISpriteGraphic desk = decorations.get(DESK);
        Point2D position = calculatePrinterPosition(desk.getPositionX(), desk.getPositionY(), desk.getWidth());
        printerSprite = new PrinterSprite(position.getX(), position.getY());
    }

    private Point2D calculatePrinterPosition(double deskPositionX, double deskPositionY, double deskWidth) {
        double positionX = deskPositionX + deskWidth * 0.95 - PrinterSprite.getDefaultWidth();
        double positionY = deskPositionY - PrinterSprite.getDefaultHeight();
        return new Point2D(positionX, positionY);
    }

    @Override
    public void update() {
        updateDecorations();
        printerSprite.update();
    }

    private void updateDecorations() {
        decorations.forEach((k, v) -> v.update());
    }

    @Override
    public void render() {
        background.render();
        renderDecorations();
        printerSprite.render();
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
}
