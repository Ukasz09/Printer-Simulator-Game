package com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes;

import com.github.Ukasz09.graphiceUserInterface.sprites.properites.ImagesProperties;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TaskbarPane extends ComputerPane {
    private static final Image DEFAULT_WINDOW_BUTTON = ImagesProperties.windowsLogoTaskbarImage();
    private static final double DEFAULT_WINDOW_BUTTON_WIDTH = 20;
    public static final double DEFAULT_HEIGHT = 20; //25 is min size

    private final Image windowButtonImage = DEFAULT_WINDOW_BUTTON;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TaskbarPane(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        addStartButton();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addStartButton() {
        Button windowButton = makeButton(DEFAULT_WINDOW_BUTTON_WIDTH, getHeight()); // /2
        addNodeToPane(windowButton);
    }

    private Button makeButton(double width, double height) {
        ImageView buttonImageView = getWindowButtonImageView(width, height);

//        Button windowButton = new Button("", buttonImageView);
        Button windowButton = new Button();
        windowButton.setMinSize(width*4, height);
        windowButton.setMaxSize(width*width, height);
        BackgroundImage bImage = new BackgroundImage(buttonImageView.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(windowButton.getWidth(), windowButton.getHeight(), true, true, true, true));
        Background b=new Background(bImage);
        windowButton.setBackground(b);
        windowButton.setOnMouseClicked(event -> {
                System.out.println("TE");
        });
        return windowButton;
    }

    private ImageView getWindowButtonImageView(double width, double height) {
        ImageView iv = new ImageView(windowButtonImage);
        iv.setFitWidth(width);
        iv.setFitHeight(height);
        return iv;
    }

    @Override
    public void render() {
        renderTaskBar();
    }

    private void renderTaskBar() {
        setColor(Color.rgb(35,93,219));
        graphicContext.fillRect(0, 0, getWidth(), getHeight());
    }

    private void setColor(Color color) {
        graphicContext.setFill(color);
    }

    @Override
    public void update() {
//todo:
    }
}
