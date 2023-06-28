package Helper;

import Controller.AddCustController;
import Controller.AddEmployeeController;
import Controller.AddRepairController;
import Controller.MainWinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Scenes {

    /**
     * Calls the main window
     *
     * @param event
     * @throws IOException
     */
    public static void toMain(ActionEvent event) throws IOException {
        FXMLLoader mainWin = new FXMLLoader(MainWinController.class.getResource("../View/mainWin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainWin.load(), 1067, 656);
        stage.setTitle("Appointment Schedule");
        stage.setScene(scene);
        stage.show();
    }

    public static void toAddCust(ActionEvent event) throws IOException {
        FXMLLoader addCust = new FXMLLoader(AddCustController.class.getResource("../View/addcust.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(addCust.load());
        stage.setTitle("Customer Information");
        stage.setScene(scene);
        stage.show();
    }


    public static void toAddEmpl(ActionEvent event) throws IOException {
        FXMLLoader addEmpl = new FXMLLoader(AddEmployeeController.class.getResource("../View/addemployee.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(addEmpl.load());
        stage.setTitle("Employee Information");
        stage.setScene(scene);
        stage.show();
    }

    public static void toEditCust(ActionEvent event) throws IOException {
        FXMLLoader addCust = new FXMLLoader(AddEmployeeController.class.getResource("../View/addemployee.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(addCust.load());
        stage.setTitle("Customer Information");
        stage.setScene(scene);
        stage.show();
    }

    public static void toAddRepair(ActionEvent event) throws IOException {
        FXMLLoader repair = new FXMLLoader(AddRepairController.class.getResource("../View/addrepair.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(repair.load());
        stage.setTitle("Repair");
        stage.setScene(scene);
        stage.show();
    }

    public static void toParts(ActionEvent event) throws IOException {
        FXMLLoader parts = new FXMLLoader(AddRepairController.class.getResource("../View/parts.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parts.load());
        stage.setTitle("Parts");
        stage.setScene(scene);
        stage.show();
    }

    public static void toReports(ActionEvent event) throws IOException {
        FXMLLoader reports = new FXMLLoader(AddRepairController.class.getResource("../View/reports.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(reports.load());
        stage.setTitle("reports");
        stage.setScene(scene);
        stage.show();
    }
}
