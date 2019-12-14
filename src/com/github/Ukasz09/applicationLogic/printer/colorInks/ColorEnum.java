package com.github.Ukasz09.applicationLogic.printer.colorInks;

public enum ColorEnum {
    RED(4),
    BLUE(4),
    YELLOW(4),
    BLACK(2);


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    double defaultIncConsumption;

    ColorEnum(double defaultIncConsumption) {
        this.defaultIncConsumption = defaultIncConsumption;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public double getDefaultIncConsumption() {
        return defaultIncConsumption;
    }
}
