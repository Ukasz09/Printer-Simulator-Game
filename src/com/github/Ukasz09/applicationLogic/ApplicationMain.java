package com.github.Ukasz09.applicationLogic;

import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.backgrounds.Background;
import com.github.Ukasz09.graphiceUserInterface.backgrounds.RoomBackground;
import com.github.Ukasz09.graphiceUserInterface.sprites.printerParts.PartDrawing;
import com.github.Ukasz09.graphiceUserInterface.sprites.printerParts.PrinterBodySprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ApplicationMain extends Application {
    private final static String applicationTitle = "Printer Game Application";

    private final boolean fullScreen=true;

    private ViewManager manager;
    private double elapsedTime;
    private Long lastNanoTime;

    private Background background;
    private List<PartDrawing> itemsList;

    Printer printer;

    public ApplicationMain() {
        manager = ViewManager.getInstance();
        manager.initialize(applicationTitle, fullScreen);
        lastNanoTime = System.nanoTime();
        itemsList = new ArrayList<>();
        addStartedItems();
        printer=new Printer();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = manager.getMainStage();

        prepareBackground();

        class gameAnimationTimer extends AnimationTimer {
            @Override
            public void handle(long currentNanoTime) {
                updateAllItems();
                renderApplication();
            }
        }
        new gameAnimationTimer().start();
        primaryStage.show();
    }

    private void prepareBackground() {
        background = new RoomBackground();
        background.playBackgroundSound();
    }

    private void addStartedItems() {
        itemsList.add(new PrinterBodySprite());
    }

    private void updateApplication() {
        updateAllItems();
    }

    private void updateAllItems() {
        for (PartDrawing item : itemsList)
            item.update();
    }

    private void renderApplication() {
        background.render();
        renderAllItems();
    }

    private void renderAllItems() {
        for (PartDrawing item : itemsList)
            item.render();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
