package Controller;

import DBAccess.CustomerSQL;
import DBAccess.EmployeeSQL;
import DBAccess.PartsSQL;
import DBAccess.RepairSQL;
import Helper.Scenes;
import Model.Customers;
import Model.Employee;
import Model.Parts;
import Model.Repair;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddRepairController implements Initializable {
    @FXML
    private Label partQtyLabel;
    @FXML
    private ComboBox custNameCombo;
    @FXML
    private ComboBox partCombo;
    @FXML
    private ComboBox statusCombo;
    @FXML
    private ComboBox assgnEmplCombo;
    @FXML
    private ComboBox deviceTypeCombo;
    @FXML
    private TextField repairNotesText;
    @FXML
    private DatePicker tatDatepicker;
    @FXML
    private Button saveRepairbutton;
    @FXML
    private Button cancelRepairButton;
    private String device;
    private String notes;
    private String status;
    private LocalDateTime dueDate;
    private String assgnEmpl;
    private int repairID = -1;
    private ObservableList<Repair> allRepair = FXCollections.observableArrayList();
    private ObservableList<String> repairStatus = FXCollections.observableArrayList("Awating Triage", "Awaiting Parts", "In Repair", "Completed");
    private ObservableList<Employee> allEmpl = EmployeeSQL.allEmployees();
    private ObservableList<Customers> allCust = FXCollections.observableArrayList();
    private ObservableList<Parts> allParts = FXCollections.observableArrayList();
    private ObservableList<String> partName;
    private ObservableList<String> custName;
    private ObservableList<String> emplName;
    private Repair currRepair;
    private Customers currCust;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emplName = getEmplNAme();
        allParts = PartsSQL.getParts();
        allRepair = RepairSQL.getAllRepairs();
        allCust = CustomerSQL.getAllCust();
        custName = getCustName();
        partName = getAllParts();

        statusCombo.setItems(repairStatus);
        assgnEmplCombo.setItems(emplName);
        custNameCombo.setItems(custName);
        partCombo.setItems(partName);



        if (MainWinController.passRepair != null) {
            repairID = MainWinController.passRepair.getRepairId();
            currRepair = getRepair(repairID);
            statusCombo.setValue(currRepair.getStatus());
            assgnEmplCombo.setValue(currRepair.getAssgnempl());
            deviceTypeCombo.setValue(currRepair.getDevice());
            repairNotesText.setText(currRepair.getNotes());
            tatDatepicker.setValue(currRepair.getDueDate().toLocalDate());
            custNameCombo.setValue(getCustomer(currRepair.getCustomerId()));
            partCombo.setValue(getPart(currRepair.getPartId()));
            updatePartQty();
        }
    }

    /**
     * Method to retrieve Repair Object based on passed Repair ID
     * @param repairID
     * @return Repair
     */
    public Repair getRepair(int repairID) {
        ObservableList<Repair> allRepair = RepairSQL.getAllRepairs();
        Repair repair = null;
        try {
            for (Repair r : allRepair) {
                if (r.getRepairId() == repairID) {
                    repair = r;
                }
            }

        }catch(NullPointerException e) {
            e.printStackTrace();
        }
        return repair;
    }

    /**
     * Method to retreive Customer Name based on passed customer ID
     * @param customerID
     * @return String
     */
    public String getCustomer(int customerID) {
        ObservableList<Customers> allCust = CustomerSQL.getAllCust();
        String custName = null;
        try {
            for (Customers c : allCust) {
                if (c.getCustId() == customerID) {
                    custName = c.getCustName();
                }
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        return custName;
    }

    /**
     * Method to obtain all Employee names for combo Box
     * @return
     */
    public ObservableList<String> getEmplNAme() {
        ObservableList<String> empl = FXCollections.observableArrayList();
        String currName;
        for (Employee e : allEmpl) {
            currName = e.getEmployeeName();
            empl.add(currName);
        }
        return empl;
    }

    /**
     * Method to retrieve a list of Customer names for combo box
     * @return
     */
    public ObservableList<String> getCustName() {
        ObservableList<String> cust = FXCollections.observableArrayList();
        String currName;
        for (Customers c : allCust) {
            currName = c.getCustName();
            cust.add(currName);
        }
        return cust;
    }

    /**
     * Method to reteive a List of Part Names for Combo box
     * @return
     */
    public  ObservableList<String> getAllParts() {
        ObservableList<String> partName = FXCollections.observableArrayList();
        String currPart;
        for (Parts p : allParts) {
            currPart = p.getPartName();
            partName.add(currPart);
        }
        return partName;
    }

    /**
     * Method to retrieve Part Name based on Passed Part ID
     * @param partID
     * @return
     */
    public String getPart(int partID) {
        String partName = null;
        for (Parts p : allParts) {
            if (p.getPartID() == partID) {
                partName = p.getPartName();
            }
        }
        return partName;
    }

    public void updatePartQty() {
        String partName = partCombo.getSelectionModel().toString();
        for (Parts p : allParts) {
            if (p.getPartName().equals(partName)) {
                partQtyLabel.setText(String.valueOf(p.getQty()));
            }
        }
    }

    @FXML
    private void toMain(ActionEvent event) throws IOException {
        MainWinController.passRepair = null;
        Scenes.toMain(event);
    }

    @FXML
    private void saveRepair(ActionEvent event) {
        String device = deviceTypeCombo.getSelectionModel().toString();
        String status = statusCombo.getSelectionModel().toString();
        String dueDate = tatDatepicker.getValue().toString();
        String empl = assgnEmplCombo.getValue().toString();
        String custName = custNameCombo.getSelectionModel().toString(); // Need ID
        String part = partCombo.getSelectionModel().toString(); // Need ID

        if (repairID > 0) {
        }
    }
}
