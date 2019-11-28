package com.github.Ukasz09.applicationLogic.printer;

import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.applicationLogic.printer.paper.PrinterPaper;
import com.github.Ukasz09.applicationLogic.printer.printOption.GrayPrint;
import com.github.Ukasz09.applicationLogic.printer.printOption.PrintOption;
import com.github.Ukasz09.graphiceUserInterface.sprites.PartDrawing;
import com.github.Ukasz09.graphiceUserInterface.sprites.printerParts.PrinterBodySprite;
import javafx.scene.image.Image;

import java.util.*;

public class Printer {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final double DEFAULT_INC_CAPACITY = 50;
    private static final int DEFAULT_AMOUNT_OF_SHEETS = 10;
    private final ColorEnum[] defaultIncColors = {ColorEnum.BLACK, ColorEnum.MULTICOLOR};

    private List<PrintOption> printOptionList;
    private Map<ColorEnum, ColorInk> printerIncs;
    private List<PartDrawing> printerParts;
    private Deque<PrinterPaper> notTakenPrintedPages;
    private int avaliablePaperSheets;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Printer() {
        printerIncs = new HashMap<>();
        addDefaultIncs();
        printerParts = new ArrayList<>();
        addDefaultParts();
        notTakenPrintedPages = new LinkedList<>();
        avaliablePaperSheets = DEFAULT_AMOUNT_OF_SHEETS;
        printOptionList = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //todo: tmp null
    private void addDefaultIncs() {
        for (ColorEnum color : defaultIncColors)
            printerIncs.put(color, new ColorInk(color, color.getDefaultIncConsumption(), DEFAULT_INC_CAPACITY, null));
    }

    private void addDefaultParts() {
        printerParts.add(new PrinterBodySprite());
    }

    private void addDefaultOptions() {
        printOptionList.add(new GrayPrint());
    }

    public void refillInc(ColorEnum color) {
        printerIncs.get(color).refillInc();
    }

    public void refillAvaliablePaper(int amount) {
        avaliablePaperSheets += amount;
    }

    private Image setPropertiesToImage(Image imageToPrint) {
        Image imageWithProperites = imageToPrint;
        for (PrintOption option : printOptionList)
            imageWithProperites = option.applyPropertiesToImage(imageToPrint);

        return imageWithProperites;
    }

    public boolean printImage(Image imageToPrint, ColorEnum color, int amountOfCopy) {
        if (!isEnoughtOfInc(color) || imageToPrint == null || amountOfCopy <= 0 || avaliablePaperSheets < amountOfCopy)
            return false;

        Image imageWithProperties = setPropertiesToImage(imageToPrint);
        for (int i = 0; i < amountOfCopy; i++) {
            notTakenPrintedPages.push(new PrinterPaper(imageWithProperties, null));
            printerIncs.get(color).shrinkActualInkCapacity();
        }
        return true;
    }

    private boolean isEnoughtOfInc(ColorEnum colorEnum) {
        ColorInk ink = printerIncs.get(colorEnum);
        if (ink == null)
            return false;
        return (ink.getActualCapacity() >= ink.getIncConsumption());

    }

    public void takePrintedPages(int amount) {
        if (notTakenPrintedPages.size() <= amount)
            notTakenPrintedPages.clear();
        else {
            for (int i = amount; i > 0; i--)
                notTakenPrintedPages.pop();
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //todo: tmp (?)
    public double getColorCapacity(ColorEnum color) {
        ColorInk colorInk = printerIncs.get(color);
        if (colorInk == null)
            throw new NullPointerException("This color ink dont exist in this printer");
        return colorInk.getActualCapacity();
    }


}
