package com.github.Ukasz09.applicationLogic.printer.colorInks;


import com.github.Ukasz09.graphiceUserInterface.sprites.printerParts.PartDrawing;

public class ColorInk {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ColorEnum color;
    private double incConsumption;
    private double defaultCapacity;
    private double actualCapacity;
    private PartDrawing incSprite;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ColorInk(ColorEnum color, double incConsumption, double defaultCapacity, PartDrawing incSprite) {
        this.color = color;
        this.incConsumption = incConsumption;
        this.defaultCapacity = defaultCapacity;
        actualCapacity = defaultCapacity;
        this.incSprite = incSprite;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                   Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void refillInc() {
        actualCapacity = defaultCapacity;
    }

    public void shrinkActualInkCapacity() {
        actualCapacity -= incConsumption;
        if (actualCapacity < 0)
            actualCapacity = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public double getIncConsumption() {
        return incConsumption;
    }

    public ColorEnum getColor() {
        return color;
    }

    public double getActualCapacity() {
        return actualCapacity;
    }
}
