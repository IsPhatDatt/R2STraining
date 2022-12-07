package repository;

import config.JdbcConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FlightRepository {
    public void listFlights() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                                                "FROM CHUYENBAY");
            System.out.println("List flight");
            while(rs.next()){
                System.out.println(rs.getString(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getString(3) + " | " +
                        rs.getDouble(4) + " | " +
                        rs.getTime(5) + " | " +
                        rs.getTime(6) + " | " +
                        rs.getDouble(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listFlightsRequest1() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            String sql = "SELECT * FROM CHUYENBAY WHERE GaDi='DAD'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of flights to Dà lạt");
            while(rs.next()){
                System.out.println(rs.getString(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getString(3) + " | " +
                        rs.getDouble(4) + " | " +
                        rs.getTime(5) + " | " +
                        rs.getTime(6) + " | " +
                        rs.getDouble(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listFlightsRequest4() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            String sql = "SELECT * FROM CHUYENBAY WHERE DODAI>8000 AND DODAI<10000";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of flights with route lengths less than 10,000km and greater than 8,000km");
            while(rs.next()){
                System.out.println(rs.getString(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getString(3) + " | " +
                        rs.getDouble(4) + " | " +
                        rs.getTime(5) + " | " +
                        rs.getTime(6) + " | " +
                        rs.getDouble(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listFlightsRequest5() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            String sql = "SELECT * FROM CHUYENBAY WHERE GADI='SGN' AND GADEN='BMV'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of flights departing from Saigon (SGN) to Ban Me Thuoc (BMV)");
            while(rs.next()){
                System.out.println(rs.getString(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getString(3) + " | " +
                        rs.getDouble(4) + " | " +
                        rs.getTime(5) + " | " +
                        rs.getTime(6) + " | " +
                        rs.getDouble(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public int countFlightsRequest6() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            String sql = "SELECT Count(*) FROM CHUYENBAY WHERE GADI='SGN'";
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

    public void listFlightsRequest14() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            String sql = "SELECT MACB FROM CHUYENBAY WHERE DODAI < " +
                    "(SELECT TAMBAY FROM MAYBAY WHERE LOAI = 'Airbus A320')";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of flights that can be performed by Airbus A320");
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
