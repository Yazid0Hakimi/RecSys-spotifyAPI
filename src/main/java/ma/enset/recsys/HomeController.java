package ma.enset.recsys;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private GridPane songsGrid;
    List<Song> recommendations;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recommendations = new ArrayList<>(getRecommendations());
        for (int i = 0; i < recommendations.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("song.fxml"));
                VBox songLayout = loader.load();

                // Access the controller and set data for the song card
                SongController songController = loader.getController();
                songController.setData(recommendations.get(i));

                int row = i / 4; // Calculate row index
                int column = i % 4; // Calculate column index

                songsGrid.add(songLayout, column, row);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                // Ensure that song.fxml or songsGrid is not null
            } catch (Exception exc) {
                exc.printStackTrace();
                // Catch any other potential exceptions
            }
        }
    }

    private List<Song> getRecommendations(){
        List<Song> songs = new ArrayList<>();
        Song song = new Song();
        song.setSongName("Safar");
        song.setArtist("Naar");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);song = new Song();
        song.setSongName("Sarcelles");
        song.setArtist("Elgrandetoto");
        song.setCoverImg("/images/safar-cover.jpg");
        songs.add(song);
        return songs;
    }
}
