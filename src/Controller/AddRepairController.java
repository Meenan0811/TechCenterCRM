package Controller;

import DBAccess.CustomerSQL;
import DBAccess.EmployeeSQL;
import DBAccess.RepairSQL;
import Helper.Scenes;
import Model.Customers;
import Model.Employee;
import Model.Repair;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddRepairController implements Initializable {
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
    private ObservableList<String> emplName;
    private Repair currRepair;
    private Customers currCust;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emplName = getEmplNAme();
        allRepair = RepairSQL.getAllRepairs();

        statusCombo.setItems(repairStatus);
        assgnEmplCombo.setItems(emplName);


        if (MainWinController.passRepair != null) {
            repairID = MainWinController.passRepair.getRepairId();
            currRepair = getRepair(repairID);
            statusCombo.setValue(currRepair.getStatus());
            assgnEmplCombo.setValue(currRepair.getAssgnempl());
            deviceTypeCombo.setValue(currRepair.getDevice());
            repairNotesText.setText(currRepair.getNotes());
            tatDatepicker.setValue(currRepair.getDueDate().toLocalDate());
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
     * Method to retreive Customer Object based on passed customer ID
     * @param customerID
     * @return Customers
     */
    public Customers getCustomer(int customerID) {
        ObservableList<Customers> allCust = CustomerSQL.getAllCust();
        Customers cust = null;
        try {
            for (Customers c : allCust) {
                if (c.getCustId() == customerID) {
                    cust = c;
                }
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        return cust;
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

    @FXML
    private void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }
}
