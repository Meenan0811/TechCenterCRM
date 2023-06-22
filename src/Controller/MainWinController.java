package Controller;

import DBAccess.CustomerSQL;
import DBAccess.EmployeeSQL;
import DBAccess.RepairSQL;
import Helper.Alerts;
import Helper.JDBC;
import Helper.Scenes;
import Model.Customers;
import Model.Employee;
import Model.Repair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

public class MainWinController implements Initializable {


    /**
     *MainWindow controller, populates Appointment and customer windows. Passes selected data from Main Controller to next screen
     *
     *
     *
     * @author Matt Meenan
     */

        @FXML
        private Button addCustButton;
        @FXML
        private Button updateCustButton;
        @FXML
        private Button custDeleteButton;
        @FXML
        private Button reportButton;
        @FXML
        private Button logoutButton;
        @FXML
        private TableColumn custNameCol;
        @FXML
        private TableColumn custPhoneCol;
        @FXML
        private TableColumn custRepairIdCol;
        @FXML
        private TableColumn deviceCol;
        @FXML
        private TableColumn statusCol;
        @FXML
        private TableColumn createDateCol;
        @FXML
        private TableColumn dueDateCol;
        @FXML
        private TableColumn assignedEmplCol;
        @FXML
        private TableView repairTable;
        @FXML
        private Button addEmployeeButton;
        @FXML
        private Button updateEmployeeButton;
        @FXML
        private Button employeeDeleteButton;
        @FXML
        private RadioButton weekRadio;
        @FXML
        private RadioButton monthRadio;
        @FXML
        private RadioButton allRadio;
        @FXML
        private TableView emplTable;
        @FXML
        private TableColumn emplIdCol;
        @FXML
        private TableColumn emplNameCol;
        @FXML
        private TableColumn employeeLocCol;


        private LocalDate currDate = LocalDate.now();
        private ObservableList<Repair> allcustRepair = FXCollections.observableArrayList();
        private ObservableList<Employee> allEmployee = FXCollections.observableArrayList();

        public static Object passAppt;
        public static Repair passCust;


    /**
         * OverRides initialize method of Initializable class
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            allEmployee = EmployeeSQL.allEmployees();
            allcustRepair = RepairSQL.allCustomerRepairs();
            allSelected();
            setEmployeeTable(allEmployee);

        }

        public void setCustTable(ObservableList<Repair> all) {
            repairTable.setItems(all);
            
            custNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("custName"));
            custPhoneCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("custPhone"));
            custRepairIdCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("repairId"));
            createDateCol.setCellValueFactory(new PropertyValueFactory<Repair, LocalDate>("createDate"));
            deviceCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("device"));
            dueDateCol.setCellValueFactory(new PropertyValueFactory<Repair, LocalDate>("dueDate"));
            assignedEmplCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("assgnempl"));
            custRepairIdCol.setCellValueFactory(new PropertyValueFactory<Repair, Integer>("repairId"));
            statusCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("status"));
        }
        
        public void setEmployeeTable(ObservableList<Employee> allEmployee){
            emplTable.setItems(allEmployee);

            //emplIdCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeID"));
            emplNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
            employeeLocCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeLoc"));
        }

    public void allSelected() {
        monthRadio.setSelected(false);
        weekRadio.setSelected(false);
        allRadio.setSelected(true);

        setCustTable(allcustRepair);
    }

     public void toAddCust(ActionEvent event) throws IOException {
         Scenes.toAddCust(event);
     }

     public void toAddEmpl(ActionEvent event) throws IOException {
            Scenes.toAddEmpl(event);
     }

     public void deleteRepair(ActionEvent event) {
            Repair repair = Repair.class.cast(repairTable.getSelectionModel().getSelectedItem());
            Alerts.deleteRepair(repair);
            try {
                Scenes.toMain(event);
            }catch(IOException e) {
                e.printStackTrace();
            }
        }


     public void editRepair(ActionEvent event) throws IOException {
            passCust = Repair.class.cast(repairTable.getSelectionModel().getSelectedItem());
            Scenes.toAddCust(event);
     }


     public void logout(ActionEvent event) {
         JDBC.closeConnection();
         System.exit(0);
     }

     public void toNewRepair(ActionEvent event) {

     }



}
