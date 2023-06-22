package Controller;

import DBAccess.RepairSQL;
import Model.Repair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private ObservableList<String> repairStatus = FXCollections

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allRepair = RepairSQL.getAllRepairs();
        if (repairID != -1) {
            for (Repair r : allRepair) {
                if (repairID == r.getRepairId()){

                }
            }
        }

    }
}
