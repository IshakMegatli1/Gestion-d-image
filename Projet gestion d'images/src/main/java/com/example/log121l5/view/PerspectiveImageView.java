package com.example.log121l5.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import com.example.log121l5.controller.PerspectiveController;
import com.example.log121l5.model.Observer;
import com.example.log121l5.model.PerspectiveModel;

import java.io.IOException;
import java.io.Serializable;

public class PerspectiveImageView implements Observer, Serializable {
    private transient ImageView imageView;
    private PerspectiveModel model;
    private PerspectiveController controller;
    private double lastMouseX, lastMouseY;
    private transient VBox viewBox;

    public PerspectiveImageView(PerspectiveModel model, PerspectiveController controller) {
        this.model = model;
        this.controller = controller;
        this.model.addObserver(this);
        initializeView();
        update(model);
    }

    private void initializeView() {
        Image image = new Image(getClass().getResourceAsStream("/img.jpeg"));
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        imageView.setOnScroll(this::handleScroll);
        imageView.setOnMousePressed(this::handleMousePressed);
        imageView.setOnMouseDragged(this::handleMouseDragged);

        Button undoButton = new Button("Undo");
        undoButton.setOnAction(e -> controller.undo());

        Button redoButton = new Button("Redo");
        redoButton.setOnAction(e -> controller.redo());

        viewBox = new VBox(imageView, undoButton, redoButton);
    }

    private void handleScroll(ScrollEvent event) {
        double oldFactor = model.getZoomFactor();
        if (event.getDeltaY() > 0) {
            controller.zoom(oldFactor * 1.1);
        } else {
            controller.zoom(oldFactor / 1.1);
        }
    }

    private void handleMousePressed(MouseEvent event) {
        lastMouseX = event.getSceneX();
        lastMouseY = event.getSceneY();
    }

    private void handleMouseDragged(MouseEvent event) {
        double deltaX = event.getSceneX() - lastMouseX;
        double deltaY = event.getSceneY() - lastMouseY;
        controller.translate(deltaX, deltaY);
        lastMouseX = event.getSceneX();
        lastMouseY = event.getSceneY();
    }

    @Override
    public void update(Object subject) {
        imageView.setScaleX(model.getZoomFactor());
        imageView.setScaleY(model.getZoomFactor());
        imageView.setTranslateX(model.getTranslationX());
        imageView.setTranslateY(model.getTranslationY());
    }

    public VBox getView() {
        return viewBox;
    }

    public void updateModel(PerspectiveModel model) {
        this.model = model;
        this.model.addObserver(this);
        update(model);
    }
    public void updateController(PerspectiveController controller){
        this.controller =controller;
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        initializeView();
        update(model);
    }
}
