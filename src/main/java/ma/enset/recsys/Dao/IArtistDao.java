package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.Artist;

import java.util.List;

public interface IArtistDao {
     void save(Artist o);
     void removeById(long id);
      List<Artist> getAll() ;

}
