package DBAccess;

import Helper.JDBC;
import Model.Repair;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
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
                LocalDate dueDate = rs.getDate(9).toLocalDate();
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

    public static int addRepair(String device, int customerID, int partID, String notes, String updateBy, LocalDate dueDate, String status, String assgnEmpl, String createBy)  {

        try {
            String sql = "INSERT INTO repairs(Device, Customer_ID, Part_ID, Notes, Last_Update, Last_Updated_By, Quoted_Due_Date, Status, Assigned_Employee, Create_Date, Created_By) VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?, NOW(), ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, device );
            ps.setInt(2, customerID);
            ps.setInt(3, partID);
            ps.setString(4, notes);
            ps.setString(5, updateBy);
            ps.setDate(6, Date.valueOf(dueDate));
            ps.setString(7, status);
            ps.setString(8, assgnEmpl);
            ps.setString(9, createBy);

            return ps.executeUpdate();

        }catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int editRepair(String device, int customerID, int partID, String updateBy, LocalDate dueDate, String status, String assgnEmpl, String notes, int repairID) {

        try {
            String sql = "UPDATE repairs SET Device = ?, Customer_ID = ?, Part_ID = ?, Last_Update = NOW(), Last_Updated_By = ?, Quoted_Due_Date = ?, Status = ?, Assigend_Employee = ?  Notes = ? WHERE Repair_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, device);
            ps.setInt(2, customerID);
            ps.setInt(3, partID);
            ps.setString(4, updateBy);
            ps.setDate(5, Date.valueOf(dueDate));
            ps.setString(6, status);
            ps.setString(7, assgnEmpl);
            ps.setString(8, notes);
            ps.setInt(9, repairID);

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
            String sql = "SELECT customers.Customer_Name, customers.Phone, customers.Create_Date, repairs.Repair_ID, repairs.Device, repairs.Quoted_Due_Date, repairs.Status, repairs.Assigned_Employee, repairs.Notes, customers.Customer_ID\n" +
                    "FROM repairs\n" +
                    "INNER JOIN customers on repairs.Customer_ID = customers.Customer_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                String custName = rs.getString(1);
                String custPhone = rs.getString(2);
                LocalDateTime createDate = rs.getTimestamp(3).toLocalDateTime();
                int repairID = rs.getInt(4);
                String device = rs.getString(5);
                LocalDate dueDate = rs.getDate(6).toLocalDate();
                String status = rs.getString(7);
                String assgnEmpl = rs.getString(8);
                String repairNotes = rs.getString(9);
                int customerID = rs.getInt(10);
                System.out.println(status + " " + assgnEmpl + " " + repairID);

                Repair repair = new Repair(device, custName, custPhone, createDate, repairID, dueDate, status, assgnEmpl, repairNotes, customerID);
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
