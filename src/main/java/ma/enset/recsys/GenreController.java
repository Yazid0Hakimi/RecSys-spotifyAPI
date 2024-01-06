package ma.enset.recsys;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ma.enset.recsys.Dao.Entities.Genre;
import ma.enset.recsys.Dao.IUserPreferences;
import ma.enset.recsys.Dao.IUserPreferencesImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GenreController implements Initializable {
    @FXML private ComboBox<String> genresBox;
    @FXML private Button addPreferences;
    @FXML private HBox back;
    IUserPreferences userPreferences = new IUserPreferencesImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Genre> genres = userPreferences.getGenres();
        for (Genre genre : genres) {
            genresBox.getItems().add(genre.getName());
        }
    }

    @FXML
    private void addGenreToPreferences(){
        System.out.println(genresBox.getSelectionModel().getSelectedIndex()+1);
    }

    public void handleBackLink(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 1200, 600);
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Home");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
