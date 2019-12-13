package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PrintErrorDialogWindow extends ErrorDialogWindow {
    private static final double ERROR_IMAGE_SIZE = 80;

    private ImageView errorImageView;
    private Pane textPane;
    private Text errorText;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrintErrorDialogWindow(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        setDefaultProperty();
        addNodeToContentPane(errorImageView);
        addNodeToContentPane(textPane);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setDefaultProperty() {
        setImageViewProperty();
        setTextPaneProperty();
        addTextToTextPane();
    }

    private void setImageViewProperty() {
        errorImageView = new ImageView(ErrorKind.UNKNOWN_ERROR.errorImage);
        errorImageView.setFitWidth(ERROR_IMAGE_SIZE);
        errorImageView.setFitHeight(ERROR_IMAGE_SIZE);
    }

    private void setTextPaneProperty() {
        double textPaneWidth = getWidth() - ERROR_IMAGE_SIZE * 1.5;
        FlowPane pane = new FlowPane();
        pane.setPrefSize(textPaneWidth, getHeight() - getWindowTaskbarHeight() * 2);
        pane.setAlignment(Pos.CENTER);
        this.textPane=pane;
        setTextProperty(textPaneWidth);
    }

    private void setTextProperty(double textPaneWidth) {
        errorText = new Text(ErrorKind.UNKNOWN_ERROR.errorText);
        errorText.setTextAlignment(TextAlignment.CENTER);
        errorText.setWrappingWidth(textPaneWidth);
    }

    private void addTextToTextPane() {
        textPane.getChildren().add(errorText);
    }

    @Override
    public void updateErrorMessage(Image errorImage, String errorText) {
        this.errorImageView.setImage(errorImage);
        this.errorText.setText(errorText);
    }
}
