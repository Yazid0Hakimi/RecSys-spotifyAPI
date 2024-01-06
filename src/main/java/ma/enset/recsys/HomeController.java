package ma.enset.recsys;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

//    private List<Song> getRecommendations(){
//        List<Song> songs = new ArrayList<>();
//        Song song = new Song();
//        song.setSongName("Safar");
//        song.setArtist("Naar");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);song = new Song();
//        song.setSongName("Sarcelles");
//        song.setArtist("Elgrandetoto");
//        song.setCoverImg("/images/safar-cover.jpg");
//        songs.add(song);
//        return songs;
//    }

    private List<Song> getRecommendations() {
        String requestURL = "https://api.spotify.com/v1/recommendations?seed_tracks=0c6xIDDpzE81m2q797ordA,6nTiIhLmQ3FWhvrGafw2zj&seed_genres=morrocanRap";
        List<Song> songs = new ArrayList<>();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer BQAIWwOnf-ZeAe-NXvPikpGx_q6xrDMXJ0gFmBaJrB-iXEMXoefSyx6ig3wmnfwadJWUCU-dAZCxzERSClE6z8J0Qo9Unf3Zv6fxzWf-DFy40WtvRO8");
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
                    String ImageUrl = imagesArray.get(1).getAsJsonObject().get("url").getAsString();
                    JsonArray artists = musicItem.getAsJsonArray("artists");
                    String artistName = artists.get(0).getAsJsonObject().get("name").getAsString();
                    Song song = new Song();
                    song.setSongName(name);
                    song.setArtist(artistName);
                    song.setCoverImg(ImageUrl);
                    songs.add(song);
                    System.out.println("Name: " + name);
                    System.out.println("Artist: " + artistName);
                    System.out.println("images : " + ImageUrl);
                    System.out.println("---------------------");
                }
            } else {
                System.out.println("HTTP Request Failed: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }
}
