package com.github.Ukasz09.applicationLogic.printer.printerExceptions;

public enum PrinterErrorCodes {
    //Cointainers
    RUN_OUT_OF_INK("E0001"),
    RUN_OUT_OF_PAPER("E0002"),
    FULL_AVAILABLE_PAPER_STACK("E0003"),
    FULL_PRINTED_PAGES_STACK("E0004"),

    //Other
    INCORRECT_IMAGE_TO_PRINT("E0005"),
    INCORRECT_QTY_OF_PAGE_TO_PRINT("E0006");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String code;

    PrinterErrorCodes(String code) {
        this.code = code;
    }
}
