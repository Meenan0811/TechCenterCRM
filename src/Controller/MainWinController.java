package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.net.URL;
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
        private ObservableList<Appt> allAppt = FXCollections.observableArrayList();
        private ObservableList<Appt> tempAppt = FXCollections.observableArrayList();

        public static Object passAppt;
        public static Object passCust;


        /**
         * OverRides initialize method of Initializable class
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

           /* allAppt = ApptSQL.getAppts();
            allSelected();
            custTable.setItems(CustomerSQL.getAllCust());
            custIdCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("custId"));
            nameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("phone"));
            addressCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
            divIdCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("divId"));
            postalCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("postal"));


        } */

        /**
         * Sets appointment table to only display Appointments that start in the current month
         */
        public void monthSelected() {
            tempAppt.clear();
            Month month = currDate.getMonth();
            Month tempMonth;
            int year = currDate.getYear();
            int tempYear;

            monthRadio.setSelected(true);
            weekRadio.setSelected(false);
            allRadio.setSelected(false);

            for (Appt a : allAppt) {
                tempMonth = a.getStart().getMonth();
                tempYear = a.getStart().getYear();
                if (tempMonth.equals(month) && year == tempYear) {
                    tempAppt.add(a);
                }
            }
            setApptTable(tempAppt);
        }

        /**
         * When week view radio button is selected loops through available appointments and displays all appointments within the next 7 days in appointments table
         *
         */
        public void weekSelected() {
            LocalDate date;
            tempAppt.clear();

            monthRadio.setSelected(false);
            weekRadio.setSelected(true);
            allRadio.setSelected(false);


            for(Appt a : allAppt) {
                date = a.getStart().toLocalDate();
                if (date.equals(currDate) || date.isAfter(currDate) && date.isBefore(currDate.plusDays(7))) {
                    tempAppt.add(a);
                }
            }
            setApptTable(tempAppt);
        }

        /**
         * Displays all available Appointments in Appointment table
         */
        public void allSelected() {
            monthRadio.setSelected(false);
            weekRadio.setSelected(false);
            allRadio.setSelected(true);

            setApptTable(allAppt);
        }

        /**
         * Passes ObservableList to apptTable and and columns
         * @param appt
         */
        public void setApptTable(ObservableList<Appt> appt) {
            apptTable.setItems(appt);
            apptIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("apptId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("description"));
            locCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("location"));
            contactCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("contactId"));
            typeCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("FormatStart"));
            endCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("FormatEnd"));
            apptCustIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("custId"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("userId"));
        }


        /**
         * Calls Add appointment screen
         * @param event
         * @throws IOException
         */
        public void toAddAppt(ActionEvent event) throws IOException {
            Scenes.toAddAppt(event);
        }

        /**
         * Call Edit Appointment screen and passes selected Appointment
         * @param event
         * @throws IOException
         */

        public void toEditAppt(ActionEvent event) throws IOException {
            passAppt = apptTable.getSelectionModel().getSelectedItem();
            if(passAppt != null) {
                Scenes.toEditAppt(event);
            }
            else {
                Alerts.alertMessage(2);
            }
        }

        /**
         * Calls the deleteAlert method and passes selected appointment to Alerts class, if user accepts prompt Appt object is passed to ApptSQL delete method and appointment is removed from the database
         * @param event
         * @throws IOException
         */
        public void deleteAppt(ActionEvent event) throws IOException {
            Appt appt = Appt.class.cast(apptTable.getSelectionModel().getSelectedItem());
            Alerts.deleteAlert(appt);
            Scenes.toMain(event);

        }

        /**
         * Calls toAddCust method of Scenes class
         * @param event
         * @throws IOException
         */
        public void toAddCust(ActionEvent event) throws IOException {
            Scenes.toAddCust(event);
        }

        public void toEditCust(ActionEvent event) throws IOException {
            passCust = custTable.getSelectionModel().getSelectedItem();
            if(passCust != null) {
                Scenes.toEditCust(event);
            }
            else {
                Alerts.alertMessage(3);
            }
        }

        /**
         * Calls the deleteCust method and passes selected customer object to Alerts class, if user accepts prompt customer object is passed to CustomerSQL delete method and customer and all associated appointment's are removed from the database
         * @param event
         * @throws IOException
         */
        public void deleteCust(ActionEvent event) throws IOException {
            try {
                Customers cust = Customers.class.cast(custTable.getSelectionModel().getSelectedItem());
                if (cust != null) {

                    Alerts.deleteCust(cust);
                    Scenes.toMain(event);
                } else {
                    throw new NullPointerException();
                }
            } catch (NullPointerException n) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Please select a Customer to delete");
                error.setHeaderText("Delete Customer");
                error.showAndWait();
            }
        }


        /**
         * Calls toReports method of Scenes class
         * @param event
         * @throws IOException
         */
        public void toReport(ActionEvent event) throws IOException {
            Scenes.toReports(event);
        }

        /**
         * Calls logout method of Scenes class
         * @param event
         * @throws IOException
         */
        public void logout(ActionEvent event) throws IOException {
            Scenes.logout(event);
        }

    }
