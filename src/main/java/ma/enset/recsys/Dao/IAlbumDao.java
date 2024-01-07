package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.Album;

import java.util.List;

public interface IAlbumDao {
    void saveAlbum(Album album);
    void removeAlbumById(long id);
}
