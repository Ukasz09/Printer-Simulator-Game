package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane;

import com.github.Ukasz09.applicationLogic.printer.printerExceptions.PrinterErrorCodes;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.image.Image;

public enum ErrorKind {
    RUN_OUT_OF_INK_ERROR
            (PrinterErrorCodes.RUN_OUT_OF_INK.code, ImagesProperties.emptyInksError(), "Poziom atramentu nie wystarczajacy do wydrukowania. Uzupelnij by kontunuowac ..."),
    PRINTER_IS_BUSY_ERROR(PrinterErrorCodes.PRINTER_IS_BUSY_ERROR.code, ImagesProperties.printerIsBusyError(), "Prosze poczekac na zakonczenie procesu"),

    UNKNOWN_ERROR(PrinterErrorCodes.UNKNOWN_ERROR.code, ImagesProperties.exitButtonImage(), "Nierozpoznany blad!"),
    NO_ERRORS("0", null, "Brak bledow");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String errorCode;
    public Image errorImage;
    public String errorText;

    ErrorKind(String errorCode, Image errorImage, String errorText) {
        this.errorCode = errorCode;
        this.errorImage = errorImage;
        this.errorText = errorText;
    }
}
