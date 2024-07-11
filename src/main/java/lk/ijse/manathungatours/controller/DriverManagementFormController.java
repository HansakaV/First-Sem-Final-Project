package lk.ijse.manathungatours.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.manathungatours.Util.Regex;
import lk.ijse.manathungatours.dao.custom.impl.driverDaoImpl;
import lk.ijse.manathungatours.dao.custom.driverDAO;
import lk.ijse.manathungatours.dto.DriverDTO;
import lk.ijse.manathungatours.dto.tm.DriverTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private driverDAO driverDao = new driverDaoImpl();


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
        try {

            ArrayList<DriverDTO> driverList = driverDao.getAll();
            for (DriverDTO dto : driverList) {
                tblDrivers.getItems().add(new DriverTm(dto.getId(), dto.getName(), dto.getAddress(), dto.getTel()));
            }
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

            boolean isDeleted = driverDao.delete(id);
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

        DriverDTO driver = new DriverDTO(id, name,address, tel);
        try {

            boolean isSaved = driverDao.save(driver);
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

        DriverDTO driver = new DriverDTO(id, name, address, tel);
        try {

            boolean isUpdated = driverDao.update(driver);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Update Done!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();


       ArrayList<DriverDTO>  driver = driverDao.search(id);
        if (driver != null) {
            for (DriverDTO dto : driver) {
                txtId.setText(dto.getId());
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtTel.setText(dto.getTel());
            }
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

