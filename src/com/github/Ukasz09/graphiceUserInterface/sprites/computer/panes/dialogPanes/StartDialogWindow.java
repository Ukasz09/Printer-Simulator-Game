package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes;

import com.github.Ukasz09.applicationLogic.Logger;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.eventKind.EventKind;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes.ContentPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes.VerticalContentPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.StartDialogWindowTaskbar;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.Taskbar;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class StartDialogWindow extends WindowDialog {
    private static final double DEFAULT_TASKBAR_TO_WINDOW_PROPORTION = 0.2;
    private static final double DEFAULT_ICONS_TO_WINDOW_PROPORTION = 0.2;
    private static final Image DEFAULT_PRINTER_ICON_IMAGE = ImagesProperties.printerIconImage();
    private static final Image DEFAULT_THEME_CHANGE_ICON_IMAGE = ImagesProperties.themeChangeIcon();
    private static final Image DEFAULT_WALLPAPER_CHANGE_ICON_IMAGE = ImagesProperties.wallpaperChangeIcon();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StartDialogWindow(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height, 0.9);
        getPane().setVisible(false);
        addStartPaneButtons();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected ContentPane makeContentPaneInstance() {
        double taskbarHeight = getHeight() * DEFAULT_TASKBAR_TO_WINDOW_PROPORTION;
        VerticalContentPane contentPane = new VerticalContentPane(0, 0, getWidth(), getHeight() - taskbarHeight);
        return contentPane;
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case MENU_START_BUTTON:
                getPane().setVisible(!getPane().isVisible());
                break;

            default:
                Logger.logError(getClass(), "Unknown eventKind");
        }
    }

    @Override
    protected Taskbar makeWindowTaskbarPane() {
        double taskbarHeight = getHeight() * DEFAULT_TASKBAR_TO_WINDOW_PROPORTION;
        Taskbar startPaneTaskbar = new StartDialogWindowTaskbar(0, 0, getWidth(), taskbarHeight);
        return startPaneTaskbar;
    }

    private void addStartPaneButtons() {
        double sizeOfImageInButton = getWidth() * DEFAULT_ICONS_TO_WINDOW_PROPORTION;
        addStartPaneButton(sizeOfImageInButton, DEFAULT_PRINTER_ICON_IMAGE, "Drukowanie", EventKind.PRINTER_BUTTON);
        addStartPaneButton(sizeOfImageInButton, DEFAULT_WALLPAPER_CHANGE_ICON_IMAGE, "Zmiana tapety", EventKind.WALLPAPER_CHANGE);

        //todo: nie dziala
        addChangeThemeButton(sizeOfImageInButton, "Zmiana motywu");

    }

    private void addStartPaneButton(double sizeOfImageInButton, Image buttonImage, String buttonTxt, EventKind buttonEvent) {
        Pos alignment = Pos.BASELINE_LEFT;
        Button button = makeButtonWithImageTextAndEventHandler(sizeOfImageInButton, sizeOfImageInButton, getWidth(), sizeOfImageInButton, buttonImage, buttonTxt, buttonEvent, alignment);
        addNodeToContentPane(button);
    }

    private void addChangeThemeButton(double sizeOfImageInButton, String text) {
        Button button = makeStartPaneButton(sizeOfImageInButton, DEFAULT_THEME_CHANGE_ICON_IMAGE, text);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeThemeColor());
        addNodeToContentPane(button);
    }

    private Button makeStartPaneButton(double sizeOfImageInButton, Image buttonImage, String buttonTxt) {
        Pos alignment = Pos.BASELINE_LEFT;
        Button button = makeButtonWithImageAndText(sizeOfImageInButton, sizeOfImageInButton, getWidth(), sizeOfImageInButton, buttonImage, buttonTxt, alignment);
        return button;
    }
}
