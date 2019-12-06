package com.github.Ukasz09.applicationLogic.printer;

import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.applicationLogic.printer.paper.PrinterPaper;
import com.github.Ukasz09.applicationLogic.printer.printOption.GrayPrint;
import com.github.Ukasz09.applicationLogic.printer.printOption.PrintOption;
import javafx.scene.image.Image;

import java.util.*;

public class Printer {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Fields
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final double DEFAULT_INC_CAPACITY = 40;
    private static final int DEFAULT_AMOUNT_OF_SHEETS = 2;
    private final ColorEnum[] defaultIncColors = {ColorEnum.BLUE, ColorEnum.RED, ColorEnum.YEALLOW, ColorEnum.BLACK};

    private List<PrintOption> printOptionList;
    private Map<ColorEnum, ColorInk> printerIncs;
    private Deque<PrinterPaper> notTakenPrintedPages;
    private int availablePaperSheets;
    private boolean isInPrintingTime;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Constructors
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Printer() {
        printerIncs = new LinkedHashMap<>();
        addDefaultIncs();
        notTakenPrintedPages = new LinkedList<>();
        availablePaperSheets = DEFAULT_AMOUNT_OF_SHEETS;
        printOptionList = new ArrayList<>();
        isInPrintingTime = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //todo: tmp null
    private void addDefaultIncs() {
        for (ColorEnum color : defaultIncColors)
            printerIncs.put(color, new ColorInk(color, color.getDefaultIncConsumption(), DEFAULT_INC_CAPACITY));
    }

    private void addDefaultOptions() {
        printOptionList.add(new GrayPrint());
    }

    public void refillInc(ColorEnum color) {
        printerIncs.get(color).refillInc();
    }

    public void refillAvailablePaper(int amount) {
        availablePaperSheets += amount;
    }

    private Image setPropertiesToImage(Image imageToPrint) {
        Image imageWithProperites = imageToPrint;
        for (PrintOption option : printOptionList)
            imageWithProperites = option.applyPropertiesToImage(imageToPrint);

        return imageWithProperites;
    }

    public boolean printImage(Image imageToPrint, boolean multicolor, int amountOfCopy) {
        if (!isEnoughOfRequiredColors(multicolor) || imageToPrint == null || amountOfCopy <= 0 || availablePaperSheets < amountOfCopy)
            return false;

        Image imageWithProperties = setPropertiesToImage(imageToPrint);
        for (int i = 0; i < amountOfCopy; i++) {
            notTakenPrintedPages.push(new PrinterPaper(imageWithProperties));
            shrinkIncCapacity(multicolor);
            availablePaperSheets--;
        }
        return true;
    }

    private boolean isEnoughOfRequiredColors(boolean multicolor) {
        if ((multicolor && !isEnoughMulticolorInc()) || (!multicolor && !isEnoughOfInc(ColorEnum.BLACK)))
            return false;
        return true;
    }


    private boolean isEnoughMulticolorInc() {
        return (isEnoughOfInc(ColorEnum.RED) && isEnoughOfInc(ColorEnum.BLUE) &&
                isEnoughOfInc(ColorEnum.YEALLOW) & isEnoughOfInc(ColorEnum.BLACK));
    }

    private boolean isEnoughOfInc(ColorEnum colorEnum) {
        ColorInk ink = printerIncs.get(colorEnum);
        if (ink == null)
            return false;
        return (ink.getActualCapacity() >= ink.getIncConsumption());

    }

    private void shrinkIncCapacity(boolean multicolor) {
        if (multicolor)
            shrinkMulticolorCapacity();
        else shrinkBlackColorCapacity();
    }

    private void shrinkMulticolorCapacity() {
        shrinkBlackColorCapacity();
        printerIncs.get(ColorEnum.RED).shrinkActualInkCapacity();
        printerIncs.get(ColorEnum.YEALLOW).shrinkActualInkCapacity();
        printerIncs.get(ColorEnum.BLUE).shrinkActualInkCapacity();
    }

    private void shrinkBlackColorCapacity() {
        printerIncs.get(ColorEnum.BLACK).shrinkActualInkCapacity();
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
    public boolean isInPrintingTime() {
        return isInPrintingTime;
    }

    public void setInPrintingTime(boolean inPrintingTime) {
        isInPrintingTime = inPrintingTime;
    }

    public Map<ColorEnum, ColorInk> getPrinterIncs() {
        return printerIncs;
    }

    public int getAvailablePaperSheets() {
        return availablePaperSheets;
    }

    public int getQtyOfPrintedPages() {
        return notTakenPrintedPages.size();
    }
}
