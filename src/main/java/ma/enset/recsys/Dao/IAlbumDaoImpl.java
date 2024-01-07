package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IAlbumDaoImpl implements IAlbumDao {
    @Override
    public void saveAlbum(Album album) {
        Connection connection = DbSingeleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO album(seed_albums, name) VALUES (?, ?)");
            pstm.setString(1, album.getSeedAlbums());
            pstm.setString(2, album.getName());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAlbumById(long id) {
        Connection connection = DbSingeleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM album WHERE ID=?");
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
