package com.github.Ukasz09.applicationLogic.printer.colorInks;


public class ColorInk {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ColorEnum color;
    private double incConsumption;
    private double defaultCapacity;
    private double actualCapacity;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ColorInk(ColorEnum color, double incConsumption, double defaultCapacity) {
        this.color = color;
        this.incConsumption = incConsumption;
        this.defaultCapacity = defaultCapacity;
        actualCapacity = defaultCapacity;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void refillInc() {
        actualCapacity = defaultCapacity;
    }

    public void shrinkActualInkCapacity() {
        actualCapacity -= incConsumption;
        if (actualCapacity < 0)
            actualCapacity = 0;
    }

    public double getIncConsumption() {
        return incConsumption;
    }

    public ColorEnum getColor() {
        return color;
    }

    public double getActualCapacity() {
        return actualCapacity;
    }

    public double getPercentageLevel() {
        return actualCapacity / defaultCapacity * 100;
    }
}
