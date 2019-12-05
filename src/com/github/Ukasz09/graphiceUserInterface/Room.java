package com.github.Ukasz09.graphiceUserInterface;

import com.github.Ukasz09.graphiceUserInterface.backgrounds.Background;
import com.github.Ukasz09.graphiceUserInterface.backgrounds.RoomBackground;
import com.github.Ukasz09.graphiceUserInterface.sprites.Sprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.*;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations.CatSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.animatedDecorations.GlobeSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations.DeskSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations.FlowerSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.decorations.normalDecorations.ZingsPosterSprite;
import com.github.Ukasz09.graphiceUserInterface.sprites.printer.printerPArtsTMP.PrinterSprite;

import java.util.LinkedHashMap;
import java.util.Map;

public class Room {
    private ViewManager manager;
    private Background background;
    private Map<DecorationsEnum, Sprite> decorations;
    private PrinterSprite printerSprite;

    public Room() {
        manager = ViewManager.getInstance();
        background = new RoomBackground();
        decorations = new LinkedHashMap<>();
        addDefaultDecorations();
        setPositionsOfDecorations();
        printerSprite = new PrinterSprite();
        setPrinterPosition();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addDefaultDecorations() {
        decorations.put(DecorationsEnum.GLOBE, new GlobeSprite());
        decorations.put(DecorationsEnum.DESK, new DeskSprite());
        decorations.put(DecorationsEnum.CAT, new CatSprite());
        decorations.put(DecorationsEnum.FLOWER, new FlowerSprite());
        decorations.put(DecorationsEnum.POSTER, new ZingsPosterSprite());
    }

    private void setPositionsOfDecorations() {
        if (setDeskPosition()) {
            Sprite desk = decorations.get(DecorationsEnum.DESK);
            setGlobePosition(desk);
        }
        setCatPosition();
        setFlowerPosition();
        setPosterPosition();
    }

    private boolean setDeskPosition() {
        Sprite desk = decorations.get(DecorationsEnum.DESK);
        if (desk == null)
            return false;
        double positionX = manager.getRightFrameBorder() / 2 - desk.getWidth() / 2;
        double positionY = manager.getBottomFrameBorder() - desk.getHeight() - background.getFloorHeight();
        desk.setPosition(positionX, positionY);
        return true;
    }

    private boolean setGlobePosition(Sprite desk) {
        Sprite globe = decorations.get(DecorationsEnum.GLOBE);
        if (globe == null)
            return false;
        double positionX = desk.getPositionX();
        double positionY = desk.getPositionY() - globe.getHeight();
        globe.setPosition(positionX, positionY);
        return true;
    }

    private boolean setCatPosition() {
        Sprite cat = decorations.get(DecorationsEnum.CAT);
        if (cat == null)
            return false;
        double positionX = manager.getRightFrameBorder() - cat.getWidth();
        double positionY = manager.getBottomFrameBorder() - background.getFloorHeight() - cat.getHeight();
        cat.setPosition(positionX, positionY);
        return true;
    }

    private boolean setFlowerPosition() {
        Sprite flower = decorations.get(DecorationsEnum.FLOWER);
        if (flower == null)
            return false;
        double positionX = 0;
        double positionY = manager.getBottomFrameBorder() - background.getFloorHeight() - flower.getHeight();
        flower.setPosition(positionX, positionY);
        return true;
    }

    private boolean setPrinterPosition() {
        if (printerSprite == null)
            return false;
        Sprite desk = decorations.get(DecorationsEnum.DESK);
        double positionX = desk.getPositionX() + desk.getWidth() * 0.95 - printerSprite.getWidth();
        double positionY = desk.getPositionY() - printerSprite.getHeight();
        printerSprite.setPosition(positionX, positionY);
        return true;
    }

    private boolean setPosterPosition() {
        Sprite poster = decorations.get(DecorationsEnum.POSTER);
        if (poster == null)
            return false;
        double positionX, positionY;
        positionX = positionY = ZingsPosterSprite.DEFAULT_FRAME_THICKNESS * 2;
        poster.setPosition(positionX, positionY);
        return true;
    }

    public void update() {
        updateDecorations();
        printerSprite.update();
        printerSprite.updateActualPosterImage(decorations.get(DecorationsEnum.POSTER).getSpriteImage());
    }

    private void updateDecorations() {
        decorations.forEach((k, v) -> v.update());
    }

    public void render() {
        background.render();
        renderDecorations();
        printerSprite.render();
    }

    private void renderDecorations() {
        decorations.forEach((k, v) -> v.render());
    }

    public void playBackgroundTheme() {
        background.playBackgroundSound();
    }

    public void stopBackgroundTheme() {
        background.stopBackgroundSound();
    }

}
