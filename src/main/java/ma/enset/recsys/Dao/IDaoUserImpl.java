package ma.enset.recsys.Dao;

import ma.enset.Session;
import ma.enset.recsys.Dao.Entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IDaoUserImpl implements IDaoUser {

    @Override
    public void save(User o) {
        Connection connection = DbSingeleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `user` (firstName, lastName, email, password, birthDate) " +
                    "VALUES (?, ?, ?, ?, ?)");

            // Assuming 'o' is an instance of the User class
            pstm.setString(1, o.getFirstName());
            pstm.setString(2, o.getLastName());
            pstm.setString(3, o.getEmail());
            pstm.setString(4, o.getPassword());

            // Assuming 'o.getBirthDate()' returns a java.util.Date object
            java.sql.Date sqlDate = new java.sql.Date(o.getBirthDate().getTime());
            pstm.setDate(5, sqlDate);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeById(long id) {
        Connection connection = DbSingeleton.getConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM `user` WHERE ID = ?");
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        Connection connection = DbSingeleton.getConnection();

        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM `user` WHERE ID = ?");
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Assuming you have a constructor in the User class that accepts ResultSet
                user = new User(rs.getLong("ID"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("email"), rs.getString("password"), rs.getDate("birthDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        Connection connection = DbSingeleton.getConnection();

        List<User> userList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user`");

            while (rs.next()) {
                User user = new User(rs.getLong("ID"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("email"), rs.getString("password"), rs.getDate("birthDate"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void update(User user) {
        Connection connection = DbSingeleton.getConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE `user` SET firstName=?, lastName=?, " +
                    "email=?, password=?, birthDate=? WHERE ID=?");
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            pstmt.setLong(6, user.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User Login(String email, String password) {
        Connection connection = DbSingeleton.getConnection();
        User user = null;

        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE email LIKE ?");
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");

                if (BCrypt.checkpw(password, hashedPassword)) {
                    user = new User();
                    user.setID(rs.getLong("ID"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setBirthDate(rs.getDate("birthDate"));

                    // Set session variables
                    Session.setUsername(user.getFirstName() + " " + user.getLastName());
                    Session.setUserId(user.getID());

                    System.out.println("Connected successfully");
                } else {
                    System.out.println("Incorrect password");
                }
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
