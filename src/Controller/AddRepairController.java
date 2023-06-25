package Controller;

import DBAccess.CustomerSQL;
import DBAccess.EmployeeSQL;
import DBAccess.PartsSQL;
import DBAccess.RepairSQL;
import Helper.Alerts;
import Helper.Scenes;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddRepairController implements Initializable {

    @FXML
    private Button saveDtButton;
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
    private Label partsUsedLabel;
    @FXML
    private RadioButton dataTransferRadio;
    @FXML
    private TextField repairNotesText;
    @FXML
    private DatePicker tatDatepicker;
    @FXML
    private Button saveRepairbutton;
    private String device;
    private String notes;
    private String status;
    private LocalDateTime dueDate;
    private String assgnEmpl;
    private int repairID = -1;
    private ObservableList<Repair> allRepair = FXCollections.observableArrayList();
    private ObservableList<String> devices = FXCollections.observableArrayList("Personal Computer", "Tablet", "Phone");
    private ObservableList<String> repairStatus = FXCollections.observableArrayList("Awating Triage", "Awaiting Parts", "In Repair", "Completed");
    private ObservableList<Employee> allEmpl = EmployeeSQL.allEmployees();
    private ObservableList<Customers> allCust = FXCollections.observableArrayList();
    private ObservableList<Parts> allParts = FXCollections.observableArrayList();
    private ObservableList<DataTransfer> allDt = FXCollections.observableArrayList();
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
        deviceTypeCombo.setItems(devices);
        tatDatepicker.setValue(LocalDate.now().plusDays(3));

        if (MainWinController.passRepair != null && MainWinController.passRepair.getType().equals("Repair")) {
            repairID = MainWinController.passRepair.getRepairId();
            currRepair = getRepair(repairID);
            statusCombo.setValue(currRepair.getStatus());
            assgnEmplCombo.setValue(currRepair.getAssgnempl());
            deviceTypeCombo.setValue(currRepair.getDevice());
            repairNotesText.setText(currRepair.getNotes());
            tatDatepicker.setValue(currRepair.getDueDate());
            custNameCombo.setValue(getCustomer(currRepair.getCustomerId()));
            if (currRepair.getType().equals("Repair")) {
                partCombo.setValue(getPart(currRepair.getPartId()));
                updatePartQty();
            }
        }

        if (MainWinController.passRepair != null && MainWinController.passRepair.getType().equals("DT")) {
            DataTransfer dt = null;
            allDt = RepairSQL.getAllDt();
            for (DataTransfer d : allDt) {
                if (d.getRepairId() == MainWinController.passRepair.getRepairId()) {
                    dt = d;
                }
            }
            if (dt != null) {
                repairID = MainWinController.passRepair.getRepairId();
                currRepair = getRepair(repairID);
                statusCombo.setValue(currRepair.getStatus());
                assgnEmplCombo.setValue(currRepair.getAssgnempl());
                deviceTypeCombo.setValue(currRepair.getDevice());
                repairNotesText.setText(currRepair.getNotes());
                tatDatepicker.setValue(currRepair.getDueDate());
                custNameCombo.setValue(getCustomer(currRepair.getCustomerId()));
                partCombo.setItems(devices);
                partCombo.setValue(dt.getOldDevice());
                dataTransferRadio.setSelected(true);
            }

        }
    }

    /**
     * Method to change Part Use label and combo box to Old device label and combo box for a Data Transfer
     */
    public void dataTransferSelected() {
        partsUsedLabel.setText("Old Device");
        partCombo.setItems(devices);
        partCombo.setValue("Personal Computer");
        saveRepairbutton.setVisible(false);
        saveDtButton.setVisible(true);
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

    /**
     * Method to parse information from textFields and boxes and call either RepairSQL addRepair if npo repairID was passed, or edit Repair if existing repair ID passed
     * @param event
     */
    @FXML
    private void saveRepair(ActionEvent event) throws IOException{

        try {
            String device = deviceTypeCombo.getValue().toString();
            String status = statusCombo.getValue().toString();
            LocalDate dueDate = tatDatepicker.getValue();
            String empl = assgnEmplCombo.getValue().toString();
            String custName = custNameCombo.getValue().toString();
            int custID = 0;
            String notes = repairNotesText.getText();
            String currUser = LoginController.currUser;
            String oldDevice = null;
            int partID = -1;
            String part = null;
            String type = null;
            if (partCombo.getValue() != null) {
                part = partCombo.getValue().toString();
                partID = 0;
            }

            for (Customers c : allCust) {
                if (c.getCustName().equals(custName)) {
                    custID = c.getCustId();
                }
            }

            for (Parts p : allParts) {
                if (p.getPartName().equals(part)) {
                    partID = p.getPartID();
                }
            }
            System.out.println("Data Transfer Radio: " + dataTransferRadio.isSelected() + " Repair ID: " + repairID);

            if (!device.isEmpty() && !status.isEmpty() && !empl.isEmpty() && !custName.isEmpty() && !notes.isEmpty() && dueDate != null) {
                if (repairID > 0) {
                    if (!dataTransferRadio.isSelected()) {
                        type = "Repair";
                        RepairSQL.editRepair(device, custID, partID, currUser, dueDate, status, empl, notes, type, repairID);
                        //Scenes.toMain(event);
                        System.out.println("EditRepair entered");
                    } else {
                        type = "Repair";
                        RepairSQL.addRepair(device, custID, partID, notes, currUser, dueDate, status, empl, LoginController.currUser, type);
                        //Scenes.toMain(event);
                        System.out.println("NewRepair entered");
                    }
                }
            } else {
                Alerts.alertMessage(4);
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
            Alerts.alertMessage(4);
        }catch (NumberFormatException n) {
            n.printStackTrace();
            Alerts.alertMessage(4);
        }
    }

    public void saveDT(ActionEvent event) {
        try {
            String device = deviceTypeCombo.getValue().toString();
            String status = statusCombo.getValue().toString();
            LocalDate dueDate = tatDatepicker.getValue();
            String empl = assgnEmplCombo.getValue().toString();
            String custName = custNameCombo.getValue().toString();
            String notes = repairNotesText.getText();
            String currUser = LoginController.currUser;
            String oldDevice = partCombo.getValue().toString();
            String type;
            int custID = 0;
            int partID = 1;

            for (Customers c : allCust) {
                if (c.getCustName().equals(custName)) {
                    custID = c.getCustId();
                }
            }
            System.out.println("Data Transfer Radio: " + dataTransferRadio.isSelected() + " Repair ID: " + repairID);

            if (!device.isEmpty() && !status.isEmpty() && !empl.isEmpty() && !custName.isEmpty() && !notes.isEmpty() && dueDate != null) {
                if (repairID > 0) {
                    type = "DT";
                    RepairSQL.editDataTransfer(device, custID, partID, currUser, dueDate, status, empl, notes, oldDevice, repairID, type);
                    //Scenes.toMain(event);
                    System.out.println("EditDT entered");
                }else {
                    type = "DT";
                    RepairSQL.addDataTransfer(device, custID, partID, notes, currUser, dueDate, status, empl, LoginController.currUser, oldDevice, type);
                    //Scenes.toMain(event);
                    System.out.println("NewDT entered");
                }
            } else {
                Alerts.alertMessage(4);
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
            Alerts.alertMessage(4);
        }catch (NumberFormatException n) {
            n.printStackTrace();
            Alerts.alertMessage(4);
        }
    }

}
