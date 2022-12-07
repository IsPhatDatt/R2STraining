package repository;

import config.JdbcConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AircraftRepository {
    public void listAircrafts() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    "FROM MAYBAY");
            System.out.println("List aircraft");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listAircraftsRequest2() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * " +
                    "FROM MAYBAY " +
                    "WHERE TAMBAY > 10000");
            System.out.println("List of aircraft with a range of more than 10,000km");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public int countAircraftsRequest7() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM MAYBAY WHERE LOAI LIKE '%BOEING%'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int rowCount = rs.getInt(1);
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
        return 0;
    }

    public void listAircraftsRequest13() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        String sql = "SELECT LOAI FROM MAYBAY WHERE TAMBAY > " +
                "(SELECT DODAI FROM CHUYENBAY WHERE MACB = 'VN280')";
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of aircraft that can make flight VN280");
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

}
