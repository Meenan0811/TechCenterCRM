package Controller;

import DBAccess.PartsSQL;
import Helper.Alerts;
import Helper.Scenes;
import Model.Parts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Allows user to add and edit parts
 *
 * @author Matthew Meenan
 */
public class PartsController implements Initializable {

    @FXML
    private TableView partTable;
    @FXML
    private TableColumn partIdCol;
    @FXML
    private TableColumn partNameCol;
    @FXML
    private TableColumn partQtyCol;
    @FXML
    private Button addPartButton;
    @FXML
    private TextField partNameText;
    @FXML
    private TextField partQtyText;
    @FXML
    private TextField updatePartQtyText;
    @FXML
    private Label partNameLabel;
    private int partId;
    private String partName;
    private int partQty;
    private Parts part;
    private ObservableList<Parts> allparts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allparts = PartsSQL.getParts();
        setPartsTable(allparts);
    }

    public void setPartsTable(ObservableList parts) {
        partTable.setItems(parts);

        partIdCol.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("partName"));
        partQtyCol.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("qty"));
    }

    public void addPart(ActionEvent event) {
        if (part == null) {
            try {
                partName = partNameText.getText();
                partQty = Integer.parseInt(partQtyText.getText());

                PartsSQL.addPart(partName, partQty);
                Scenes.toParts(event);
            } catch (NumberFormatException n) {
                Alerts.alertMessage(8);
                n.printStackTrace();
            } catch (NullPointerException e) {
                Alerts.alertMessage(8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                partName = partNameText.getText();
                partQty = Integer.parseInt(partQtyText.getText());
                partId = part.getPartID();

                PartsSQL.updatePart(partId, partName, partQty);
                Scenes.toParts(event);
            } catch (NumberFormatException n) {
                Alerts.alertMessage(8);
                n.printStackTrace();
            } catch (NullPointerException e) {
                Alerts.alertMessage(8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void editPart(ActionEvent event) {
        if (partTable.getSelectionModel().getSelectedItem() != null) {
            part = Parts.class.cast(partTable.getSelectionModel().getSelectedItem());
            addPartButton.setText("Edit Part");
            partNameText.setText(part.getPartName());
            partQtyText.setText(String.valueOf(part.getQty()));
        }else {
            Alerts.alertMessage(7);
        }
    }

    public void updateQty(ActionEvent event) {

        if (partTable.getSelectionModel().getSelectedItem() != null) {
            try {
                part = Parts.class.cast(partTable.getSelectionModel().getSelectedItem());
                partId = part.getPartID();
                partQty = Integer.parseInt(updatePartQtyText.getText());
                partName = part.getPartName();

                PartsSQL.updatePart(partId, partName, partQty);
                Scenes.toParts(event);
            }catch (NullPointerException n) {
                Alerts.alertMessage(8);
            }catch (NumberFormatException e) {
                Alerts.alertMessage(8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alerts.alertMessage(7);
        }

    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);

    }
}
