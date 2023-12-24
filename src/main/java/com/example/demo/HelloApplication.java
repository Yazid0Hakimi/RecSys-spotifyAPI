package com.example.demo;

import com.example.demo.controller.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the Register.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        // Load the FXML file and set the RegisterController as its controller
        Scene scene = new Scene(loader.load(), 600, 400);
        stage.setTitle("Register");

        // Get the RegisterController instance
//        RegisterController registerController = loader.getController();

        // Set up the stage with the scene
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}