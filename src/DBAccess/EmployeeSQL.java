package DBAccess;

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
    public static ObservableList<Employee> getUsers() {
        ObservableList<Employee> userList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM employees";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userID = rs.getInt("Employee_ID");
                String employeeName = rs.getString("Employee_Name");
                String username = rs.getString("UserName");
                String password = rs.getString("Password");
                Model.Employee employee = new Model.Employee(userID, employeeName, username, password);
                userList.add(employee);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static void addEmployee(String employeeName, String userName, String passWord) {
        try {
            String sql = "INSERT INTO employees (Employee_Name, UserName, Password) VALUES (?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, employeeName);
            ps.setString(2, userName);
            ps.setString(3, passWord);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
