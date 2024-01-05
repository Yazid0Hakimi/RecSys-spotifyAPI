package ma.enset.recsys;

import java.io.IOException;
import java.util.Date;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import ma.enset.recsys.Dao.Entities.User;
import ma.enset.recsys.Dao.IDaoUserImpl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterController{
    private IDaoUserImpl userDAO = new IDaoUserImpl();

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField birthDateField;
    @FXML private Label signInLink;

    // Method to handle sign-up button click
    @FXML
    public void register() {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String birthDate = birthDateField.getText();

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match");
            return;
        }

        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        // Split full name to firstname and lastname
        String []name = fullName.split(" ");

        User newUser = new User(name[0],name[1], email, hashedPassword, new Date(birthDate));
        userDAO.save(newUser);
        fullNameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        birthDateField.setText("");
    }

    public void handleSignInLink(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = (Stage) signInLink.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Sign IN");
//        RegisterController registrationController = loader.getController();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
