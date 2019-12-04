package com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers;

import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;

public interface IPaperGraphic extends IDrawingGraphic {

    void doAnimation();

    boolean canBeDestroyedNow();
}
