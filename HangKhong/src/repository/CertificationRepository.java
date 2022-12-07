package repository;

import config.JdbcConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CertificationRepository {
    public void listCertifications() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
                    "FROM CHUNGNHAN");
            System.out.println("List certification");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listCertificationsRequest9() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        String sql = "SELECT MANV FROM CHUNGNHAN CN JOIN MAYBAY MB ON CN.MAMB= MB.MAMB WHERE MB.LOAI LIKE('%Boeing%')";
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of codes of Boeing pilots");
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listCertificationsRequest10() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        String sql = "SELECT NV.MANV, NV.TEN, NV.LUONG " +
                "FROM NHANVIEN NV JOIN CHUNGNHAN CN " +
                "ON CN.MANV = NV.MANV " +
                "WHERE MAMB = 747";
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of staffs who can fly aircraft with code 747");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listCertificationsRequest11() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        String sql = "SELECT CN.MAMB " +
                "FROM NHANVIEN NV JOIN CHUNGNHAN CN " +
                "ON CN.MANV = NV.MANV " +
                "WHERE NV.Ten LIKE('Nguyen%')";
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of codes for aircraft that employees with the last name Nguyễn can fly");
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listCertificationsRequest12() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        String sql = "SELECT MaNV " +
                    "FROM NHANVIEN " +
                    "WHERE MaNV IN (SELECT DISTINCT MANV " +
                                "FROM CHUNGNHAN " +
                                "WHERE MAMB IN " +
                                                "(SELECT MAMB " +
                                                "FROM MAYBAY " +
                                                "WHERE LOAI LIKE '%Airbus%')) AND MaNV IN " +
                                                                                        "( SELECT DISTINCT MaNV " +
                                                                                        "FROM CHUNGNHAN WHERE MAMB IN " +
                                                                                        "(SELECT MAMB FROM MAYBAY " +
                                                                                        "WHERE LOAI LIKE '%Boeing%'))";
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of codes of pilots who can both fly Boeing and fly Airbus");
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void listCertificationsRequest15() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        String sql = "SELECT DISTINCT NV.TEN FROM NHANVIEN NV JOIN CHUNGNHAN CN " +
                "ON NV.MANV = CN.MANV " +
                "JOIN MAYBAY MB " +
                "ON MB.MAMB = CN.MAMB " +
                "WHERE MB.LOAI LIKE '%Boeing%'";
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of names of Boeing pilots");
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
