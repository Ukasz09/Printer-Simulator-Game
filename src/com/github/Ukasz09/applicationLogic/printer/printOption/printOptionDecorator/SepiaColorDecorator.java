package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator;

import javafx.scene.effect.Effect;
import javafx.scene.effect.SepiaTone;

public class SepiaColorDecorator extends BasePrintDecorator  {
    private IPrintDecorator basePrintDecorator;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SepiaColorDecorator(IPrintDecorator basePrintDecorator) {
        this.basePrintDecorator = basePrintDecorator;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Effect addedEffects() {
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
