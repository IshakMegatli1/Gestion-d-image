package com.example.log121l5.view;


import javafx.scene.image.Image;
import com.example.log121l5.model.ImageModel;
import com.example.log121l5.model.Observer;
import javafx.scene.layout.VBox;

import java.io.Serializable;

public class ImageView extends javafx.scene.image.ImageView implements Observer, Serializable {
    private ImageModel imageModel;

    private transient javafx.scene.image.ImageView imageView;


    private transient VBox viewBox;
    public ImageView(ImageModel imageModel) {
        this.imageModel = imageModel;
        this.imageModel.addObserver(this);
        update(this.imageModel);

        Image image = new Image(getClass().getResourceAsStream("/img.jpeg"));
        imageView = new javafx.scene.image.ImageView(image);


        viewBox = new VBox(imageView);
    }
    @Override
    public void update(Object subject) {
        if (subject instanceof ImageModel) {
            ImageModel model = (ImageModel) subject;
            setImage(new Image("file:" + model.getFilePath()));
        }
    }

    public VBox getView() {
        return viewBox;
    }

    public void updateModel(ImageModel model) {
        this.imageModel = model;
    }
}
