package repository;

import config.JdbcConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static config.Constants.*;

public class FlightRepository {
    public void getListFlight() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
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

//    1. Cho biết các chuyến bay đà lạt (DAD).
    public void getListFlightFromDaLat() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
                        "FROM CHUYENBAY " +
                        "WHERE GaDi='" + AirplaneStationCodeDaLat + "'";
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
        }
    }

//    4. Cho biết các chuyến bay có đô dài đương bay nhỏ hơn 10.000km và lớn hơn 8.000km.
    public void getListFlightLenghtLessThan10000KmAndGreaterThan8000Km() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
                        "FROM CHUYENBAY " +
                        "WHERE DODAI > " + FlightLength8000Km + " AND DODAI < " + FlightLength10000Km;
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
        }
    }

//    5. Cho biết các chuyến bay xuất phát từ Sài Gòn (SGN) đên Ban Mê Thuộc (BMV).
    public void getListFlightFromSaiGonToBanMeThuoc() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
              Statement stmt = conn.createStatement();) {
            String sql = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
                        "FROM CHUYENBAY " +
                        "WHERE GADI = '" + AirplaneStationCodeSaiGon + "' AND GADEN = '" + AirplaneStationCodeBanMeThuoc + "'";
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
        }
    }

//    6. Có bao nhiêu chuyến bay xuất phát từ Sài Gòn (SGN).
    public int countFlightsStartSaiGon() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT Count(MACB) " +
                        "FROM CHUYENBAY " +
                        "WHERE GADI='" + AirplaneStationCodeSaiGon + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int rowCount = rs.getInt(1);
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    14. Cho biết các chuyến bay có thể thực hiện bởi máy bay Airbus A320.
    public void getListOfFlightsCanBePerformedByAirbusA320() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT MACB " +
                    "FROM CHUYENBAY " +
                    "WHERE DODAI < " +
                    "(SELECT TAMBAY " +
                    "FROM MAYBAY " +
                    "WHERE LOAI = '" + TypeOfAirCraftAirbusA320 + "')";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of flights that can be performed by Airbus A320");
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    17.	Giả sử một hành khách muốn đi thẳng từ ga A đến ga B rồi quay trở về ga A. Cho biết các đường bay nào có thể đáp ứng yêu cầu này.
    public void getListOfFlightFromStationAToBBackA() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT CB.MACB, CB.GADI, CB.GADEN, CB.DODAI, CB.GIODI, CB.GIODEN, CB.CHIPHI " +
                    "FROM CHUYENBAY CB, CHUYENBAY CHUYENBAY " +
                    "WHERE CB.GADi = CHUYENBAY.GADEN AND CB.GADEN = CHUYENBAY.GADI";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of flights from A to B back A: ");
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
        }
    }

//    18. Với mỗi ga có chuyến bay xuất phát từ đó cho biết có bao nhiêu chuyến bay khởi hành từ ga đó.
    public void getListOfStationAndNumberOfFlightOfStation() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT GADI, COUNT(MACB) " +
                    "FROM CHUYENBAY " +
                    "GROUP BY(GADI)";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of station and total flight depart from there:");
            while(rs.next()){
                System.out.println("Station: " + rs.getString(1));
                System.out.println("Total flight of station: " + rs.getInt(2));
                System.out.println("___________");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//  19. Với mỗi ga có chuyến  bay xuất phát từ đó cho biết tổng chi phí phải trả cho phi công lái các chuyến bay khởi hành từ ga đó.
    public void getListOfStationAndSumPriceFlightOfStation() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT GADI, SUM(CHIPHI) " +
                    "FROM CHUYENBAY " +
                    "GROUP BY(GADI)";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of station and total price flight depart from there:");
            while(rs.next()){
                System.out.println("Station: " + rs.getString(1));
                System.out.println("Total price flight of station: " + rs.getDouble(2));
                System.out.println("___________");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    20.	Cho biết danh sách các chuyến bay có thể khởi hành trước 12:00
    public void getListOfFlightBefore12PM() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT GADI, COUNT(MACB), GiODI " +
                    "FROM CHUYENBAY " +
                    "WHERE GIODI < '" + TimeFlight12PM + "' " +
                    "GROUP BY(GADI)";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of station and total price flight depart from there:");
            while(rs.next()){
                System.out.println("Station: " + rs.getString(1));
                System.out.println("Number of flight: " + rs.getDouble(2));
                System.out.println("Time start flight: " + rs.getTime(3));
                System.out.println("___________");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
