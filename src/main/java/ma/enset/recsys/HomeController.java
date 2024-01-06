package ma.enset.recsys;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable {
    @FXML private GridPane songsGrid;
//    @FXML private ProgressIndicator spinner;
    @FXML private Button genresPreferences;
    @FXML private Button albumsPreferences;
    @FXML private Button artistsPreferences;
    private List<Song> recommendations;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        animateSpinner();
        fetchRecommendations();
    }

//    private void animateSpinner() {
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(spinner.progressProperty(), 0)),
//                new KeyFrame(Duration.seconds(2), new KeyValue(spinner.progressProperty(), 1))
//        );
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }

    private void fetchRecommendations() {
//        spinner.setVisible(true);

        Task<List<Song>> task = new Task<>() {
            @Override
            protected List<Song> call() throws Exception {
                List<Song> songs = new ArrayList<>();
                if (songs.isEmpty()){
                    String requestURL = "https://api.spotify.com/v1/recommendations?seed_tracks=0c6xIDDpzE81m2q797ordA,6nTiIhLmQ3FWhvrGafw2zj&seed_genres=morrocanRap";

                    try {
                        URL url = new URL(requestURL);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Authorization", "Bearer BQC9R1926EPWgYlStSCzIj_FsC-IeJ_Bkiraao6S7fEO2qznYy0add79TLerTF8Rl1t6_3mwmAluGoDwnG4kMbNGDbNDikGkwn2qa1yaKNZYDkESMDw");
                        int responseCode = connection.getResponseCode();

                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder response = new StringBuilder();
                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();
                            Gson gson = new Gson();
                            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
                            JsonArray items = jsonResponse.getAsJsonArray("tracks");

                            for (JsonElement item : items) {
                                JsonObject musicItem = item.getAsJsonObject();
                                JsonObject album = musicItem.getAsJsonObject("album");
                                String name = musicItem.get("name").getAsString();
                                JsonArray imagesArray = album.getAsJsonArray("images");
                                String imageUrl = imagesArray.get(1).getAsJsonObject().get("url").getAsString();
                                JsonArray artists = musicItem.getAsJsonArray("artists");
                                String artistName = artists.get(0).getAsJsonObject().get("name").getAsString();

                                Song song = new Song();
                                song.setSongName(name);
                                song.setArtist(artistName);
                                song.setCoverImg(imageUrl);
                                songs.add(song);
                            }
                        } else {
                            System.out.println("HTTP Request Failed: " + responseCode);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return songs;
            }
        };

        task.setOnSucceeded(event -> {
            recommendations = task.getValue();
            updateUI();
//            spinner.setVisible(false);
        });

        new Thread(task).start();
    }

    private void updateUI() {
        for (int i = 0; i < recommendations.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("song.fxml"));
                VBox songLayout = loader.load();

                SongController songController = loader.getController();
                songController.setData(recommendations.get(i));

                int row = i / 4;
                int column = i % 4;

                songsGrid.add(songLayout, column, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleGenresPreferencesLink(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("genre-preferences.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 1200, 600);
            Stage stage = (Stage) genresPreferences.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Genres Preferences");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleArtistsPreferencesLink(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("artist-preferences.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 1200, 600);
            Stage stage = (Stage) genresPreferences.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Artists Preferences");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleAlbumsPreferencesLink(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("album-preferences.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 1200, 600);
            Stage stage = (Stage) genresPreferences.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Albums Preferences");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
