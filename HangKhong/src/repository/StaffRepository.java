package repository;

import config.JdbcConnection;

import javax.management.Query;
import java.sql.*;
import java.util.Scanner;

public class StaffRepository {
    public void listStaffs() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *" +
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
        String insertStaff = "INSERT INTO NHANVIEN (MANV, TEN, LUONG) VALUES(?,?,?)";
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

    public void listStaffsRequest3() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        String sql = "SELECT * FROM NHANVIEN WHERE LUONG<10000";
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of staffs whose salary is less than 10,000");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    public double sumStaffsRequest8() throws SQLException {
        Connection conn = JdbcConnection.getConnection();
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            String sql = "SELECT SUM(LUONG) FROM NHANVIEN";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            double sumSalary = rs.getDouble(1);
            return sumSalary;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
        return 0;
    }
}
