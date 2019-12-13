package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.theme;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public abstract class Theme {
    private String themeHexColor;
    private String textHexColor;
    private boolean isDark;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Theme(String themeHexColor, String textHexColor, boolean isDark) {
        this.themeHexColor = themeHexColor;
        this.textHexColor = textHexColor;
        this.isDark=isDark;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setThemeToPane(Pane pane) {
        pane.setStyle("-fx-background-color: " + themeHexColor);
    }

    public void setThemeToText(Text text) {
        text.setStyle("-fx-fill: " + textHexColor);
    }

    public boolean isDark() {
        return isDark;
    }
}
