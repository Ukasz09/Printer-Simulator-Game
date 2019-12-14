package com.github.Ukasz09.graphiceUserInterface.sprites;

import com.github.Ukasz09.graphiceUserInterface.IDrawingGraphic;

public interface ISpriteGraphic extends IDrawingGraphic, IEventHandler, ILayout {

     void setImageViewVisible(boolean visible);
}
