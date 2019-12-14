package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.ComputerPaneWithGraphicContext;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes.ContentPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.theme.BrightTheme;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.theme.DarkTheme;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.theme.Theme;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.Taskbar;
import javafx.scene.Node;
import javafx.scene.layout.*;

public abstract class WindowDialog extends ComputerPaneWithGraphicContext {
    protected static final double DEFAULT_TASKBAR_HEIGHT_TO_WINDOW_PROPORTION = 0.12;

    private final ContentPane contentPane;
    private final Taskbar windowTaskbarPane;
    protected static Theme actualTheme;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public WindowDialog(double positionX, double positionY, double width, double height, double opacity) {
        super(positionX, positionY, width, height);
        getPane().setOpacity(opacity);
        windowTaskbarPane = makeWindowTaskbarPane();
        addNodeToPane(windowTaskbarPane.getPane());
        contentPane = makeContentPaneInstance();
        addContentPaneToNode();
        actualTheme = new DarkTheme();
        applyTheme(contentPane.getPane());
        windowTaskbarPane.attachObserver(this);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Pane makePaneInstance() {
        return new AnchorPane();
    }

    protected abstract ContentPane makeContentPaneInstance();

    protected abstract Taskbar makeWindowTaskbarPane();

    private static void applyTheme(Pane pane) {
        actualTheme.setThemeToPane(pane);
    }

    private void addContentPaneToNode() {
        AnchorPane.setTopAnchor(contentPane.getPane(), windowTaskbarPane.getHeight());
        addNodeToPane(contentPane.getPane());
    }

    @Override
    public void update() {
        applyTheme(contentPane.getPane());
    }

    @Override
    public void render() {
        //nothing to do
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addNodeToContentPane(Node node) {
        contentPane.addNodeToPane(node);
    }

    protected double getWindowTaskbarHeight() {
        return windowTaskbarPane.getHeight();
    }

    protected static void changeThemeColor() {
        if (actualTheme.isDark())
            actualTheme = new BrightTheme();
        else actualTheme = new DarkTheme();
    }
}
