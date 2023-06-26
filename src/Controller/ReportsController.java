package Controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReportsController<Repairs> implements Initializable {

    @FXML
    private TableView table;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn phoneCol;
    @FXML
    private TableColumn dateInCol;
    @FXML
    private TableColumn dateDueCol;
    @FXML
    private TableColumn statusCol;
    @FXML
    private TableColumn assgnEmployeeCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private ComboBox repairStatusCombo;
    @FXML
    private ComboBox timeframeStatusCombo;
    @FXML
    private ComboBox emplloyeeNameCombo;
    @FXML
    private ComboBox timeFrameCombo;
    @FXML
    private Label numRepairsCompleteLabel;
    private ObservableList<Repair> joinedRepairs = FXCollections.observableArrayList();
    private ObservableList<Employee> allEmpl = FXCollections.observableArrayList();
    private ObservableList<Repair> tempRepair = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        joinedRepairs = RepairSQL.allCustomerRepairs();
        allEmpl = EmployeeSQL.allEmployees();

        setTable(joinedRepairs);




    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }

    public void repairsComplete(ActionEvent event) {

    }

    public void emplRepairsComplete(ActionEvent event) {

    }

    public void setTable(ObservableList<Repair> repairs) {
        table.setItems(repairs);

        nameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("custName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("custPhone"));
        dateInCol.setCellValueFactory(new PropertyValueFactory<Repair, LocalDate>("createDate"));
        dateDueCol.setCellValueFactory(new PropertyValueFactory<Repair, LocalDate>("dueDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("status"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("type"));
        assgnEmployeeCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("assgnempl"));
    }
}
