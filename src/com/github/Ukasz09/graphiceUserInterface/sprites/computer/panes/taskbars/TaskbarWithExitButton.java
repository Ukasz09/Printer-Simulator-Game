package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class TaskbarWithExitButton extends Taskbar {
    private static final Image DEFAULT_EXIT_BUTTON_IMAGE = ImagesProperties.exitButtonIcon();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TaskbarWithExitButton(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        addExitButton();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addExitButton() {
        Button button = makeExitButton();
        AnchorPane.setRightAnchor(button, 0.0);
        addNodeToPane(button);
    }

    private Button makeExitButton() {
        return makeButtonWithBackgroundAndEventHandler(getHeight(), getHeight(), DEFAULT_EXIT_BUTTON_IMAGE, EventKind.EXIT_BUTTON);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        //todo:
    }

}
