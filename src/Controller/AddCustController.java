package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (MainWinController.passCust != null) {
            custNameText.setText(MainWinController.passCust.getCustName());
            custPhoneText.setText(MainWinController.passCust.getCustPhone());
        }
    }
}
