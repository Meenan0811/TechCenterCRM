package Controller;

import DBAccess.EmployeeSQL;
import Helper.Alerts;
import Helper.Scenes;
import Model.Employee;
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
    private TextField locText;
    @FXML
    private ComboBox adminCombo;
    private String admin;
    private String emplName;
    private String userName;
    private String pw;
    private String location;
    private int emplID = -1;
    private ObservableList<String> adminBox = FXCollections.observableArrayList("No", "Yes");
    private Employee currEmpl = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminCombo.setItems(adminBox);
        adminCombo.getSelectionModel().select(1);

        if (emplID > 0) {
            currEmpl = MainWinController.passEmployee;
            employeeNametext.setText(currEmpl.getEmployeeName());
            employeeNametext.setEditable(false);
            locText.setText(currEmpl.getEmployeeLoc());
            locText.setEditable(false);
            adminCombo.setValue(currEmpl.getAdmin());
            adminCombo.setEditable(false);
            userNameText.setText(currEmpl.getUserName());
            pwText.setText(currEmpl.getPassWord());

            if (currEmpl.getAdmin().equals("Yes")) {
                employeeNametext.setEditable(true);
                adminCombo.setEditable(true);
                locText.setEditable(true);
            }
        }




    }

    public void saveEmpl(ActionEvent event) {

        try {
            emplName = employeeNametext.getText();
            userName = userNameText.getText();
            pw = pwText.getText();
            location = locText.getText();
            admin = adminCombo.getValue().toString();

            if (emplID > 0) {
                EmployeeSQL.editEmployee(emplID, emplName, userName, pw, location, admin);
            }else {
                EmployeeSQL.addEmployee(emplName, userName, pw, location, admin);
            }
        }catch (NullPointerException | NumberFormatException e) {
            Alerts.alertMessage(4);
            e.printStackTrace();
        }

    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }
}
