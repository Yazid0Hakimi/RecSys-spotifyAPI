package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // Set the scene to full size
        scene.setFill(null);  // Set fill to null to allow the scene to fill the entire stage

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
