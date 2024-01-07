package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.Artist;

import java.util.List;

public interface IArtistDao {
     void saveArtist(Artist o);
     void removeArtistById(long id);

}
