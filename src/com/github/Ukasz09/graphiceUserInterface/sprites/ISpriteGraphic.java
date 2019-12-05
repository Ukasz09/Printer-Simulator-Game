package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;

public interface ISpriteGraphic extends IDrawingGraphic, IEventHandler {

    double getPositionX();

    double getPositionY();

    double getWidth();

    double getHeight();

}
