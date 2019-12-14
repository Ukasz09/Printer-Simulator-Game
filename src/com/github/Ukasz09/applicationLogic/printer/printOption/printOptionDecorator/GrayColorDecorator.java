package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

public class GrayColorDecorator extends BasePrintDecorator {
    private IPrintDecorator basePrintDecorator;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GrayColorDecorator(IPrintDecorator basePrintDecorator) {
        this.basePrintDecorator = basePrintDecorator;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Effect addedEffects() {
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
