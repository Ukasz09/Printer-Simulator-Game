package com.github.Ukasz09.applicationLogic;

import com.github.Ukasz09.graphiceUserInterface.ViewManager;
import com.github.Ukasz09.graphiceUserInterface.backgrounds.Background;
import com.github.Ukasz09.graphiceUserInterface.backgrounds.RoomBackground;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationMain extends Application {
    private ViewManager manager;
    private double elapsedTime;
    private Long lastNanoTime;

    private Background background;

    public ApplicationMain() {
        manager = ViewManager.getInstance();
        lastNanoTime = System.nanoTime();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        manager.initialize("PrinterGameApplication", true);
        primaryStage = manager.getMainStage();

        prepareBackground();

        class gameAnimationTimer extends AnimationTimer {
            @Override
            public void handle(long currentNanoTime) {
                background.render();
            }
        }
        new gameAnimationTimer().start();
        primaryStage.show();
    }

    private void prepareBackground() {
        background = new RoomBackground();
        background.playBackgroundSound();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
