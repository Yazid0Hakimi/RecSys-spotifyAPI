package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloController {

    public void sendSpotifyRequest() {
        String requestURL = "https://api.spotify.com/v1/recommendations?seed_tracks=0c6xIDDpzE81m2q797ordA,6nTiIhLmQ3FWhvrGafw2zj&seed_genres=morrocanRap";

        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer BQB9EJBI2IoCd0JCl82C_nAn6LZn_dlY5KjsJwU1YxGDAZRnoinTWMjw0ekdsl6KN-ncbLKMBInAZaJ6jHJklCLe9cdGIoN-aax0QyoC7eRH5o7xZak");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response using Gson
                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
                // Extract specific data
                JsonArray items = jsonResponse.getAsJsonArray("tracks");

                for (JsonElement item : items) {
                    JsonObject musicItem = item.getAsJsonObject();
                    JsonObject album = musicItem.getAsJsonObject("album");
                    String albumType = album.get("album_type").getAsString();
                    String releaseDate = album.get("release_date").getAsString();
                    String name = musicItem.get("name").getAsString();
                    JsonArray artists = musicItem.getAsJsonArray("artists");
                    String artistName = artists.get(0).getAsJsonObject().get("name").getAsString();

                    // Apply logic similar to JavaScript logic here if needed
                    // Logic for name and artistName length truncation can be added here

                    // Output extracted data
                    System.out.println("Name: " + name);
                    System.out.println("Artist: " + artistName);
                    System.out.println("Album Type: " + albumType);
                    System.out.println("Release Date: " + releaseDate);
                    System.out.println("---------------------");
                }
            } else {
                System.out.println("HTTP Request Failed: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
