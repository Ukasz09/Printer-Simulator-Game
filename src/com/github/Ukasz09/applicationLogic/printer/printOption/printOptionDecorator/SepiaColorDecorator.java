package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator;

import javafx.scene.effect.Effect;
import javafx.scene.effect.SepiaTone;

public class SepiaColorDecorator extends BasePrintDecorator implements IColorDecorator {
    private BasePrintDecorator basePrintDecorator;

    public SepiaColorDecorator(BasePrintDecorator basePrintDecorator) {
        this.basePrintDecorator = basePrintDecorator;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Effect addedEffects() {
        SepiaTone sepiaTone = getSepiaEffect();
        sepiaTone.setInput(basePrintDecorator.addedEffects());
        return sepiaTone;
    }

    private SepiaTone getSepiaEffect() {
        SepiaTone sepiaTone = new SepiaTone();
        sepiaTone.setLevel(1);
        return sepiaTone;
    }
}
