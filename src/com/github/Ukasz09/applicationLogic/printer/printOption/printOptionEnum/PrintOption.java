package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.*;

public enum PrintOption {
    SEPIA(true) {
        @Override
        public IPrintDecorator setOptionDecorator(IPrintDecorator basePrintDecorator) {
            return new SepiaColorDecorator(basePrintDecorator);
        }
    },

    BLACK_AND_WHITE(false) {
        @Override
        public IPrintDecorator setOptionDecorator(IPrintDecorator basePrintDecorator) {
            return new GrayColorDecorator(basePrintDecorator);
        }
    },

    RAND_HUE(true) {
        @Override
        public IPrintDecorator setOptionDecorator(IPrintDecorator basePrintDecorator) {
            return new RandomHueDecorator(basePrintDecorator);
        }
    },

    NORMAL(true) {
        @Override
        public IPrintDecorator setOptionDecorator(IPrintDecorator basePrintDecorator) {
            return basePrintDecorator;
        }
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public final boolean multicolor;

    PrintOption(boolean multicolor) {
        this.multicolor = multicolor;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public abstract IPrintDecorator setOptionDecorator(IPrintDecorator basePrintDecorator);
}
