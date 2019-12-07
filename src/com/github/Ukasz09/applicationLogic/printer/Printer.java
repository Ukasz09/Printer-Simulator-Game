package com.github.Ukasz09.applicationLogic.printer;

import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.applicationLogic.printer.paper.PrinterPaper;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterErrorCodes;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterContainersException;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterException;
import javafx.scene.image.Image;

import java.util.*;

public class Printer {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final double DEFAULT_INC_CAPACITY = 40;
    private static final int DEFAULT_AMOUNT_OF_SHEETS = 2;
    private static final int DEFAULT_MAX_QTY_OF_AVAILABLE_SHEETS = 5;
    private static final int DEFAULT_MAX_QTY_OF_PRINTED_SHEETS = 10;

    private final ColorEnum[] defaultIncColors = {ColorEnum.BLUE, ColorEnum.RED, ColorEnum.YEALLOW, ColorEnum.BLACK};

    private Map<ColorEnum, ColorInk> printerInks;
    private Deque<PrinterPaper> notTakenPrintedPages;
    private int availablePaperSheets;
    private boolean isInPrintingTime;
    private int maxQtyOfAvailableSheets;
    private int maxQtyOfPrintedSheets;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Printer() {
        printerInks = new LinkedHashMap<>();
        addDefaultIncs(DEFAULT_INC_CAPACITY);
        notTakenPrintedPages = new LinkedList<>();
        availablePaperSheets = DEFAULT_AMOUNT_OF_SHEETS;
        isInPrintingTime = false;
        maxQtyOfAvailableSheets = DEFAULT_MAX_QTY_OF_AVAILABLE_SHEETS;
        maxQtyOfPrintedSheets = DEFAULT_MAX_QTY_OF_PRINTED_SHEETS;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addDefaultIncs(double inksCapacity) {
        for (ColorEnum color : defaultIncColors)
            printerInks.put(color, new ColorInk(color, color.getDefaultIncConsumption(), inksCapacity));
    }

    public void refillInc(ColorEnum color) {
        printerInks.get(color).refillInc();
    }

    public void refillAvailablePaper(int amount) throws PrinterContainersException {
        if (!isPossibleToAddNewPapers(amount))
            throw new PrinterContainersException(PrinterErrorCodes.FULL_AVAILABLE_PAPER_STACK.code,
                    new Throwable(". Cant add new paper. Available= " + availablePaperSheets));
        availablePaperSheets += amount;
    }

    private boolean isPossibleToAddNewPapers(int amount) {
        return (availablePaperSheets + amount <= DEFAULT_MAX_QTY_OF_AVAILABLE_SHEETS);
    }

    public void printImage(Image imageToPrint, boolean multicolor, int qtyOfCopy) throws PrinterException {
        checkPrinterContainers(multicolor, qtyOfCopy);
        checkImageToPrint(imageToPrint);
        checkQtyOfPageToPrint(qtyOfCopy);

        for (int i = 0; i < qtyOfCopy; i++) {
            notTakenPrintedPages.push(new PrinterPaper(imageToPrint));
            shrinkIncCapacity(multicolor);
            availablePaperSheets--;
        }
    }

    private void checkPrinterContainers(boolean multicolor, int qtyOfCopy) throws PrinterContainersException {
        checkInkCapacity(multicolor);
        checkAvailablePaperCapacity(qtyOfCopy);
        checkPrintedPagesStack();
    }

    private void checkInkCapacity(boolean multicolor) throws PrinterContainersException {
        if (!isEnoughOfRequiredColors(multicolor))
            throw new PrinterContainersException(PrinterErrorCodes.RUN_OUT_OF_INK.code, new Throwable(". Not enough of ink: multicolor=" + multicolor));
    }

    private void checkAvailablePaperCapacity(double qtyOfPagesToPrint) throws PrinterContainersException {
        if (availablePaperSheets < qtyOfPagesToPrint)
            throw new PrinterContainersException(PrinterErrorCodes.RUN_OUT_OF_PAPER.code, new Throwable(". Not enough of paper: available=" + availablePaperSheets));
    }

    private void checkPrintedPagesStack() throws PrinterContainersException {
        if (notTakenPrintedPages.size() >= DEFAULT_MAX_QTY_OF_PRINTED_SHEETS)
            throw new PrinterContainersException(PrinterErrorCodes.FULL_PRINTED_PAGES_STACK.code, new Throwable(". Printed papers stack full=" + notTakenPrintedPages.size()));
    }

    private boolean isEnoughOfRequiredColors(boolean multicolor) {
        if (multicolor)
            return isEnoughMulticolorInc();
        return isEnoughOfInc(ColorEnum.BLACK);
    }

    private boolean isEnoughMulticolorInc() {
        for (Map.Entry<ColorEnum, ColorInk> inkSet : printerInks.entrySet()) {
            if (!isEnoughOfInc(inkSet.getKey()))
                return false;
        }

        return true;
    }

    private boolean isEnoughOfInc(ColorEnum colorEnum) {
        ColorInk ink = printerInks.get(colorEnum);
        if (ink == null)
            return false;
        return (ink.getActualCapacity() >= ink.getIncConsumption()); //todo: dac inc consumption do drukarki
    }

    private void checkImageToPrint(Image image) throws PrinterException {
        if (image == null)
            throw new PrinterException(PrinterErrorCodes.INCORRECT_IMAGE_TO_PRINT.code, new Throwable("Image == null"));
    }

    private void checkQtyOfPageToPrint(int qtyOfPageToPrint) throws PrinterException {
        if (qtyOfPageToPrint <= 0)
            throw new PrinterException(PrinterErrorCodes.INCORRECT_QTY_OF_PAGE_TO_PRINT.code, new Throwable("PageToPrint: " + qtyOfPageToPrint));
    }

    private void shrinkIncCapacity(boolean multicolor) {
        if (multicolor)
            shrinkMulticolorCapacity();
        else shrinkInkCapacity(ColorEnum.BLACK);
    }

    private void shrinkMulticolorCapacity() {
        printerInks.forEach((color, ink) -> ink.shrinkActualInkCapacity());
    }

    private void shrinkInkCapacity(ColorEnum color) {
        printerInks.get(color).shrinkActualInkCapacity();
    }

    public void takePrintedPages(int amount) {
        if (notTakenPrintedPages.size() <= amount)
            notTakenPrintedPages.clear();
        else for (int i = 0; i < amount; i++)
            notTakenPrintedPages.pop();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isInPrintingTime() {
        return isInPrintingTime;
    }

    public void setInPrintingTime(boolean inPrintingTime) {
        isInPrintingTime = inPrintingTime;
    }

    public Map<ColorEnum, ColorInk> getPrinterInks() {
        return printerInks;
    }

    public int getAvailablePaperSheets() {
        return availablePaperSheets;
    }

    public int getMaxQtyOfAvailableSheets() {
        return maxQtyOfAvailableSheets;
    }
}
