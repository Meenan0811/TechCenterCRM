package Helper;

import javafx.scene.control.Alert;

import java.util.ResourceBundle;

public abstract class Alerts {

    public static void alertMessage(int code) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(code == 1) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("The UserName and Password provided do not match, Please try again.");
            alert.setHeaderText("Incorrect UserName and Password");
            alert.showAndWait();
        }
        if (code == 2) {

        }
    }

}
