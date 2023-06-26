package Helper;

import DBAccess.RepairSQL;
import Model.Repair;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.text.html.Option;
import java.util.Optional;
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
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("This Username has already been used. Please try a different Username");
            alert.setHeaderText("Username already selected");
            alert.showAndWait();
        }
        if (code == 3) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Select Customer");
            alert.setContentText("Please select a customer to edit.");
            alert.showAndWait();
        }
        if(code == 4) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please ensure all fields are filled out");
            alert.showAndWait();
        }
        if(code == 5) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please ensure all fields are filled out. Phone should be in the format of (123)456-7890 and Zip Code should be 5 digits");
            alert.showAndWait();
        }
        if(code == 6) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Part not available");
            alert.setContentText("Part is not available and will need to be ordered");
            alert.showAndWait();
        }
        if(code == 7) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Select Part");
            alert.setContentText("Please select a part to edit");
            alert.showAndWait();
        }
        if(code == 8) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please ensure all fields are filled out correctly");
            alert.showAndWait();
        }
        if(code == 9) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Repair not found");
            alert.setContentText("No repair matching the provided search terms found");
            alert.showAndWait();
        }
    }
//FIXME: Not showing warning message when no item is selected
    public static void deleteRepair(Repair repair) {
        try {
            if (repair != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Are you Sure you want to proceed, this will permanently delete the selected Customer and Repair");
                alert.setHeaderText("Delete customer");
                Optional<ButtonType> confirm = alert.showAndWait();
                if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                    RepairSQL.deleteRepair(repair.getRepairId());
                } else {
                    throw new NullPointerException();
                }
            }
            }catch(NullPointerException e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Please select a Repair to delete");
                error.setHeaderText("Delete Repair");
                error.showAndWait();
            }
    }

}
