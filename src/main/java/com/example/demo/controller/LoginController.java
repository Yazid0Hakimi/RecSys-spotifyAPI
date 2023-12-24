package com.example.demo.controller;
import com.example.demo.Dao.Entities.User;
import com.example.demo.Dao.IDaoUserImpl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    private IDaoUserImpl userDAO = new IDaoUserImpl();

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    public void login(){
        String email = emailField.getText();
        String password = passwordField.getText();
        User user = userDAO.Login(email, password);
        System.out.println(user.getFirstName() + " " + user.getLastName());
    }

}
