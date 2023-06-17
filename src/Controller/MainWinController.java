package Controller;

import DBAccess.CustomerSQL;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
        private TableColumn custIdCol;
        @FXML
        private TableView custTable;
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


        private LocalDate currDate = LocalDate.now();
        private ObservableList<Customers> allCust = FXCollections.observableArrayList();
        private ObservableList<Customers> tempCust = FXCollections.observableArrayList();

        public static Object passAppt;
        public static Object passCust;


    /**
         * OverRides initialize method of Initializable class
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            allCust = CustomerSQL.getAllCust();
            allSelected();


        }

        public void setCustTable(ObservableList<Customers> all) {
            custTable.setItems(all);
            custIdCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer> ("custId"));
            custNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("custName"));
            custPhoneCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("custPhone"));
            custRepairIdCol.setCellValueFactory(new PropertyValueFactory<Repair, String>("repairId"));
        }

    public void allSelected() {
        monthRadio.setSelected(false);
        weekRadio.setSelected(false);
        allRadio.setSelected(true);

        setCustTable(allCust);
    }

}
