package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddRepairController {
    @FXML
    private ComboBox statusCombo;
    @FXML
    private ComboBox assgnEmplCombo;
    @FXML
    private ComboBox deviceTypeCombo;
    @FXML
    private TextField repairNotesText;
    @FXML
    private DatePicker tatDatepicker;
    @FXML
    private Button saveRepairbutton;
    @FXML
    private Button cancelRepairButton;
}
