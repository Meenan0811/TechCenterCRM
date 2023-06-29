package DBAccess;

import Helper.JDBC;
import Model.DataTransfer;
import Model.Repair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                String type = rs.getString(14);


                Repair repair = new Repair(repairID, device, custID, partID, createDate, createBy, lastUpdate, updateBy, dueDate, status, assgnEmpl, notes, type);
                allrepair.add(repair);


            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return allrepair;
    }

    public static int addRepair(String device, int customerID, int partID, String notes, String updateBy, LocalDate dueDate, String status, String assgnEmpl, String createBy, String type)  {

        try {
            String sql = "INSERT INTO repairs(Device, Customer_ID, Part_ID, Notes, Last_Update, Last_Updated_By, Quoted_Due_Date, Status, Assigned_Employee, Create_Date, Created_By, Type) VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?, NOW(), ?, ?)";
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
            ps.setString(10, type);

            return ps.executeUpdate();

        }catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int editRepair(String device, int customerID, int partID, String updateBy, LocalDate dueDate, String status, String assgnEmpl, String notes, String type, int repairID) {

        try {
            String sql = "UPDATE repairs SET Device = ?, Customer_ID = ?, Part_ID = ?, Last_Update = NOW(), Last_Updated_By = ?, Quoted_Due_Date = ?, Status = ?, Assigned_Employee = ?,  Notes = ?, Type = ? WHERE Repair_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, device);
            ps.setInt(2, customerID);
            ps.setInt(3, partID);
            ps.setString(4, updateBy);
            ps.setDate(5, Date.valueOf(dueDate));
            ps.setString(6, status);
            ps.setString(7, assgnEmpl);
            ps.setString(8, notes);
            ps.setString(9, type);
            ps.setInt(10, repairID);


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


        try {

            String sql = "SELECT customers.Customer_Name, customers.Phone, customers.Create_Date, repairs.Repair_ID, repairs.Device, repairs.Quoted_Due_Date, repairs.Status, repairs.Assigned_Employee, repairs.Notes, customers.Customer_ID, repairs.Type\n" +
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
                String type = rs.getString(11);


                Repair repair = new Repair(device, custName, custPhone, createDate, repairID, dueDate, status, assgnEmpl, repairNotes, customerID, type);
                all.add(repair);
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return all;
    }

    public static int addDataTransfer(String device, int customerID, int partID, String notes, String updateBy, LocalDate dueDate, String status, String assgnEmpl, String createBy, String oldDevice, String type)  {

        try {
            String sql = "INSERT INTO repairs(Device, Customer_ID, Part_ID, Notes, Last_Update, Last_Updated_By, Quoted_Due_Date, Status, Assigned_Employee, Create_Date, Created_By, Old_Device, Type) VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?, NOW(), ?, ?, ?)";
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
            ps.setString(10,oldDevice);
            ps.setString(11, type);

            return ps.executeUpdate();

        }catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int editDataTransfer(String device, int customerID, int partID, String updateBy, LocalDate dueDate, String status, String assgnEmpl, String notes, String oldDevice, int repairID, String type) {

        try {
            String sql = "UPDATE repairs SET Device = ?, Customer_ID = ?, Part_ID = ?, Last_Update = NOW(), Last_Updated_By = ?, Quoted_Due_Date = ?, Status = ?, Assigned_Employee = ?,  Notes = ?, Old_Device = ?, Type = ? WHERE Repair_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, device);
            ps.setInt(2, customerID);
            ps.setInt(3, partID);
            ps.setString(4, updateBy);
            ps.setDate(5, Date.valueOf(dueDate));
            ps.setString(6, status);
            ps.setString(7, assgnEmpl);
            ps.setString(8, notes);
            ps.setString(9, oldDevice);
            ps.setString(10, type);
            ps.setInt(11, repairID);


            return ps.executeUpdate();
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static ObservableList<DataTransfer> getAllDt() {
        ObservableList<DataTransfer> allDt = FXCollections.observableArrayList();

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
                String oldDevice = rs.getString(13);
                String type = rs.getString(14);


                DataTransfer dt = new DataTransfer(repairID, device, custID, partID, createDate, createBy, lastUpdate, updateBy, dueDate, status, assgnEmpl, notes, oldDevice, type);
                allDt.add(dt);
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
        return allDt;
    }

    public static int updateAssignedEmployeeName(String updatedName, String oldName) {
       try {
           String sql = "UPDATE repairs SET Assigned_Employee = ? WHERE Assigned_Employee = ?";
           PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
           ps.setString(1, updatedName);
           ps.setString(2,oldName);

           return ps.executeUpdate();
       }catch (SQLException se) {
           se.printStackTrace();
       }
        return -1;
    }
}
