package com.github.Ukasz09.graphiceUserInterface.sprites.printer.papers;

import com.github.Ukasz09.graphiceUserInterface.sprites.ISpriteGraphic;

public interface IPaperGraphic extends ISpriteGraphic {

    void doAnimation();

    boolean canBeDestroyedNow();
}
