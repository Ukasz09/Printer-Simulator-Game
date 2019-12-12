package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class StartDialogWindowTaskbar extends Taskbar {
    private static final Image DEFAULT_USER_LOGO_IMAGE = ImagesProperties.userLogoIcon();
    private static final double DEFAULT_LOGO_PADDING = 2;

    public StartDialogWindowTaskbar(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        addUserLogoButton();
    }

    private void addUserLogoButton() {
        Button logoButton = makeButtonWithBackground(getHeight() * 0.9, getHeight() *0.9, DEFAULT_USER_LOGO_IMAGE);
        logoButton.setContentDisplay(ContentDisplay.RIGHT);
        setPaddingToLogo(logoButton, DEFAULT_LOGO_PADDING);
        addNodeToPane(logoButton);
    }

    private void setPaddingToLogo(Button logoButton, double padding) {
        AnchorPane.setRightAnchor(logoButton, padding);
        AnchorPane.setTopAnchor(logoButton, padding);
        AnchorPane.setBottomAnchor(logoButton, padding);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        //todo:
    }
}
