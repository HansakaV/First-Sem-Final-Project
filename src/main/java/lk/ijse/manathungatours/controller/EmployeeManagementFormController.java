package lk.ijse.manathungatours.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EmployeeManagementFormController {

    @FXML
    private Button btnConductors;

    @FXML
    private Button btnDrivers;

    @FXML
    private Button btnEngineers;

    @FXML
    private Button btnExit;

    @FXML
    private AnchorPane root;

    @FXML
    void EngineerManagementOnAction(ActionEvent event) throws IOException {
        AnchorPane EngineerManageForm = FXMLLoader.load(this.getClass().getResource("/view/enginnersmanagement_form.fxml"));

        root.getChildren().clear();
        root.getChildren().add(EngineerManageForm);

    }

    @FXML
    void conductorManagementOnAction(ActionEvent event) throws IOException {
        AnchorPane ConductorManageForm = FXMLLoader.load(this.getClass().getResource("/view/conductormanagement_form.fxml"));

        root.getChildren().clear();
        root.getChildren().add(ConductorManageForm);

    }

    @FXML
    void driverManageOnAction(ActionEvent event) throws IOException {
        AnchorPane DriverManageForm = FXMLLoader.load(this.getClass().getResource("/view/drivermanagement_form.fxml"));

        root.getChildren().clear();
        root.getChildren().add(DriverManageForm);

    }

    @FXML
    void exitOnAction(ActionEvent event) {

    }


}
