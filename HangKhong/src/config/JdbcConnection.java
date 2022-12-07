package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            String connectionUrl = "jdbc:mysql://localhost:3307/HangKhong";
            connection = DriverManager.getConnection(connectionUrl, "root", "Dat01229786411");
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
