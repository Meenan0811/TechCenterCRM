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
                String device = rs.getString(2);
                int custID = rs.getInt(3);
                int partID = rs.getInt(4);
                LocalDateTime createDate = rs.getTimestamp(5).toLocalDateTime();
                String createBy = rs.getString(6);
                LocalDateTime lastUpdate = rs.getTimestamp(7).toLocalDateTime();
                String updateBy = rs.getString(8);
                LocalDateTime dueDate = rs.getTimestamp(9).toLocalDateTime();
                String status = rs.getString(10);
                String assgnEmpl = rs.getString(11);
                String notes = rs.getString(12);


                Repair repair = new Repair(repairID, device, custID, partID, createDate, createBy, lastUpdate, updateBy, dueDate, status, assgnEmpl, notes);
                allrepair.add(repair);


            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return allrepair;
    }

    public static int addRepair(String device, int customerID, int partID, String notes, String updateBy, LocalDateTime dueDate, String status, String assgnEmpl)  {

        try {
            String sql = "INSERT INTO repairs(Device, Customer_ID, Part_ID, Notes, Last_Update, Last_Updated_By, Quoted_Due_Date, Status, Assigned_Employee) VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, device );
            ps.setInt(2, customerID);
            ps.setInt(3, partID);
            ps.setString(4, notes);
            ps.setString(6, updateBy);
            ps.setTimestamp(7, Timestamp.valueOf(dueDate));
            ps.setString(8, status);
            ps.setString(9, assgnEmpl);

            return ps.executeUpdate();

        }catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int editRepair(String device, int customerID, int partID, String updateBy, LocalDateTime dueDate, String status, String assgnEmpl, String notes) {

        try {
            String sql = "UPDATE repairs SET Device = ?, Customer_ID = ?, Part_ID = ?, Last_Update = NOW(), Last_Updated_By = ?, Quoted_Due_Date = ?, Status = ?, Assigend_Employee = ?  Notes = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, device);
            ps.setInt(2, customerID);
            ps.setInt(3, partID);
            ps.setString(4, updateBy);
            ps.setTimestamp(5, Timestamp.valueOf(dueDate));
            ps.setString(6, status);
            ps.setString(7, assgnEmpl);
            ps.setString(8, notes);

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
            String sql = "SELECT customers.Customer_Name, customers.Phone, customers.Create_Date, repairs.Repair_ID, repairs.Device, repairs.Quoted_Due_Date, repairs.Assigned_Employee, repairs.Status, repairs.Notes\n" +
                    "FROM repairs\n" +
                    "INNER JOIN customers on Repairs.Customer_ID = customers.Customer_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String custName = rs.getString(1);
                System.out.println("Name " + custName);
                String custPhone = rs.getString(2);
                LocalDateTime createDate = rs.getTimestamp(3).toLocalDateTime();
                int repairID = rs.getInt(4);
                LocalDateTime dueDate = rs.getTimestamp(5).toLocalDateTime();
                String status = rs.getString(6);
                String assgnEmpl = rs.getString(7);
                String repairNotes = rs.getString(8);

                Repair repair = new Repair(custName, custPhone, createDate, repairID, dueDate, status, assgnEmpl, repairNotes);
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
