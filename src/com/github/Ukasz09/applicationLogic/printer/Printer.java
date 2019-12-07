package com.github.Ukasz09.applicationLogic.printer;

import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorEnum;
import com.github.Ukasz09.applicationLogic.printer.colorInks.ColorInk;
import com.github.Ukasz09.applicationLogic.printer.paper.PrinterPaper;
import com.github.Ukasz09.applicationLogic.printer.printOption.GrayPrint;
import com.github.Ukasz09.applicationLogic.printer.printOption.PrintOption;
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

    private List<PrintOption> printOptionList;
    private Map<ColorEnum, ColorInk> printerIncs;
    private Deque<PrinterPaper> notTakenPrintedPages;
    private int availablePaperSheets;
    private boolean isInPrintingTime;

    private int maxQtyOfAvailableSheets = DEFAULT_MAX_QTY_OF_AVAILABLE_SHEETS;
    private int maxQtyOfPrintedSheets = DEFAULT_MAX_QTY_OF_PRINTED_SHEETS;

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

    //todo: dac dekorator
    public Image setPropertyToImage(Image image) {
        return setPropertiesToImage(image);
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
        for (Map.Entry<ColorEnum, ColorInk> inkSet : printerIncs.entrySet()) {
            if (!isEnoughOfInc(inkSet.getKey()))
                return false;
        }

        return true;
    }

    private boolean isEnoughOfInc(ColorEnum colorEnum) {
        ColorInk ink = printerIncs.get(colorEnum);
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

    //todo: zmienic na dekorator
    private Image setPropertiesToImage(Image imageToPrint) {
        Image imageWithProperites = imageToPrint;
        for (PrintOption option : printOptionList)
            imageWithProperites = option.applyPropertiesToImage(imageToPrint);

        return imageWithProperites;
    }

    private void shrinkIncCapacity(boolean multicolor) {
        if (multicolor)
            shrinkMulticolorCapacity();
        else shrinkInkCapacity(ColorEnum.BLACK);
    }

    private void shrinkMulticolorCapacity() {
        printerIncs.forEach((color, ink) -> ink.shrinkActualInkCapacity());
    }

    private void shrinkInkCapacity(ColorEnum color) {
        printerIncs.get(color).shrinkActualInkCapacity();
    }

    /////////////// ///////////////// ///////////// /////////

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

    public int getMaxQtyOfAvailableSheets() {
        return maxQtyOfAvailableSheets;
    }
}
