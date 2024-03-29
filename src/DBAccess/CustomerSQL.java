package DBAccess;

import Helper.JDBC;
import Model.Customers;
import Model.Repair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Contains methods to Pass SQL commands to database and retrieve, update, or add new Customer information to Customers table
 *
 * @author Matthew Meenan
 */
public abstract class CustomerSQL {

    /**
     * Passes SQL command to database to retrieve all data from Customers table and add to Customers class object
     *
     * @return ObservableList<Customers>
     */
    public static ObservableList<Customers> getAllCust() {
        ObservableList<Customers> custList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int custId = rs.getInt(1);
                String custName = rs.getString(2);
                String custPhone = rs.getString(3);
                String custStreet = rs.getString(4);
                String custCity = rs.getString(5);
                String custState = rs.getString(6);
                int custPostal = rs.getInt(7);
                LocalDateTime createDate = rs.getTimestamp(8).toLocalDateTime();
                String createdBy = rs.getString(9);
                LocalDateTime updateDate = rs.getTimestamp(10).toLocalDateTime();
                String updateBy = rs.getString(11);

                Customers cust = new Customers(custId, custName, custPhone, custStreet, custCity, custState, custPostal, createDate, createdBy, updateDate, updateBy);
                custList.add(cust);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return custList;
    }

    /**
     * Passes SQL command to database and adds new Customer to customers table
     *
     * @param custName
     * @param updateBy
     * @param
     * @return integer
     */

    public static int addCust(String custName, String custPhone, String custStreet, String custCity, String custState, int custPostal, String createdBy, String updateBy) {

        try {
            String sql = "INSERT INTO customers (Customer_Name, Phone, Street_Address, City, State, Zip_Code, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?,  now(), ?, now(), ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, custName);
            ps.setString(2, custPhone);
            ps.setString(3, custStreet);
            ps.setString(4, custCity);
            ps.setString(5,custState);
            ps.setInt(6, custPostal);
            ps.setString(7, createdBy);
            ps.setString(8, updateBy);

            return ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Passes SQL command to update the current customer based on their ID with the new information provided by the user
     *
     */
    public static int editCust(int custId, String custName, String custPhone, String custStreet, String custCity, String custState, int custPostal, String updateBy) {

        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Phone = ?, Street_Address = ?, City = ?, State = ?, Zip_Code = ?, Last_Update = now(), Last_Updated_By = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, custName);
            ps.setString(2, custPhone);
            ps.setString(3, custStreet);
            ps.setString(4,custCity);
            ps.setString(5,custState);
            ps.setInt(6, custPostal);
            ps.setString(7, updateBy);
            ps.setInt(8, custId);

            return ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Passes SQL command to delete row from customers table based on customer_ID
     *
     */

    public static int deleteCust(int custId) {

        try {
            String sql = "DELETE from customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, custId);

            return ps.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}