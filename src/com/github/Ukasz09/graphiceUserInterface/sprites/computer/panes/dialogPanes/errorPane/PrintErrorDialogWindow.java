package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane;

import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.contentPanes.ContentPane;
import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.awt.*;

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

        //todo:
        ((HBox) (getContentPane().getPane())).setAlignment(Pos.CENTER);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setDefaultProperty() {
        setImageViewProperty();
        setTextPaneProperty();
    }

    private void setImageViewProperty() {
        errorImageView = new ImageView(ErrorKind.UNKNOWN_ERROR.errorImage);
        errorImageView.setFitWidth(ERROR_IMAGE_SIZE);
        errorImageView.setFitHeight(ERROR_IMAGE_SIZE);
    }

    private void setTextPaneProperty() {
        textPane = new FlowPane();
        double textPaneWidth = getWidth() - ERROR_IMAGE_SIZE*1.5;
        textPane.setPrefSize(textPaneWidth, getHeight() - getWindowTaskbarHeight()*2);
        textPane.setStyle("-fx-background-color: red");
        ((FlowPane) textPane).setAlignment(Pos.CENTER);

        errorText = new Text(ErrorKind.UNKNOWN_ERROR.errorText);
        errorText.setTextAlignment(TextAlignment.CENTER);
        errorText.setWrappingWidth(textPaneWidth);
        textPane.getChildren().add(errorText);
    }

    @Override
    public void updateErrorMessage(Image errorImage, String errorText) {
        this.errorImageView.setImage(errorImage);
        this.errorText.setText(errorText);
    }
}
