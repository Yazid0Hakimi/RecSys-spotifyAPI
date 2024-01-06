package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.Album;
import ma.enset.recsys.Dao.Entities.Artist;
import ma.enset.recsys.Dao.Entities.Genre;

import java.util.List;

public interface IUserPreferences {

    List<Genre> getGenres();
    List<Album> getAlbums();
    List<Artist> getArtists();
}
