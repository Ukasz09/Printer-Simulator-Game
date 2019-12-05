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
//TODO: dodac ink render
//INCS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
//private final static double DEFAULT_SPACE_BETWEEN_INKS = 20;
//private ArrayList<InkSprite> inkSpriteList;
//private void setInkBoxesPosition(double spaceBetweenInks) {
//        double positionX = manager.getRightFrameBorder();
//        double positionY = 0;
//        for (InkSprite inkSprite : inkSpriteList) {
//        positionX -= (inkSprite.getWidth() + spaceBetweenInks);
//        inkSprite.setPosition(positionX, positionY);
//        }
//        }

//private Image getInkImageByColor(ColorEnum color) {
//        switch (color) {
//        case RED:
//        return ImagesProperties.redInkSprite();
//        case YEALLOW:
//        return ImagesProperties.yellowInkSprite();
//        case BLUE:
//        return ImagesProperties.blueInkSprite();
//        case BLACK:
//        return ImagesProperties.blackInkSprite();
//default:
//        return ImagesProperties.whiteInkSprite();
//        }
//        }
//private void addInkSprites() {
//        printer.getPrinterIncs().forEach((k, v) -> {
//        inkSpriteList.add(new InkSprite(getInkImageByColor(k), v));
//        });
//        }

//private void renderInks() {
//        for (InkSprite colorInk : inkSpriteList)
//        colorInk.render();
//        }

//private void updateColorInks() {
//        for (InkSprite colorInk : inkSpriteList) {
//        colorInk.update();
//        }
//        }

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
        double positionX = manager.getRightFrameBorder() / 2 - DeskSprite.getDefaultWidth() / 2;
        double positionY = manager.getBottomFrameBorder() - DeskSprite.getDefaultHeight() - background.getFloorHeight();
        return new Point2D(positionX, positionY);
    }

    private void addGlobe(double deskPositionX, double deskPositionY) {
        Point2D position = calculateGlobePosition(deskPositionX, deskPositionY);
        decorations.put(DecorationsEnum.GLOBE, new GlobeSprite(position.getX(), position.getY()));
    }

    private Point2D calculateGlobePosition(double deskPositionX, double deskPositionY) {
        double positionX = deskPositionX;
        double positionY = deskPositionY - GlobeSprite.getDefaultHeight();
        return new Point2D(positionX, positionY);
    }

    private void addCat() {
        Point2D position = calculateCatPosition();
        decorations.put(DecorationsEnum.CAT, new CatSprite(position.getX(), position.getY()));
    }

    private Point2D calculateCatPosition() {
        double positionX = manager.getRightFrameBorder() - CatSprite.getDefaultWidth();
        double positionY = manager.getBottomFrameBorder() - background.getFloorHeight() - CatSprite.getDefaultHeight();
        return new Point2D(positionX, positionY);
    }

    private void addFlower() {
        Point2D position = calculateFlowerPosition();
        decorations.put(DecorationsEnum.FLOWER, new FlowerSprite(position.getX(), position.getY()));
    }

    private Point2D calculateFlowerPosition() {
        double positionX = 0;
        double positionY = manager.getBottomFrameBorder() - background.getFloorHeight() - FlowerSprite.getDefaultHeight();
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
        double positionY = deskPositionY-PrinterSprite.getDefaultHeight();
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
