package Controller;

import DBAccess.EmployeeSQL;
import Helper.Alerts;
import Helper.Scenes;
import Model.Employee;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController  implements Initializable {

    @FXML
    private TextField employeeNametext;
    @FXML
    private TextField userNameText;
    @FXML
    private TextField pwText;
    @FXML
    private ComboBox locCombo;
    @FXML
    private ComboBox adminCombo;
    private String admin;
    private String emplName;
    private String userName;
    private String pw;
    private String location;
    private int emplID = -1;
    private ObservableList<String> adminBox = FXCollections.observableArrayList("No", "Yes");
    private ObservableList<String> noAdmin = FXCollections.observableArrayList("No");
    private ObservableList<String> loc = FXCollections.observableArrayList("001", "002");
    private ObservableList<String> locNonAdmin = FXCollections.observableArrayList();
    private ObservableList<Employee> allEmpl = FXCollections.observableArrayList();
    private Employee currEmpl = MainWinController.passEmployee;
    private Employee loggedInEmpl = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allEmpl = EmployeeSQL.allEmployees();
        adminCombo.setItems(adminBox);
        adminCombo.getSelectionModel().select(0);
        emplID = currEmpl.getEmployeeID();
        locCombo.setItems(loc);
        locCombo.getSelectionModel().select(0);

        for (Employee e : allEmpl) {
            if (e.getEmployeeID() == LoginController.currUserId && e.getAdmin().equals("Yes")) {
                loggedInEmpl = e;
            }
        }


        if (emplID > 0) {
            locNonAdmin.add(loggedInEmpl.getEmployeeLoc());
            emplID = currEmpl.getEmployeeID();
            employeeNametext.setText(currEmpl.getEmployeeName());
            employeeNametext.setEditable(false);
            locCombo.setItems(locNonAdmin);
            adminCombo.setItems(noAdmin);
            adminCombo.setEditable(false);
            userNameText.setText(currEmpl.getUserName());
            pwText.setText(currEmpl.getPassWord());

            if (loggedInEmpl != null && loggedInEmpl.getAdmin().equals("Yes")) {
                employeeNametext.setEditable(true);
                adminCombo.setItems(adminBox);
                adminCombo.getSelectionModel().select(0);
                locCombo.setItems(loc);
                locCombo.getSelectionModel().select(0);
            }
        }
    }

    public void saveEmpl(ActionEvent event) {

        try {
            emplName = employeeNametext.getText();
            userName = userNameText.getText();
            pw = pwText.getText();
            location = locCombo.getValue().toString();
            admin = adminCombo.getValue().toString();

            if (emplID > 0) {
                EmployeeSQL.editEmployee(emplID, emplName, userName, pw, location, admin);
                Scenes.toMain(event);
            }else {
                EmployeeSQL.addEmployee(emplName, userName, pw, location, admin);
                Scenes.toMain(event);
            }
        }catch (NullPointerException | NumberFormatException | IOException e) {
            Alerts.alertMessage(4);
            e.printStackTrace();
        }

    }

    /*private Boolean validateLocation(String loc) {
        Boolean validate = false;
        try {
            int location = Integer.parseInt(loc);
            if (location == 001 || location == 002) {
                validate = true;
            }else {
                Alerts.alertMessage(12);
            }
        }catch(NumberFormatException | NullPointerException e) {
            Alerts.alertMessage(12);
            e.printStackTrace();
        }
        return validate;
    }*/

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }
}
