package Controller;

import Helper.Scenes;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private TextField custStateText;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (MainWinController.passRepair != null) {
            custNameText.setText(MainWinController.passRepair.getCustName());
            custPhoneText.setText(MainWinController.passRepair.getCustPhone());
        }
    }

    public void saveCust(ActionEvent event) {
        custName = custNameText.getText();
        custPhone = custPhoneText.getText();
        custStreet = custStreetText.getText();
        custCity = custCityText.getText();
        custState = custStateText.getText();
        custZip = Integer.valueOf(custZipText.getText());


    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }
}
