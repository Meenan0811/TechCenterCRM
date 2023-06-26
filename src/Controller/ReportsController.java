package Controller;

import DBAccess.CustomerSQL;
import DBAccess.EmployeeSQL;
import DBAccess.RepairSQL;
import Helper.Scenes;
import Model.Customers;
import Model.Employee;
import Model.Repair;
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
import java.time.Month;
import java.util.ResourceBundle;

/**
 * Populates Repair table and includes methods to filter repairs based on repair status and timeframe as well as display about of repairs marked complete per employee based on a timeframe
 * @param <Repairs>
 *
 * @author Matthew Meenan
 */
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
    private LocalDate currDate;
    private ObservableList<Repair> joinedRepairs = FXCollections.observableArrayList();
    private ObservableList<Employee> allEmpl = FXCollections.observableArrayList();
    private ObservableList<Repair> tempRepair = FXCollections.observableArrayList();
    private ObservableList<String> timeFrame = FXCollections.observableArrayList("Week", "Month", "Year");
    private ObservableList<String> emplName = FXCollections.observableArrayList();
    private ObservableList<String> repairStatus = FXCollections.observableArrayList("Awating Triage", "Awaiting Parts", "In Repair", "Completed");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        joinedRepairs = RepairSQL.allCustomerRepairs();
        allEmpl = EmployeeSQL.allEmployees();
        currDate = LocalDate.now();

        for (Employee e : allEmpl) {
            String name = e.getEmployeeName();
            emplName.add(name);
        }

        setTable(joinedRepairs);

        emplloyeeNameCombo.setItems(emplName);
        emplloyeeNameCombo.getSelectionModel().select(0);
        timeFrameCombo.setItems(timeFrame);
        timeFrameCombo.getSelectionModel().select(0);
        timeframeStatusCombo.setItems(timeFrame);
        timeframeStatusCombo.getSelectionModel().select(0);
        repairStatusCombo.setItems(repairStatus);
        repairStatusCombo.getSelectionModel().select(1);
    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }

    /**
     * Method to search for employee Repairs completed based on chosen timeframe of current week, current month, or current Year
     * @return
     */
    private String  emplRepairsComplete() {
        ObservableList<Repair> searchEmpl = FXCollections.observableArrayList();
        ObservableList<Repair> allRepairs = RepairSQL.getAllRepairs();
        String empl = emplloyeeNameCombo.getValue().toString();
        String timeFrame = timeFrameCombo.getValue().toString();
        Month month = currDate.getMonth();
        int year = currDate.getYear();
        Month tempMonth;
        int tempYear = 0;

        if (timeFrame.equals("Year"))
            for (Repair r : allRepairs) {
                tempYear = r.getUpdateDate().getYear();
                if (r.getAssgnempl().toLowerCase().contains(empl.toLowerCase()) && tempYear == year) {
                    searchEmpl.add(r);

                }
            }

        if (timeFrame.equals("Month")) {
            for (Repair r : allRepairs) {
                tempMonth = r.getUpdateDate().getMonth();
                tempYear = r.getUpdateDate().getYear();
                System.out.println(tempMonth + "/" + tempYear + "Curr Month/Year " + month + "/" + year );
                if (r.getAssgnempl().toLowerCase().contains(empl.toLowerCase()) && tempMonth.equals(month) && tempYear == year) {
                    searchEmpl.add(r);
                }
            }
        }

        if (timeFrame.equals("Week")) {
            LocalDate date;
            for (Repair r : allRepairs) {
                date = r.getUpdateDate().toLocalDate();
                if (r.getAssgnempl().toLowerCase().contains(empl.toLowerCase()) && date.equals(currDate) || date.isAfter(currDate) && date.isBefore(currDate.plusDays(7))) {
                    searchEmpl.add(r);

                }
            }
        }
        return String.valueOf(searchEmpl.size());
    }

    /**
     * Calls emplRepairsComplete method when button is pressed
     * @param event
     */

    public void emplRepairSearch(ActionEvent event) {
        String numRepairs = emplRepairsComplete();
        numRepairsCompleteLabel.setText(numRepairs);
        System.out.println("Button Pressed");
    }

    private ObservableList<Repair> totalRepairsCompleted() {
        ObservableList<Repair> rep = FXCollections.observableArrayList();
        ObservableList<Repair> allRep = RepairSQL.getAllRepairs();
        Customers cust = null;
        String timeFrame = timeframeStatusCombo.getValue().toString();
        String status = repairStatusCombo.getValue().toString();
        Month month = currDate.getMonth();
        int year = currDate.getYear();
        Month tempMonth;
        int tempYear = 0;

        if (timeFrame.equals("Week")) {
            LocalDate date;
            for (Repair r : allRep) {
                date = r.getUpdateDate().toLocalDate();
                if (r.getStatus().toLowerCase().contains(status.toLowerCase()) && date.equals(currDate) || date.isAfter(currDate) && date.isBefore(currDate.plusDays(7))) {
                    cust = getCustomerNamePhone(r.getCustomerId());
                    Repair repair = new Repair(r.getDevice(), cust.getCustName(), cust.getCustPhone(), r.getCreateDate(), r.getRepairId(), r.getDueDate(), r.getStatus(), r.getAssgnempl(), r.getNotes(), r.getCustomerId(), r.getType());
                    rep.add(repair);
                }
            }
        }

        if (timeFrame.equals("Month")) {
            for (Repair r : allRep) {
                tempMonth = r.getUpdateDate().getMonth();
                tempYear = r.getUpdateDate().getYear();
                if (r.getStatus().toLowerCase().contains(status.toLowerCase()) && tempMonth.equals(month) && tempYear == year) {
                    cust = getCustomerNamePhone(r.getCustomerId());
                    Repair repair = new Repair(r.getDevice(), cust.getCustName(), cust.getCustPhone(), r.getCreateDate(), r.getRepairId(), r.getDueDate(), r.getStatus(), r.getAssgnempl(), r.getNotes(), r.getCustomerId(), r.getType());
                    rep.add(repair);
                }
            }
        }

        if (timeFrame.equals("Year")) {
            for (Repair r : allRep) {
                tempYear = r.getUpdateDate().getYear();
                if (r.getStatus().toLowerCase().contains(status.toLowerCase()) && tempYear == year) {
                    cust = getCustomerNamePhone(r.getCustomerId());
                    Repair repair = new Repair(r.getDevice(), cust.getCustName(), cust.getCustPhone(), r.getCreateDate(), r.getRepairId(), r.getDueDate(), r.getStatus(), r.getAssgnempl(), r.getNotes(), r.getCustomerId(), r.getType());
                    rep.add(repair);
                }
            }
        }
        return rep;
    }

    private Customers getCustomerNamePhone(int custID) {
        ObservableList<Customers> allCust = CustomerSQL.getAllCust();
        Customers currCust = null;

        for (Customers c : allCust) {
            if (c.getCustId() == custID) {
                currCust = c;
            }
        }
        return currCust;
    }

    public void callStatusSearch(ActionEvent event) {
        ObservableList<Repair> r = totalRepairsCompleted();
        setTable(r);
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
