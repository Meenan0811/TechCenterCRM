package DBAccess;

import Helper.JDBC;
import Model.Parts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PartsSQL {

    public static ObservableList<Parts> getParts(){
        ObservableList<Parts> allParts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM parts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int partID = rs.getInt("Part_ID");
                String partName = rs.getString("Part_Name");
                int qty = rs.getInt("QTY_Available");

                Parts parts = new Parts(partID, partName, qty);
                allParts.add(parts);
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        return allParts;
    }

    public static int addPart(String partName, int qty) {
        try{
            String sql = "INSERT INTo parts(Part_Name, QTY_Available) VALUES(?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, partName);
            ps.setInt(2, qty);

            return ps.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int updatePart(int partID, String partName, int qty) {
        try {
            String sql = "UPDATE parts SET Part_Name = ?, QTY_Available = ? WHERE Part_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, partName);
            ps.setInt(2, qty);
            ps.setInt(3,partID);

            return ps.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    public static int deletePart(int partID) {
        try{
            String sql = "DELETE FROM parts WHERE Part_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, partID);

            return ps.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }
}
