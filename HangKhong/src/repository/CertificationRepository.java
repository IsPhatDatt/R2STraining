package repository;

import config.JdbcConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static config.Constants.*;

public class CertificationRepository {
    public void getListCertification() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();){
            ResultSet rs = stmt.executeQuery("SELECT MANV, MAMB " +
                                                "FROM CHUNGNHAN");
            System.out.println("List certification");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    9. Cho biết mã số của các phi công lái máy báy Boeing.
    public void getListOfCodeOfBoeingPilot() throws SQLException {
        String sql = "SELECT MANV " +
                "FROM CHUNGNHAN CN " +
                "JOIN MAYBAY MB " +
                "ON CN.MAMB = MB.MAMB " +
                "WHERE MB.LOAI LIKE('%" + TypeOfAirCraftBoeing + "%')";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of codes of Boeing pilots");
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    10. Cho biết các nhân viên có thể lái máy bay có mã số 747.
    public void getListOfStaffCanFlyAirCraftCode747() throws SQLException {
        String sql = "SELECT NV.MANV, NV.TEN, NV.LUONG " +
                    "FROM NHANVIEN NV " +
                    "JOIN CHUNGNHAN CN " +
                    "ON CN.MANV = NV.MANV " +
                    "WHERE MAMB = " + AirCraftCode747;
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of staffs who can fly aircraft with code 747");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    11. Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có có thê lái.
    public void getListOfCodeAirCraftForStaffLastNameNguyen() throws SQLException {
        String sql = "SELECT CN.MAMB " +
                "FROM NHANVIEN NV JOIN CHUNGNHAN CN " +
                "ON CN.MANV = NV.MANV " +
                "WHERE NV.Ten LIKE('" + StaffLastNameIsNguyen +"%')";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of codes for aircraft that employees with the last name Nguyễn can fly");
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    12. Cho biết mã số của các phi công vừa lái đ ư ơc Boeing vừa lái đ ư ơc Airbus.
    public void getListOfCodeStaffCanFlyAirCraftBoeingAndAirbus() throws SQLException {
        String sql = "SELECT MaNV " +
                    "FROM NHANVIEN " +
                    "WHERE MaNV IN " +
                                "(SELECT DISTINCT MANV " +
                                "FROM CHUNGNHAN " +
                                "WHERE MAMB IN " +
                                                "(SELECT MAMB " +
                                                "FROM MAYBAY " +
                                                "WHERE LOAI LIKE '%" + TypeOfAirCraftAirbus + "%')) " +
                                                "AND MaNV IN " +
                                                            "( SELECT DISTINCT MaNV " +
                                                            "FROM CHUNGNHAN WHERE MAMB IN " +
                                                            "(SELECT MAMB FROM MAYBAY " +
                                                            "WHERE LOAI LIKE '%" + TypeOfAirCraftBoeing + "%'))";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of codes of pilots who can both fly Boeing and fly Airbus");
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    15. Cho biết tên của các phi công lái máy bay Boeing.
    public void getListOfFullNameStaffFlyAirCraftBoeing() throws SQLException {
        String sql = "SELECT DISTINCT NV.TEN " +
                "FROM NHANVIEN NV " +
                "JOIN CHUNGNHAN CN " +
                "ON NV.MANV = CN.MANV " +
                "JOIN MAYBAY MB " +
                "ON MB.MAMB = CN.MAMB " +
                "WHERE MB.LOAI LIKE '%" + TypeOfAirCraftBoeing + "%'";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of names of Boeing pilots");
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    16. Với mỗi loại máy bay có phi công lái cho biết mã số, loại máy báy và tổng số phi công có thể lái loại máy bay ñó
    public void getListStaffCanFlyAircraft() throws SQLException {

        String sql = "SELECT MB.MAMB, MB.LOAI, COUNT(CN.MANV) " +
                "FROM MAYBAY MB " +
                "INNER JOIN CHUNGNHAN CN " +
                "ON CN.MAMB = MB.MAMB " +
                "GROUP BY(MB.MAMB)";

        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();){
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("For each type of aircraft with a pilot:");
            while(rs.next()){
                System.out.println("Code airCraft: " + rs.getInt(1));
                System.out.println("Type airCraft: " + rs.getString(2));
                System.out.println("Total pilots can fly aircraft: " + rs.getInt(3));
                System.out.println("----------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    21. Cho biết mã số của các phi công chỉ lái ñược 3 loại máy bay
    public void getListOfCodeStaffOnlyFlyThreeAircraft() throws SQLException {
        String sql = "SELECT MANV " +
                "FROM CHUNGNHAN " +
                "GROUP BY(MANV) " +
                "HAVING COUNT(MAMB) = " + QuantityAircraftCanFlyIs3;
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of code staffs can fly 3 aircraft:");
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    22. Với mỗi phi công có thể lái nhiều hơn 3 loại máy bay, cho biết mã số phi công và tầm bay lớn nhất của các loại máy bay mà phi công ñó có thể lái.
    public void getListOfStaffCanFlyGreaterThan3AirCraft() throws SQLException {
        String sql = "SELECT CN.MANV, MB.TAMBAY " +
                "FROM CHUNGNHAN CN " +
                "INNER JOIN MAYBAY MB on MB.MAMB = CN.MAMB " +
                "GROUP BY(CN.MANV) " +
                "HAVING COUNT(CN.MAMB) > 3 AND MAX(MB.TAMBAY)";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of staffs can fly greater than 3 aircraft:");
            while(rs.next()){
                System.out.println("Code staff: " + rs.getInt(1));
                System.out.println("The greatest flight range of aircraft: " + rs.getDouble(2));
                System.out.println("__________");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    23. Với mỗi phi công cho biết mã số phi công và tổng số loại máy bay mà phi công ñó có thể lái.
    public void getListOfStaffNumberOfAircraftCanFly() throws SQLException {
        String sql = "SELECT NV.MANV, COUNT(CN.MAMB) " +
                "FROM NHANVIEN NV " +
                "LEFT JOIN CHUNGNHAN CN " +
                "ON CN.MANV = NV.MANV " +
                "GROUP BY NV.MANV";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of staffs and quantity aircraft can fly:");
            while(rs.next()){
                System.out.println("Code staff: " + rs.getInt(1));
                System.out.println("Quantity aircraft can fly: " + rs.getInt(2));
                System.out.println("__________");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
