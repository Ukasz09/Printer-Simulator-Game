package com.github.Ukasz09.applicationLogic.computer;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.BasePrintDecorator;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionEnum.PrintOption;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private boolean multicolor;
    private int qtyOfCopy;
    private Image imageToPrint;
    private BasePrintDecorator printDecorator; //todo: tmp -> pozniej zmienic na IPrintDecorator

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
