package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

public class RandomHueDecorator extends BasePrintDecorator {
    private IPrintDecorator basePrintDecorator;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public RandomHueDecorator(IPrintDecorator basePrintDecorator) {
        this.basePrintDecorator = basePrintDecorator;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Effect addedEffects() {
        ColorAdjust colorHue = getRandomHue();
        colorHue.setInput(basePrintDecorator.addedEffects());
        return colorHue;
    }

    private ColorAdjust getRandomHue() {
        ColorAdjust randHue = new ColorAdjust();
        double randValue = (Math.random() * 3) - 1;
        System.out.println(randValue);
        randHue.setHue(randValue);
        return randHue;
    }

}
