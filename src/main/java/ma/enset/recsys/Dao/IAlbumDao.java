package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.Album;

import java.util.List;

public interface IAlbumDao {
    void save(Album album);
    void removeById(long id);
    List<Album> getAll();
}
