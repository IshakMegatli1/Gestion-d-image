package com.example.log121l5;

import com.example.log121l5.controller.PerspectiveController;
import com.example.log121l5.model.ImageModel;
import com.example.log121l5.model.PerspectiveModel;
import com.example.log121l5.view.ImageView;
import com.example.log121l5.view.PerspectiveImageView;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private PerspectiveImageView perspectiveImageView;
    private PerspectiveImageView perspectiveImageView2;
    private ImageView thumbnailview;

    Stage stage;

    @Override
    public void start(Stage primaryStage) {
        PerspectiveModel perspectiveModel1 = new PerspectiveModel();
        PerspectiveModel perspectiveModel2 = new PerspectiveModel();
        ImageModel tableModel = new ImageModel();

        PerspectiveController perspectiveController1 = new PerspectiveController(perspectiveModel1);
        PerspectiveController perspectiveController2 = new PerspectiveController(perspectiveModel2);

        perspectiveImageView = new PerspectiveImageView(perspectiveModel1, perspectiveController1);
        perspectiveImageView2 = new PerspectiveImageView(perspectiveModel2, perspectiveController2);
        thumbnailview = new ImageView(tableModel);

        Button view1Button = new Button("View 1");
        view1Button.setOnAction(e -> openNewWindow("Thumbnail", thumbnailview.getView()));

        Button view2Button = new Button("View 2");
        view2Button.setOnAction(e -> openNewWindow("Perspective view 1", perspectiveImageView.getView()));

        Button view3Button = new Button("View 3");
        view3Button.setOnAction(e -> openNewWindow("Perspective view 2", perspectiveImageView2.getView()));

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(e -> saveState(perspectiveModel1, perspectiveModel2, primaryStage));

        MenuItem loadMenuItem = new MenuItem("Load");
        loadMenuItem.setOnAction(e -> loadState(primaryStage));

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> System.exit(0));

        fileMenu.getItems().addAll(saveMenuItem, loadMenuItem, exitMenuItem);
        menuBar.getMenus().add(fileMenu);

        VBox root = new VBox(menuBar, view1Button, view2Button, view3Button);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Application - labo 5");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void openNewWindow(String title, Node content) {
        try {
            stage.close();
        }
        catch (Exception e) {
            stage = new Stage();
        }
            Scene scene;
            if (title.equals("Thumbnail")) {
                scene = new Scene(new VBox(content), 200, 100);
            } else {
                scene = new Scene(new VBox(content), 700, 500);
            }
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
    }

    private void saveState(PerspectiveModel barChartModel, PerspectiveModel pieChartModel, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(barChartModel);
                out.writeObject(pieChartModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadState(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                PerspectiveModel perspectiveModel1 = (PerspectiveModel) in.readObject();
                PerspectiveModel perspectiveModel2 = (PerspectiveModel) in.readObject();

                PerspectiveController perspectiveController1 = new PerspectiveController(perspectiveModel1);
                PerspectiveController perspectiveController2 = new PerspectiveController(perspectiveModel2);

                perspectiveImageView.updateModel(perspectiveModel1);
                perspectiveImageView2.updateModel(perspectiveModel2);

                perspectiveImageView.updateController(perspectiveController1);
                perspectiveImageView2.updateController(perspectiveController2);

                perspectiveImageView.update(perspectiveModel1);
                perspectiveImageView2.update(perspectiveModel2);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
