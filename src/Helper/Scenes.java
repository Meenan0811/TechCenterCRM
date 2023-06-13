package Helper;

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


}
