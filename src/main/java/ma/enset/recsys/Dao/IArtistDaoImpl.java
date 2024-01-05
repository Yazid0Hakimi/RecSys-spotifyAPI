package ma.enset.recsys.Dao;

import ma.enset.recsys.Dao.Entities.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IArtistDaoImpl implements IArtistDao{
    @Override
    public void save(Artist o) {
        Connection connection = DbSingeleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO artist(seed_track, name) VALUES (?, ?)");
            pstm.setString(1, o.getSeedTrack());
            pstm.setString(2, o.getName());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(long id) {
        Connection connection = DbSingeleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM artist WHERE ID=?");
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Artist getById(long id) {
        Connection connection = DbSingeleton.getConnection();
        Artist artist = new Artist();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM artist WHERE ID=?");
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                artist.setID(rs.getLong("ID"));
                artist.setSeedTrack(rs.getString("seed_track"));
                artist.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artist;
    }

    @Override
    public List<Artist> getAll() {
        Connection connection = DbSingeleton.getConnection();
        List<Artist> artists = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM artist");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Artist artist = new Artist();
                artist.setID(rs.getLong("ID"));
                artist.setSeedTrack(rs.getString("seed_track"));
                artist.setName(rs.getString("name"));
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    @Override
    public void update(Artist o) {
        Connection connection = DbSingeleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE artist SET seed_track=?, name=? WHERE ID=?");
            pstm.setString(1, o.getSeedTrack());
            pstm.setString(2, o.getName());
            pstm.setLong(3, o.getID());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
