package com.github.Ukasz09.applicationLogic.printer.printOption;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

public class GrayColorDecorator extends BasePrintDecorator implements IColorDecorator {
    private BasePrintDecorator basePrintDecorator;

    public GrayColorDecorator(BasePrintDecorator basePrintDecorator) {
        this.basePrintDecorator = basePrintDecorator;
    }

    @Override
    protected Effect addedEffects() {
        ColorAdjust colorAdjust = getGreyEffect();
        colorAdjust.setInput(basePrintDecorator.addedEffects());
        return colorAdjust;
    }

    private ColorAdjust getGreyEffect() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1);
        return colorAdjust;
    }
}
