package com.github.Ukasz09.applicationLogic.computer;

import javafx.scene.image.Image;

public class ErrorCommunicate {
    private String errorCode;
    private String errorMessage;
    private Image errorImage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ErrorCommunicate(String errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public ErrorCommunicate(String errorCode, String errorMessage, Image errorImage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorImage = errorImage;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean haveErrorImage() {
        return errorImage != null;
    }

    public Image getErrorImage() {
        return errorImage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
