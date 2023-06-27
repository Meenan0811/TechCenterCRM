package Controller;

import DBAccess.CustomerSQL;
import DBAccess.EmployeeSQL;
import DBAccess.PartsSQL;
import DBAccess.RepairSQL;
import Model.Employee;
import Helper.Alerts;
import Helper.Scenes;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains methods to verify username and password are correct before allowing user to enter main screen. Determines users locale and language based off of system settings.
 *
 * @author Matt Meenan
 */
public class LoginController implements Initializable {

    @FXML
    private Label locLabel;
    @FXML
    private PasswordField passWordPassField;
    @FXML
    private TextField userText;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label locTagLabel;
    @FXML
    private Button submitButton;
    public static String currUser;
    public static int currUserId;


    /**
     * OverRides Initialize class initialize method, Gets users locale based on system settings and sets onScreen prompts to either French or English and displays users Locale
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();
        locTagLabel.setText(zoneId.toString());

        //Creates ResourceBundle Object and changes LoginController language based on System settings
        /*ResourceBundle rb = ResourceBundle.getBundle("language");
        userNameLabel.setText(rb.getString("userLabel"));
        passLabel.setText(rb.getString("passLabel"));
        locLabel.setText(rb.getString("localLabel"));
        userText.setPromptText(rb.getString("userText"));
        passWordPassField.setPromptText(rb.getString("passField"));*/
        //CustomerSQL.addCust("Test Guy","(609)123-1234", "29 Black Oak Dr", "Ocean View", "NJ", 0, "Test Guy", "Test Guy");
        //EmployeeSQL.addEmployee("Test Guy", "admin", "admin", "Ocean View", "Yes");
        LocalDateTime date = LocalDateTime.now();
        LocalDate minusYear = LocalDate.now().withYear(2020);
        LocalDate minusMonth = LocalDate.now().minusMonths(2);
        LocalDate minusWeeks = LocalDate.now().minusWeeks(2);
        //RepairSQL.addRepair("Tablet", 1, 0, "Test Notes, test Notes", "Employee", date,  "In repair", "Employee", "Employee");
        //PartsSQL.addPart("Labor", 1000);
        //RepairSQL.addRepair("Personal Computer", 2, 1, "Testy TEsty `123", "admin", minusMonth, "In Repair", "Test Guy", "admin", "Repair");
        //RepairSQL.addRepair("Personal Computer", 2, 1, "Number 2", "admin", minusYear, "Completed", "admin", "admin", "DT");
        //RepairSQL.addRepair("Phone", 2, 1, "Number 3", "Test Guy", minusWeeks, "In Repair", "Test Guy", "admin", "Repair");



    }

    /**
     * Verify's that information provided in Username and Password fields match a Username and Password in database. If match found call's toMain and provides user access to main screen. If no match found alerts user.
     * All login attempts are logged with Username, date, and time in a .txt file
     * @param actionEvent
     * @throws IOException
     */
    public void setSubmitButton(javafx.event.ActionEvent actionEvent) throws IOException {
        String userName = userText.getText();
        String pass = passWordPassField.getText();
        ObservableList<Model.Employee> userList = EmployeeSQL.allEmployees();
        boolean valid = false;
        this.currUser = userName;
        LocalDateTime curDate = LocalDateTime.now();
        String dateTimeFormat = "MM/dd/yyyy HH:mm";


        for(Employee e: userList) {
            String tempU = e.getUserName();
            String tempP = e.getPassWord();

            if(userName.contains(tempU) && pass.contains(tempP)) {
                valid = true;
                this.currUserId = e.getEmployeeID();


                Scenes.toMain(actionEvent);
                //Appt.immediateAppt();

                try {
                    PrintWriter pw = new PrintWriter(new FileOutputStream(
                            "login_activty.txt",
                            true ));
                    pw.append("Login Attempt: " + curDate.format(DateTimeFormatter.ofPattern(dateTimeFormat)) + "\nBy:" + userName + " UserId: " + currUserId + " Successful.\n");
                    pw.close();
                }
                catch(FileNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("File Not Found");
                }
                break;
            }
        }
        if(!valid) {
            Alerts.alertMessage(1);
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(
                        "login_activty.txt",
                        true ));
                pw.append("Login Attempt: " + curDate.format(DateTimeFormatter.ofPattern(dateTimeFormat)) + "\nBy:" + userName + " UserId: " + currUserId + " Failed.\n");
                pw.close();
            }
            catch(FileNotFoundException e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("File Not Found");
            }
        }

    }


}

