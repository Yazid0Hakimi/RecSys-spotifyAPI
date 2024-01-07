package ma.enset.recsys.Dao;

import ma.enset.Session;
import ma.enset.recsys.Dao.Entities.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IArtistDaoImpl implements IArtistDao{
    @Override
    public void saveArtist(Artist o) {
        Connection connection = DbSingeleton.getConnection();
        try {

            PreparedStatement pstm = connection.prepareStatement("INSERT INTO artist(seed_track, name) VALUES (?, ?)");
            pstm.setString(1, o.getSeedArtist());
            pstm.setString(2, o.getName());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeArtistById(long id) {
        Connection connection = DbSingeleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM artist WHERE ID=?");
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
