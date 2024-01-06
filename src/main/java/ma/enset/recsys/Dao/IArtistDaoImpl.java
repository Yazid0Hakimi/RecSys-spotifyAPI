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
    public void save(Artist o) {
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
    public List<Artist> getAll() {
        Connection connection = DbSingeleton.getConnection();
        List<Artist> artists = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT ar.ID,ar.seed_artists, ar.name FROM artist ar JOIN user_artist ua ON ar.ID = ua.artistId WHERE ua.userId ="+ Session.getUserId());
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


}
