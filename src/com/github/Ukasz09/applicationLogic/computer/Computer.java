package com.github.Ukasz09.applicationLogic.computer;

import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.BasePrintDecorator;
import com.github.Ukasz09.applicationLogic.printer.printOption.printOptionDecorator.IPrintDecorator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Computer {
    private Image imageToPrint;
    private IPrintDecorator printDecorator;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Computer(Image imageToPrint) {
        resetComputer();
        this.imageToPrint = imageToPrint;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void resetComputer() {
        resetPrintProperty();
    }

    public void resetPrintProperty() {
        printDecorator = new BasePrintDecorator();
    }

    public void setPrintDecorator(IPrintDecorator printDecorator) {
        this.printDecorator = printDecorator;
    }

    public IPrintDecorator getPrintDecorator() {
        return printDecorator;
    }

    public Image getImageToPrint() {
        return printDecorator.getImageWithAddedEffect(new ImageView(imageToPrint));
    }

    public void setImageToPrint(Image imageToPrint) {
        this.imageToPrint = imageToPrint;
    }
}
