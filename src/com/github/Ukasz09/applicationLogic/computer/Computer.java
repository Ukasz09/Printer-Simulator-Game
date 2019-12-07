package com.github.Ukasz09.applicationLogic.computer;

import com.github.Ukasz09.applicationLogic.printer.Printer;
import com.github.Ukasz09.applicationLogic.printer.printOption.BasePrintDecorator;
import com.github.Ukasz09.applicationLogic.printer.printOption.IPrintDecorator;
import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterException;
import javafx.scene.image.Image;

public class Computer {
    private boolean multicolor;
    private int qtyOfCopy;
    private Image imageToPrint;
    private IPrintDecorator printDecorator;

    private ErrorCommunicate errorCommunicate;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Computer() {
        resetComputer();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void resetComputer() {
        multicolor = false;
        qtyOfCopy = 0;
        printDecorator = new BasePrintDecorator();
        errorCommunicate = null;
        imageToPrint = null;
    }

    public void setPrintingOption(boolean multicolor, int qtyOfCopy, Image imageToPrint) {
        this.multicolor = multicolor;
        this.qtyOfCopy = qtyOfCopy;
        this.imageToPrint = imageToPrint;
    }

    public void setPrintDecorator(IPrintDecorator printDecorator) {
        IPrintDecorator actualDecorator = this.printDecorator;
    }

    public IPrintDecorator getPrintDecorator() {
        return printDecorator;
    }

    public void removeErrorCommunicate() {
        errorCommunicate = null;
    }

    public boolean showingErrorCommunicate() {
        return errorCommunicate != null;
    }

    public ErrorCommunicate getErrorCommunicate() {
        return errorCommunicate;
    }

    public void resetPrinterDecorator() {
        printDecorator = new BasePrintDecorator();
    }

    public boolean print(Printer printer) {
        try {
            printer.printImage(imageToPrint, multicolor, qtyOfCopy);
        } catch (PrinterException e) {
            errorCommunicate = new ErrorCommunicate(e.getMessage(), e.getCause().getMessage());
            return false;
        }
        return true;
    }
}
