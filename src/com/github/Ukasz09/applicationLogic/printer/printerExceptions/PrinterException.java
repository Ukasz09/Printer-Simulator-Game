package com.github.Ukasz09.applicationLogic.printer.printerExceptions;

public class PrinterException extends Exception {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrinterException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
