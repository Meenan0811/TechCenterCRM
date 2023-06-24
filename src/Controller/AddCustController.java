package Controller;

import DBAccess.CustomerSQL;
import Helper.Alerts;
import Helper.Scenes;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCustController implements Initializable {
    @FXML
    private TextField custNameText;
    @FXML
    private TextField custPhoneText;
    @FXML
    private TextField custStreetText;
    @FXML
    private TextField custCityText;
    @FXML
    private ComboBox custStateCombo;
    @FXML
    private TextField custZipText;
    @FXML
    private Button custSave;
    @FXML
    private Button custCancel;
    @FXML
    private Label addCustLabel;
    private int custID = -1;
    private String custName;
    private String custPhone;
    private String custStreet;
    private String custCity;
    private int custZip;
    private String custState;
    private String currUser;
    private ObservableList<String> allStates = FXCollections.observableArrayList("AL", "Ak", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WAS", "WV", "WI", "WY");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custStateCombo.setItems(allStates);
        custStateCombo.setValue("AL");

    }

    /**
     * Retrieves entered information, validates all information is entered and matches correct formats required
     * @param event
     * @throws IOException
     */
    public void saveCust(ActionEvent event) throws IOException {
        custName = custNameText.getText();
        custPhone = custPhoneText.getText();
        custStreet = custStreetText.getText();
        custCity = custCityText.getText();
        custState = custStateCombo.getValue().toString();
        currUser = LoginController.currUser;

        if (!custZipText.getText().isBlank()) {
            custZip = Integer.valueOf(custZipText.getText());
        }

        if (custName.isEmpty() || custPhone.isEmpty() || custStreet.isEmpty() || custCity.isEmpty() || !validateZip(String.valueOf(custZip)) || !validateNumber(custPhone)) {
            Alerts.alertMessage(5);
        } else {
            CustomerSQL.addCust(custName, custPhone, custStreet, custCity, custState, custZip, currUser, currUser);
            Scenes.toMain(event);
        }
    }

    /**
     * Method to validate entered phone number is in correct format
     * @param phoneNum
     * @return
     */
    private boolean validateNumber(String phoneNum) {
        if (phoneNum.matches("\\(\\d{3}\\)\\d{3}\\-\\d{4}")) return true;
        else return false;
    }

    /**
     * Method to Validate Zip code is entered in valid format
     * @param custZip
     * @return
     */
    private boolean validateZip(String custZip) {
        if (custZip.matches("\\d{5}")) return true;
        else return false;
    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }
}
