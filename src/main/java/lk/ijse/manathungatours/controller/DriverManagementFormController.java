package lk.ijse.manathungatours.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.manathungatours.Util.Regex;
import lk.ijse.manathungatours.model.Conductor;
import lk.ijse.manathungatours.model.Driver;
import lk.ijse.manathungatours.model.tm.DriverTm;
import lk.ijse.manathungatours.repository.ConductorRepo;
import lk.ijse.manathungatours.repository.DriverRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DriverManagementFormController {

    public TableColumn colid;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn coltel;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<DriverTm> tblDrivers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    public void initialize() {
        if (colid == null || colname == null || coladdress == null || coltel == null) {
            System.err.println("One or more TableColumn objects are not properly initialized!");
            return;
        }

        setCellValueFactory();
        loadAllDrivers();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllDrivers() {
        ObservableList<DriverTm> obList = FXCollections.observableArrayList();

        try {
            List<Driver> driverList = DriverRepo.getAll();
            for (Driver driver : driverList) {
              DriverTm tm = new DriverTm(
                      driver.getId(),
                      driver.getName(),
                      driver.getAddress(),
                      driver.getTel()


                );

                obList.add(tm);
            }

            tblDrivers.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        AnchorPane employeeManageForm = FXMLLoader.load(this.getClass().getResource("/view/employeemanagement_form.fxml"));

        root.getChildren().clear();
        root.getChildren().add(employeeManageForm);


    }

    @FXML
    void clearOnAction(ActionEvent event) {clearFields();}
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }



    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = DriverRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Removed From System!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        isValid();
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        Driver driver = new Driver(id, name,address, tel);

        try {
            boolean isSaved = DriverRepo.save(driver);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added to System!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        Driver driver = new Driver(id, name, address, tel);

        try {
            boolean isUpdated = DriverRepo.update(driver);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Update Done!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();

        Driver driver = DriverRepo.searchById(id);
        if (driver != null) {
            txtId.setText(driver.getId());
            txtName.setText(driver.getName());
            txtAddress.setText(driver.getAddress());
            txtTel.setText(driver.getTel());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "OOPS!! Not Found!").show();
        }

    }

    public void driverOnReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.DID,txtId);
    }

    public void nameOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.NAME,txtName);
    }

    public void adderssOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.ADDRESS,txtAddress);
    }

    public void telOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.TEL,txtTel);
    }
    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.DID,txtId))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.NAME,txtName))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.ADDRESS,txtAddress))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.TEL,txtTel))return false;
        return true;
    }
}

