package com.example.demo.controller;

import java.io.IOException;
import java.util.Date;

import javafx.fxml.FXMLLoader;

import com.example.demo.Dao.Entities.User;
import com.example.demo.Dao.IDaoUserImpl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterController extends Application {
    private IDaoUserImpl userDAO = new IDaoUserImpl();

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField birthDateField;

    // Method to handle sign-up button click
    @FXML
    public void handleSignUpButton() {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String birthDate = birthDateField.getText();

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            // Handle password mismatch
            System.out.println("Passwords do not match");
            return;
        }

        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Print input data to console
        System.out.println("test");
        System.out.println("Full Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Hashed Password: " + hashedPassword);
        System.out.println("Birth Date: " + birthDate);

        User newUser = new User(fullName,fullName, email, hashedPassword, new Date(birthDate));
        userDAO.save(newUser);
    }
    @Override
    public void start(Stage primaryStage) {
    }


    public static void main(String[] args) {
        launch(args);
    }

//    public void switchToLogin() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/poo/rs/FXML/login.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) switchToLogin.getScene().getWindow(); // Get the current stage
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
