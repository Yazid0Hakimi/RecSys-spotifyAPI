package ma.enset.recsys.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSingeleton {
    public static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/recsys", "root","");
            System.out.println("Database connected");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("database not connected");
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}