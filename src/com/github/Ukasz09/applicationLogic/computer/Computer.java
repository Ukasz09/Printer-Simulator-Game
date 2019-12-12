package com.github.Ukasz09.applicationLogic.computer;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.BasePrintDecorator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Computer {
    private final Image defaultImageToPrint;

    private boolean multicolor;
    private int qtyOfCopy;
    private Image imageToPrint;
    private BasePrintDecorator printDecorator; //todo: tmp -> pozniej zmienic na IPrintDecorator

    private ErrorCommunicate errorCommunicate;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Computer(Image imageToPrint) {
        defaultImageToPrint = imageToPrint;
        resetComputer();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //todo: dac przey printowaniu B-W zabieranie tylko czarnego
    public void resetComputer() {
        resetPrintProperty();
        errorCommunicate = null;
        imageToPrint = defaultImageToPrint;
    }

    public void resetPrintProperty() {
        printDecorator = new BasePrintDecorator();
        multicolor = false;
        qtyOfCopy = 0;
    }

    public void setPrintingOption(boolean multicolor, int qtyOfCopy, Image imageToPrint) {
        this.multicolor = multicolor;
        this.qtyOfCopy = qtyOfCopy;
        this.imageToPrint = imageToPrint;
    }

    public void setPrintDecorator(BasePrintDecorator printDecorator) {
        this.printDecorator = printDecorator;
    }

    public BasePrintDecorator getPrintDecorator() {
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

    ////////////////////////////

    //todo: tmp
    public Image getImageToPrint() {
        return printDecorator.getImageWithAddedEffect(new ImageView(imageToPrint));
    }

    public int getQtyOfCopy() {
        return qtyOfCopy;
    }

    public boolean isMulticolor() {
        return multicolor;
    }

    public void setImageToPrint(Image imageToPrint) {
        this.imageToPrint = imageToPrint;
    }

    //    public boolean print(Printer printer) {
//        try {
//            printer.printImage(imageToPrint, multicolor, qtyOfCopy);
//        } catch (PrinterException e) {
//            errorCommunicate = new ErrorCommunicate(e.getMessage(), e.getCause().getMessage());
//            return false;
//        }
//        return true;
//    }
}
