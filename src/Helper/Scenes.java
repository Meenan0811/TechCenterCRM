package Helper;

import Controller.AddCustController;
import Controller.AddEmployeeController;
import Controller.AddRepairController;
import Controller.MainWinController;
import Model.Customers;
import Model.Repair;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.swing.*;
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
        FXMLLoader addCust = new FXMLLoader(AddEmployeeController.class.getResource("../View/addemployee.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(addCust.load());
        stage.setTitle("Customer Information");
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
        FXMLLoader addCust = new FXMLLoader(AddRepairController.class.getResource("../View/addrepair.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(addCust.load());
        stage.setTitle("Repair");
        stage.setScene(scene);
        stage.show();
    }
}
