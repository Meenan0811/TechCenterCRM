package Controller;

import Model.Customers;
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
        private Button apptAddButton;
        @FXML
        private Button apptUpdateButton;
        @FXML
        private Button apptDeleteButton;
        @FXML
        private Button reportButton;
        @FXML
        private Button logoutButton;
        @FXML
        private TableColumn apptIdCol;
        @FXML
        private TableView apptTable;
        @FXML
        private TableColumn titleCol;
        @FXML
        private TableColumn descriptionCol;
        @FXML
        private TableColumn locCol;
        @FXML
        private TableColumn contactCol;
        @FXML
        private TableColumn typeCol;
        @FXML
        private TableColumn startCol;
        @FXML
        private TableColumn endCol;
        @FXML
        private TableColumn apptCustIdCol;
        @FXML
        private TableColumn userIdCol;
        @FXML
        private TableView custTable;
        @FXML
        private TableColumn custIdCol;
        @FXML
        private TableColumn nameCol;
        @FXML
        private TableColumn phoneCol;
        @FXML
        private TableColumn addressCol;
        @FXML
        private TableColumn divIdCol;
        @FXML
        private TableColumn postalCol;
        @FXML
        private Button custAddButton;
        @FXML
        private Button custUpdateButton;
        @FXML
        private Button custDeleteButton;
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

        }

}
