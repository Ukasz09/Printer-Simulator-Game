package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.Taskbar;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.taskbars.TaskbarWithExitButton;

public abstract class WindowDialogWithExitTaskbar extends WindowDialog {

    private static final double DEFAULT_WINDOW_OPACITY = 0.9;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public WindowDialogWithExitTaskbar(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height, DEFAULT_WINDOW_OPACITY);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected Taskbar makeWindowTaskbarPane() {
        double taskbarHeight = getHeight() * DEFAULT_TASKBAR_HEIGHT_TO_WINDOW_PROPORTION;
        return new TaskbarWithExitButton(0, 0, getWidth(), taskbarHeight);
    }

}
