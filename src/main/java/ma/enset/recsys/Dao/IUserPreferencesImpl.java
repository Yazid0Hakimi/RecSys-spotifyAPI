package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.Album;
import ma.enset.recsys.Dao.Entities.Artist;
import ma.enset.recsys.Dao.Entities.Genre;
import ma.enset.recsys.Dao.Entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IUserPreferencesImpl implements IUserPreferences {
    @Override
    public List<Genre> getGenres() {
        Connection connection = DbSingeleton.getConnection();

        List<Genre> genreList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `genre`");
            while (rs.next()) {
                Genre genre = new Genre(rs.getString("name"));
                genre.setID(rs.getInt("ID"));
                genreList.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreList;
    }

    @Override
    public List<Album> getAlbums() {
        Connection connection = DbSingeleton.getConnection();

        List<Album> albumList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `album`");
            while (rs.next()) {
                Album album = new Album(rs.getString("seed_albums"), rs.getString("name"));
                album.setID(rs.getInt("ID"));
                albumList.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albumList;
    }

    @Override
    public List<Artist> getArtists() {
        Connection connection = DbSingeleton.getConnection();

        List<Artist> artistList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `artist`");

            while (rs.next()) {
                Artist artist = new Artist(rs.getString("seed_artists"),rs.getString("name"));
                artist.setID(rs.getInt("ID"));
                artistList.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistList;
    }
}
