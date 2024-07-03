package lk.ijse.manathungatours.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.manathungatours.Util.Regex;
import lk.ijse.manathungatours.dao.custom.impl.customerDaoImpl;
import lk.ijse.manathungatours.model.Driver;
import lk.ijse.manathungatours.model.Engineer;
import lk.ijse.manathungatours.model.Passenger;
import lk.ijse.manathungatours.model.PassengerDTO;
import lk.ijse.manathungatours.model.tm.PassengerTm;
import lk.ijse.manathungatours.repository.DriverRepo;
import lk.ijse.manathungatours.repository.EngineerRepo;
import lk.ijse.manathungatours.repository.PassengerRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerManagementFormController {

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
    private TableView<PassengerTm> tblPassengers;

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
        loadAllPassengers();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllPassengers() {
        try {
            customerDaoImpl customerDao = new customerDaoImpl();
            ArrayList<PassengerDTO> allPassengers = customerDao.getAllPassengers();
            for (PassengerDTO allPassenger : allPassengers) {
                tblPassengers.getItems().add(new PassengerTm(allPassenger.getId(),allPassenger.getName(),allPassenger.getAddress(),allPassenger.getTel()));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backOnAction(ActionEvent event) {

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
            customerDaoImpl customerDao = new customerDaoImpl();
            boolean isDeleted = customerDao.delete(id);
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

        try {
            customerDaoImpl customerDao = new customerDaoImpl();
           boolean isSaved =  customerDao.save(new PassengerDTO(id,name,address,tel));
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

      PassengerDTO passenger = new PassengerDTO(id, name, address, tel);

        try {
           customerDaoImpl customerDao = new customerDaoImpl();
           boolean isUpdated = customerDao.update(passenger);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Update Done!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        customerDaoImpl customerDao = new customerDaoImpl();
        ArrayList<PassengerDTO> passengerDTOS = customerDao.searchById(id);

        if (passengerDTOS == null) {
            new Alert(Alert.AlertType.INFORMATION, "OOPS!! Not Found!").show();
        }
           else  {
            for (PassengerDTO dto : passengerDTOS) {
                txtId.setText(dto.getId());
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtTel.setText(dto.getTel());
            }

        }

    }

    public void idOnRelaesed(KeyEvent keyEvent) {Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.PID,txtId);}
    public void nameOnRelaesed(KeyEvent keyEvent) {Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.NAME,txtName);}
    public void addessOnRelaesed(KeyEvent keyEvent) {Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.ADDRESS,txtAddress);}
    public void telOnRelaesed(KeyEvent keyEvent) {Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.TEL,txtTel);}
    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.PID,txtId))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.NAME,txtName))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.ADDRESS,txtAddress))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.TEL,txtTel))return false;
        return true;
    }
}

