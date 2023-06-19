package DBAccess;

import Helper.JDBC;
import Model.Repair;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class RepairSQL {

    public static ObservableList<Repair> getAllRepairs() {
        ObservableList<Repair> allrepair = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM repairs";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int repairID = rs.getInt(1);
                int productID = rs.getInt(2);
                int custID = rs.getInt(3);
                int partID = rs.getInt(4);
                LocalDateTime createDate = rs.getTimestamp(5).toLocalDateTime();
                String createBy = rs.getString(6);
                LocalDateTime lastUpdate = rs.getTimestamp(7).toLocalDateTime();
                String updateBy = rs.getString(8);
                LocalDateTime dueDate = rs.getTimestamp(9).toLocalDateTime();
                String notes = rs.getString(10);


                Repair repair = new Repair(repairID, productID, custID, partID, notes);
                allrepair.add(repair);
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return allrepair;
    }

    public static int addRepair(int productID, int customerID, int partID, String notes, String emplName, LocalDateTime dueDate)  {

        try {
            String sql = "INSERT INTO repairs(Product_ID, Customer_ID, Part_ID, Notes, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, NOW(), ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, productID );
            ps.setInt(2, customerID);
            ps.setInt(3, partID);
            ps.setString(4, notes);
            ps.setString(6, emplName);
            ps.setTimestamp(7, Timestamp.valueOf(dueDate));

            return ps.executeUpdate();

        }catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int editRepair(int productId, int customerID, int partID, String notes) {

        try {
            String sql = "UPDATE repairs SET Product_ID = ?, Customer_ID = ?, Part_ID = ?, Notes = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, customerID);
            ps.setInt(3, partID);
            ps.setString(4, notes);

            return ps.executeUpdate();
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int deleteRepair(int repairID) {

        try {
            String sql = "DELETE FROM repairs WHERE Repair_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, repairID);

            return ps.executeUpdate();
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }


    public static ObservableList<Repair> allCustomerRepairs() {
        ObservableList<Repair> all = FXCollections.observableArrayList();
        System.out.println("CustomerRepair Called");

        try {
            System.out.println("Try Block Entered");
            String sql = "SELECT customers.Customer_Name, customers.Phone, customers.Create_Date, repairs.Repair_ID, repairs.Notes\n" +
                    "FROM repairs\n" +
                    "INNER JOIN customers on Repairs.Customer_ID = customers.Customer_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String custName = rs.getString(1);
                System.out.println("Name " + custName);
                String custPhone = rs.getString(2);
                LocalDate createDate = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
                int repairID = rs.getInt(4);
                String repairNotes = rs.getString(5);

                Repair repair = new Repair(custName, custPhone, createDate, repairID, repairNotes);
                System.out.println(repair.getCustName() + " " + repair.getRepairId() + " Repair object created");
                all.add(repair);
            }
        }catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Catch Block");

        }
        return all;

    }
}
