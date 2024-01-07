package ma.enset.recsys.Dao;

import ma.enset.Session;
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
            ResultSet rs = stmt.executeQuery("SELECT g.ID ,g.name FROM genre g JOIN user_genre ug ON g.ID = ug.genreId WHERE ug.userId ="+ Session.getUserId());
            while (rs.next()) {
                Genre genre = new Genre( rs.getString("name"));
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
            ResultSet rs = stmt.executeQuery("SELECT a.ID,a.seed_albums, a.name FROM album a JOIN user_album ua ON a.ID = ua.albumId WHERE ua.userId ="+Session.getUserId());
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
            ResultSet rs = stmt.executeQuery("SELECT ar.ID,ar.seed_artists, ar.name FROM artist ar JOIN user_artist ua ON ar.ID = ua.artistId WHERE ua.userId ="+Session.getUserId());

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
