package ma.enset.recsys;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ma.enset.recsys.Dao.Entities.User;
import ma.enset.recsys.Dao.IDaoUserImpl;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {
    private IDaoUserImpl userDAO = new IDaoUserImpl();

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label signUpLink;
    @FXML private Button signInBtn;

    public void login(){
        String email = emailField.getText();
        String password = passwordField.getText();
        User user = userDAO.Login(email, password);
        System.out.println(user.getFirstName() + " " + user.getLastName());
        openHomePage((Stage) emailField.getScene().getWindow());
    }

    public void handleSignUpLink(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 800, 500);
            Stage stage = (Stage) signUpLink.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Sign UP");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openHomePage(Stage loginStage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 1200, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Home");
            stage.centerOnScreen();
            stage.show();
            loginStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
