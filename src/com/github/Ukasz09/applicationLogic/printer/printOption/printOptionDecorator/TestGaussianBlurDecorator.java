package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator;

import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;

public class TestGaussianBlurDecorator extends BasePrintDecorator {
    private BasePrintDecorator basePrintDecorator;

    public TestGaussianBlurDecorator(BasePrintDecorator basePrintDecorator) {
        this.basePrintDecorator = basePrintDecorator;
    }

    @Override
    protected Effect addedEffects() {
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setInput(basePrintDecorator.addedEffects());
        return gaussianBlur;
    }
}
