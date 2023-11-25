package com.example.demo;

import com.example.demo.Dao.Entities.User;
import com.example.demo.Dao.IDaoUserImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(loader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        IDaoUserImpl userDAO = new IDaoUserImpl();

        User newUser = new User(1, "John", "Doe", "john@example.com", "password123", new Date());
        userDAO.update(newUser);

        // Retrieve and display all users
        System.out.println("All Users:");
        List<User> userList = userDAO.getAll();
        for (User user : userList) {
            System.out.println(user.getID() + " - " + user.getFirstName() + " " + user.getLastName());
        }

        // Retrieve a user by ID
        long userId = 1; // Replace with an existing user ID
        User retrievedUser = userDAO.getById(userId);
        if (retrievedUser != null) {
            System.out.println("\nUser with ID " + userId + ": " + retrievedUser.getFirstName() + " " + retrievedUser.getLastName());
        } else {
            System.out.println("\nUser with ID " + userId + " not found.");
        }

        // Update user information
        if (retrievedUser != null) {
            retrievedUser.setFirstName("UpdatedFirstName");
            retrievedUser.setLastName("UpdatedLastName");
            userDAO.update(retrievedUser);
            System.out.println("\nUser with ID " + userId + " updated.");
        }

        // Delete a user by ID
        long deleteUserId = 2; // Replace with an existing user ID for deletion
        userDAO.removeById(deleteUserId);
        System.out.println("\nUser with ID " + deleteUserId + " deleted.");

        // Verify user login
        String loginEmail = "john@example.com"; // Replace with a valid email
        String loginPassword = "password123"; // Replace with the corresponding password
        User loggedInUser = userDAO.Login(loginEmail, loginPassword);
        if (loggedInUser != null) {
            System.out.println("\nUser logged in: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        } else {
            System.out.println("\nLogin failed. Invalid credentials.");
        }
    }
}