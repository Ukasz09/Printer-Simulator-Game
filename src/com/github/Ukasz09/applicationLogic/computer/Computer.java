package com.github.Ukasz09.applicationLogic.computer;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.BasePrintDecorator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Computer {
    private final Image defaultImageToPrint;

    private Image imageToPrint;
    private BasePrintDecorator printDecorator; //todo: tmp -> pozniej zmienic na IPrintDecorator

    private ErrorCommunicate errorCommunicate;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Computer(Image imageToPrint) {
        defaultImageToPrint = imageToPrint;
        resetComputer();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void resetComputer() {
        resetPrintProperty();
        errorCommunicate = null;
        imageToPrint = defaultImageToPrint;
    }

    public void resetPrintProperty() {
        printDecorator = new BasePrintDecorator();
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

    public Image getImageToPrint() {
        return printDecorator.getImageWithAddedEffect(new ImageView(imageToPrint));
    }

    public void setImageToPrint(Image imageToPrint) {
        this.imageToPrint = imageToPrint;
    }
}
