package ma.enset.recsys.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ma.enset.recsys.Dao.Entities.Album;
import ma.enset.recsys.Dao.Entities.Artist;
import ma.enset.recsys.Dao.Entities.Genre;
import ma.enset.recsys.Dao.IUserPreferences;
import ma.enset.recsys.Dao.IUserPreferencesImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HelloController {
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
        if (fullNameField != null && emailField != null &&
                passwordField != null && confirmPasswordField != null &&
                birthDateField != null) {

            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            String birthDate = birthDateField.getText();

            // Print input data to console
            System.out.println("Full Name: " + fullName);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            System.out.println("Confirm Password: " + confirmPassword);
            System.out.println("Birth Date: " + birthDate);
        } else {
            System.err.println("FXML elements are not properly initialized!");
        }
    }

    public static void sendSpotifyRequest(String seedTracks, String seedGenres, String seedArtists) {

// we need to check if seedtracks .... are filled or empty
        String requestURL = "https://api.spotify.com/v1/recommendations?seed_tracks=" + seedTracks +
                "&seed_genres=" + seedGenres + "&seed_artists=" + seedArtists;

        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer BQAW8-c7xi-XIpll9u3SVcxW2iaqWYrQ0Zaadn9mcvIZUAW7YVTVFedFHn5bUGkdnUhRdIra7pB_QI6gbksvHyMrnLYqvlW1ejZ9LD9Vgys-yJUUEA0");
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

                    System.out.println("Name: " + name);
                    System.out.println("Artist: " + artistName);
                    System.out.println("Images: " + imageUrl);
                    System.out.println("---------------------");
                }
            } else {
                System.out.println("HTTP Request Failed: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getPreferences() {
        IUserPreferences userPreferences = new IUserPreferencesImpl();
        // Test getGenres
        List<Genre> genres = userPreferences.getGenres();
        System.out.println("Genres:");
        for (Genre genre : genres) {
            System.out.println(genre.getID() + ": " + genre.getName());
        }

        // Test getAlbums
        List<Album> albums = userPreferences.getAlbums();
        System.out.println("\nAlbums:");
        for (Album album : albums) {
            System.out.println(album.getID() + ": " + album.getName() + ", Seed Albums: " + album.getSeedAlbums());
        }

        // Test getArtists
        List<Artist> artists = userPreferences.getArtists();
        System.out.println("\nArtists:");
        for (Artist artist : artists) {
            System.out.println(artist.getID() + ": " + artist.getName() + ", Seed Artists: " + artist.getSeedArtist());
        }
    }

    public static void sendSpotifySearchArtistRequest(String query) {
        query.replace(" ", "%20");
        String requestURL = "https://api.spotify.com/v1/search?q=artist:" + query + "&type=artist";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer BQAW8-c7xi-XIpll9u3SVcxW2iaqWYrQ0Zaadn9mcvIZUAW7YVTVFedFHn5bUGkdnUhRdIra7pB_QI6gbksvHyMrnLYqvlW1ejZ9LD9Vgys-yJUUEA0");
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
                JsonObject artistsObject = jsonResponse.getAsJsonObject("artists");
                JsonArray items = artistsObject.getAsJsonArray("items");

                JsonObject artist = items.get(0).getAsJsonObject();
                String artistName = artist.get("name").getAsString();
                String artistId = artist.get("id").getAsString();

                System.out.println("Artist Name: " + artistName);
                System.out.println("Artist ID: " + artistId);
                System.out.println("---------------------");

            } else {
                System.out.println("HTTP Request Failed: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendSpotifySearchAlbumRequest(String query) {
        query = query.replace(" ", "%20");
        String requestURL = "https://api.spotify.com/v1/search?q=album:" + query + "&type=album";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer BQAW8-c7xi-XIpll9u3SVcxW2iaqWYrQ0Zaadn9mcvIZUAW7YVTVFedFHn5bUGkdnUhRdIra7pB_QI6gbksvHyMrnLYqvlW1ejZ9LD9Vgys-yJUUEA0");
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
                JsonObject albumsObject = jsonResponse.getAsJsonObject("albums");
                JsonArray items = albumsObject.getAsJsonArray("items");

                if (items.size() > 0) {
                    JsonObject album = items.get(0).getAsJsonObject();
                    String albumName = album.get("name").getAsString();
                    String albumId = album.get("id").getAsString();

                    System.out.println("Album Name: " + albumName);
                    System.out.println("Album ID: " + albumId);
                    System.out.println("---------------------");
                } else {
                    System.out.println("No albums found for the query: " + query);
                }
            } else {
                System.out.println("HTTP Request Failed: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String genre = "hiphop";
        String album = "6VJwtVaX5pGXibVdA358dc";
        String artist = "3TVXtAsR1Inumwj472S9r4";
//        getPreferences();
        sendSpotifyRequest(album, genre, artist);
//        sendSpotifySearchArtistRequest("drake");
//        sendSpotifySearchAlbumRequest("black Rose");
    }
}
