package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

public class RandomHueDecorator extends BasePrintDecorator implements IColorDecorator {
    private BasePrintDecorator basePrintDecorator;

    public RandomHueDecorator(BasePrintDecorator basePrintDecorator) {
        this.basePrintDecorator = basePrintDecorator;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Effect addedEffects() {
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
