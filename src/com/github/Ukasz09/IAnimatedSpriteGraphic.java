package com.github.Ukasz09;

import com.github.Ukasz09.graphiceUserInterface.sprites.ISpriteGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.states.IKindOfState;

public interface IAnimatedSpriteGraphic extends ISpriteGraphic {
    void changeState(IKindOfState state);
}
