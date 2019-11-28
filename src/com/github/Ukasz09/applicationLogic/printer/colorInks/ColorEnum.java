package com.github.Ukasz09.applicationLogic.printer.colorInks;

public enum ColorEnum {
    MULTICOLOR(3), BLACK(1);

    double defaultIncConsumption;

    ColorEnum(double defaultIncConsumption) {
        this.defaultIncConsumption = defaultIncConsumption;
    }


    public double getDefaultIncConsumption() {
        return defaultIncConsumption;
    }
}
