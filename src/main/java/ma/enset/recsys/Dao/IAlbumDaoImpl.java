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
    public void save(Album album) {
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
    public void removeById(long id) {
        Connection connection = DbSingeleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM album WHERE ID=?");
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Album> getAll() {
        Connection connection = DbSingeleton.getConnection();
        List<Album> albums = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM album");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Album album = new Album();
                album.setID(rs.getInt("ID"));
                album.setSeedAlbums(rs.getString("seed_albums"));
                album.setName(rs.getString("name"));
                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }
}
