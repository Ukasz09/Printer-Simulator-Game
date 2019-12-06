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
    private static final double DEFAULT_INC_CAPACITY = 40;
    private static final int DEFAULT_AMOUNT_OF_SHEETS = 2;
    private static final int DEFAULT_MAX_QTY_OF_AVAILABLE_SHEETS = 5; //todo: tmp -> pozniej dac niestatyczna zmienna i geter
    public static final int DEFAULT_MAX_QTY_OF_PRINTED_SHEETS = 10;

    private final ColorEnum[] defaultIncColors = {ColorEnum.BLUE, ColorEnum.RED, ColorEnum.YEALLOW, ColorEnum.BLACK};

    private List<PrintOption> printOptionList;
    private Map<ColorEnum, ColorInk> printerIncs;
    private Deque<PrinterPaper> notTakenPrintedPages;
    private int availablePaperSheets;
    private boolean isInPrintingTime;

    private int maxQtyOfAvailableSheets=DEFAULT_MAX_QTY_OF_AVAILABLE_SHEETS;

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

    public boolean refillAvailablePaper(int amount) {
        if (isPossibleToAddNewPapers(amount)) {
            availablePaperSheets += amount;
            return true;
        }
        return false;
    }

    private boolean isPossibleToAddNewPapers(int amount) {
        return (availablePaperSheets + amount <= DEFAULT_MAX_QTY_OF_AVAILABLE_SHEETS);
    }

    private Image setPropertiesToImage(Image imageToPrint) {
        Image imageWithProperites = imageToPrint;
        for (PrintOption option : printOptionList)
            imageWithProperites = option.applyPropertiesToImage(imageToPrint);

        return imageWithProperites;
    }

    public boolean printImage(Image imageToPrint, boolean multicolor, int amountOfCopy) {
        if (!isEnoughOfRequiredColors(multicolor) || imageToPrint == null || amountOfCopy <= 0
                || availablePaperSheets < amountOfCopy || getQtyOfPrintedPages() >= DEFAULT_MAX_QTY_OF_PRINTED_SHEETS) //todo: tmp - poprawic pozniej
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

    public int getMaxQtyOfAvailableSheets() {
        return maxQtyOfAvailableSheets;
    }
}
