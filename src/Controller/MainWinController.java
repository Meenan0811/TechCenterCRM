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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
        @FXML
        private TextField searchRepairTextField;


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
            passCust = null;
            passRepair = null;
            passEmployee = null;

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
            System.out.println(allEmployee);
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
            if (custTable.getSelectionModel().getSelectedItem() != null) {
                passCust = Customers.class.cast(custTable.getSelectionModel().getSelectedItem());
                Scenes.toAddCust(event);
            }else {
                Alerts.alertMessage(3);
            }
     }

     public void deleteCust(ActionEvent event) throws IOException {
            Customers cust = Customers.class.cast(custTable.getSelectionModel().getSelectedItem());
            int custID = cust.getCustId();
            CustomerSQL.deleteCust(custID);
            Scenes.toMain(event);
     }

     public void toAddEmpl(ActionEvent event) throws IOException {
         for (Employee e : allEmployee) {
             if (e.getemployeeID() == LoginController.currUserId && e.getAdmin().equals("Yes")) {
                 Scenes.toAddEmpl(event);
             } else {
                 Alerts.alertMessage(11);
             }
         }
     }

     public void toEditEmpl(ActionEvent event) throws IOException {
            if (emplTable.getSelectionModel().getSelectedItem() != null) {
                passEmployee = Employee.class.cast(emplTable.getSelectionModel().getSelectedItem());
                Scenes.toAddEmpl(event);
            }else {
                Alerts.alertMessage(10);
            }
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

        public void toParts(ActionEvent event) throws IOException {
            Scenes.toParts(event);
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

     public void toReports(ActionEvent event) throws IOException {
         Scenes.toReports(event);
     }

    /**
     * Method to Search Customer Repairs by Customer Name
     * @param partialName
     * @return
     */
    private ObservableList<Repair> customerSearchName(String partialName) {
        ObservableList<Repair> searchName = FXCollections.observableArrayList();

        for (Repair r : allcustRepair) {
            if (r.getCustName().toLowerCase().contains(partialName.toLowerCase())) searchName.add(r);
        }
        return searchName;
    }

    /**
     * Method to search repairs by status
     * @param repairStatus
     * @return
     */
    private ObservableList<Repair> repairStatusSearch(String repairStatus) {
        ObservableList<Repair> searchStatus = FXCollections.observableArrayList();

        for (Repair r : allcustRepair) {
            if (r.getStatus().toLowerCase().contains(repairStatus.toLowerCase())) searchStatus.add(r);
        }
        return searchStatus;
    }

    /**
     * Method to search repairs by device
     * @param deviceSearch
     * @return
     */
    private ObservableList<Repair> deviceSearch(String deviceSearch) {
        ObservableList<Repair> searchDevice = FXCollections.observableArrayList();

        for (Repair r : allcustRepair) {
            if (r.getDevice().toLowerCase().contains(deviceSearch.toLowerCase())) searchDevice.add(r);
        }
        return searchDevice;
    }

    public void search(ActionEvent event) {
        String search = searchRepairTextField.getText();
        ObservableList<Repair> searchResults = customerSearchName(search);

        if (searchResults.size() == 0) {
            searchResults = repairStatusSearch(search);
        }
        if (searchResults.size() == 0) {
            searchResults = deviceSearch(search);
        }
        if (searchResults.size() == 0) {
            Alerts.alertMessage(9);
        }

        setRepairTable(searchResults);
    }



}
