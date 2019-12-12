package com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.BasePrintDecorator;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.GrayColorDecorator;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.RandomHueDecorator;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.SepiaColorDecorator;

public enum PrintOption {
    SEPIA {
        @Override
        public BasePrintDecorator setOptionDecorator(BasePrintDecorator basePrintDecorator) {
            return new SepiaColorDecorator(basePrintDecorator);
        }
    },

    BLACK_AND_WHITE {
        @Override
        public BasePrintDecorator setOptionDecorator(BasePrintDecorator basePrintDecorator) {
            return new GrayColorDecorator(basePrintDecorator);
        }
    },

    RAND_HUE {
        @Override
        public BasePrintDecorator setOptionDecorator(BasePrintDecorator basePrintDecorator) {
            return new RandomHueDecorator(basePrintDecorator);
        }
    },

    NORMAL {
        @Override
        public BasePrintDecorator setOptionDecorator(BasePrintDecorator basePrintDecorator) {
            return basePrintDecorator;
        }
    };

    public abstract BasePrintDecorator setOptionDecorator(BasePrintDecorator basePrintDecorator);

}
