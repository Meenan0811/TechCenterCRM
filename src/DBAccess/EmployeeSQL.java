package DBAccess;

import Helper.Alerts;
import Helper.JDBC;
import Model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods to pass SQL commands to database and retrieve information from Users table
 * @author Matthew Meenan
 */
public abstract class EmployeeSQL {

    /**
     * PAsses SQL commands to database to retrieve all User names and Passwords
     * @return ObservableList<user>
     */
    public static ObservableList<Employee> allEmployees() {
        ObservableList<Employee> userList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM employees";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int employeeID = rs.getInt("Employee_ID");
                String employeeName = rs.getString("Employee_Name");
                String username = rs.getString("UserName");
                String password = rs.getString("Password");
                String location = rs.getString("Location");
                Employee employee = new Employee(employeeID, employeeName, username, password, location);
                userList.add(employee);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static void addEmployee(String employeeName, String userName, String passWord, String location) {
        ObservableList<Employee> allEmp = allEmployees();
        int matchingId = 0;
        try {
            for (Employee e : allEmp) {
                if (userName.equals(e.getUserName())) { matchingId = matchingId + 1; }
            }
            if (matchingId == 0) {
                String sql = "INSERT INTO employees (Employee_Name, UserName, Password, Location) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.setString(1, employeeName);
                ps.setString(2, userName);
                ps.setString(3, passWord);
                ps.setString(4, location);

                ps.executeUpdate();
            }
            else {
                Alerts.alertMessage(2);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public static int editEmployee(int employeeID, String employeeName, String userName, String passWord, String location) {
        try {
            String sql = " UPDATE employees SET Employee_Name = ?, UserName = ?, Password = ?, Location = ? WHERE Employee_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, employeeName);
            ps.setString(2, userName);
            ps.setString(3, passWord);
            ps.setString(4, location);
            ps.setInt(5, employeeID);

            return ps.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int deleteEmployee(int employeeID) {
        try {
            String sql = "DELETE FROM employees WHERE Employee_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, employeeID);

            return ps.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
        return -1;
    }
}
