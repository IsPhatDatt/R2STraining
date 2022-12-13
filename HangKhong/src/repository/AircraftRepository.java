package repository;

import config.JdbcConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static config.Constants.*;

public class AircraftRepository {
    public void getListAircraft() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT MAMB, LOAI, TAMBAY " +
                                                "FROM MAYBAY");
            System.out.println("List aircraft");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    2. Cho biết các loại máy bay có tầm bay lớn hơn 10,000km.
    public void getListTypeOfAircraftFlightRangeGreaterThan10000Km() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT MAMB, LOAI, TAMBAY " +
                                                "FROM MAYBAY " +
                                                "WHERE TAMBAY > " + FlightLength10000Km);
            System.out.println("List of aircraft with a range of more than 10,000km");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    7. Có bao nhiêu loại máy báy Boeing.
    public int countTypeOfAircraftBoeing() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT COUNT(LOAI) " +
                    "FROM MAYBAY " +
                    "WHERE LOAI LIKE '%" + TypeOfAirCraftBoeing + "%'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int rowCount = rs.getInt(1);
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    13. Cho biết các loại máy bay có thể đươc thực hiện chuyến bay VN280.
    public void getListTypeOfAirCraftCanMakeFlightVN280() throws SQLException {
        String sql = "SELECT MAMB, LOAI " +
                "FROM MAYBAY " +
                "WHERE TAMBAY > " +
                "(SELECT DODAI " +
                "FROM CHUYENBAY " +
                "WHERE MACB = '" + FlightCodeVN280 + "') ";

        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();){
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of aircraft that can make flight VN280");
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
