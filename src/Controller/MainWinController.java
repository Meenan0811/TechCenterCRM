package Controller;

import DBAccess.CustomerSQL;
import DBAccess.EmployeeSQL;
import DBAccess.RepairSQL;
import Helper.Alerts;
import Helper.JDBC;
import Helper.Scenes;
import Model.Customers;
import Model.DataTransfer;
import Model.Employee;
import Model.Repair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
        private TableColumn typeCol;
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
        @FXML
        private TableColumn custIDCol;
        @FXML
        private TableView custTable;
        @FXML
        private TableColumn custIdCol2;
        @FXML
        private TableColumn custNameCol2;


        private LocalDate currDate = LocalDate.now();
        private ObservableList<Repair> allcustRepair = FXCollections.observableArrayList();
        private ObservableList<Employee> allEmployee = FXCollections.observableArrayList();
        private ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

        public static Employee passEmployee;
        public static Repair passRepair;
        public static Customers passCust;


    /**
         * OverRides initialize method of Initializable class
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            allEmployee = EmployeeSQL.allEmployees();
            allcustRepair = RepairSQL.allCustomerRepairs();
            allCustomers = CustomerSQL.getAllCust();
            allSelected();
            setEmployeeTable(allEmployee);
            setCustTable(allCustomers);

        }

        public void setRepairTable(ObservableList<Repair> all) {
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
            custIDCol.setCellValueFactory(new PropertyValueFactory<Repair, Integer>("customerId"));
            typeCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("type"));
        }

        public void setCustTable(ObservableList<Customers> allCust) {
            custTable.setItems(allCust);

            custIdCol2.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("custId"));
            custNameCol2.setCellValueFactory(new PropertyValueFactory<Customers, String>("custName"));
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

        setRepairTable(allcustRepair);
    }

     public void toAddCust(ActionEvent event) throws IOException {
            Scenes.toAddCust(event);
     }

     public void toEditCust(ActionEvent event) throws IOException {
            passCust = Customers.class.cast(custTable.getSelectionModel().getSelectedItem());
            Scenes.toAddCust(event);
     }

     public void deleteCust(ActionEvent event) throws IOException {
            Customers cust = Customers.class.cast(custTable.getSelectionModel().getSelectedItem());
            int custID = cust.getCustId();
            CustomerSQL.deleteCust(custID);
            Scenes.toMain(event);
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


     //FIXME: Delete if not needed
        /*public void editRepair(ActionEvent event) throws IOException {
            passRepair = Repair.class.cast(repairTable.getSelectionModel().getSelectedItem());
            Scenes.toAddCust(event);
     }*/


     public void logout(ActionEvent event) {
         JDBC.closeConnection();
         System.exit(0);
     }

     public void toNewRepair(ActionEvent event) throws IOException {
            Scenes.toAddRepair(event);
     }

     public void toEditRepair(ActionEvent event) throws IOException {
         passRepair = Repair.class.cast(repairTable.getSelectionModel().getSelectedItem());
         Scenes.toAddRepair(event);
     }



}
