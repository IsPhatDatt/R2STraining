package repository;

import config.JdbcConnection;

import javax.management.Query;
import java.sql.*;
import java.util.Scanner;

import static config.Constants.*;

public class StaffRepository {
    public void getListStaff() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MANV, TEN, LUONG " +
                                                "FROM NHANVIEN");
            System.out.println("List staff");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public void createStaff() throws SQLException {
        String insertStaff = "INSERT INTO NHANVIEN (MANV, TEN, LUONG) " +
                            "VALUES(?,?,?)";
        Scanner sc = new Scanner(System.in);
        int idStaff;
        double salary;
        String name;
        try(Connection conn = JdbcConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(insertStaff);) {
            System.out.println("Enter id staff");
            idStaff = sc.nextInt();
            System.out.println("Enter name staff");
            name = sc.next();
            System.out.println("Enter id staff");
            salary = sc.nextDouble();
            preparedStatement.setInt(1, idStaff);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, salary);
            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                System.out.println("Added staff success!");
            } else {
                System.out.println("Fail!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    3. Tìm các nhân viên có lương nhỏ hơn 10,000.
    public void getListOfSalaryStaffLessThan10000() throws SQLException {
        String sql = "SELECT MANV, TEN, LUONG " +
                    "FROM NHANVIEN " +
                    "WHERE LUONG < " + SalaryLessThan10000;
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of staffs whose salary is less than 10,000");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    8. Cho biết tổng số lương phải trả cho các nhân viên.
    public double sumSalaryOfStaff() throws SQLException {
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT SUM(LUONG) " +
                        "FROM NHANVIEN";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            double sumSalary = rs.getDouble(1);
            return sumSalary;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
//26. Tìm các nhân viên không phải là phi công.
    public void getListOfStaffNoPilot() throws SQLException {
        String sql = "SELECT MANV, TEN " +
                "FROM NHANVIEN " +
                "WHERE MANV NOT IN " +
                "( SELECT MANV FROM CHUNGNHAN " +
                "GROUP BY MANV )";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of staffs whose salary is less than 10,000");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    27. Cho biết mã số của các nhân viên có lương cao nhất.
    public void getListOfStaffHaveHighestSalary() throws SQLException {
        String sql = "SELECT MANV, TEN, MAX(LUONG) " +
                "FROM NHANVIEN";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of staffs highest salary");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | Lương: " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    28. Cho biết tổng số lương phải trả cho các phi công.
    public void getListOfPilotTotalSalaryPayments() throws SQLException {
        String sql = "SELECT NV.MANV, NV.TEN, NV.LUONG " +
                "FROM NHANVIEN NV " +
                "INNER JOIN CHUNGNHAN CN " +
                "ON CN.MANV = NV.MANV " +
                "GROUP BY(NV.MANV)";
        try (Connection conn = JdbcConnection.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of salary of pilots:");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | Lương: " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
