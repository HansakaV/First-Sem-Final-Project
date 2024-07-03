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
import lk.ijse.manathungatours.dao.custom.impl.conductorDaoImpl;
import lk.ijse.manathungatours.model.Bus;
import lk.ijse.manathungatours.model.Conductor;
import lk.ijse.manathungatours.model.ConductorDTO;
import lk.ijse.manathungatours.model.tm.ConductorTm;
import lk.ijse.manathungatours.repository.BusRepo;
import lk.ijse.manathungatours.repository.ConductorRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConductorManagementFormController {

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
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ConductorTm> tblconductors;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txttel;


    public void initialize() {
        if (colId == null || colName == null || colAddress == null || colTel == null) {
            System.err.println("One or more TableColumn objects are not properly initialized!");
            return;
        }

        setCellValueFactory();
        loadAllConductors();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllConductors() {
        try {
            conductorDaoImpl conductorDao = new conductorDaoImpl();
            ArrayList<ConductorDTO> conductorList = conductorDao.getAll();
            for (ConductorDTO dto : conductorList) {
                tblconductors.getItems().add(new ConductorTm(dto.getId(), dto.getName(), dto.getAddress(), dto.getTel()));
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
            txtid.setText("");
            txtname.setText("");
            txtaddress.setText("");
            txttel.setText("");
        }



    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtid.getText();

        try {
            conductorDaoImpl conductorDao = new conductorDaoImpl();
            boolean isDeleted = conductorDao.delete(id);
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
        String id = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String tel = txttel.getText();

        ConductorDTO conductor = new ConductorDTO(id, name,address, tel);
        try {
            conductorDaoImpl conductorDao = new conductorDaoImpl();
            boolean isSaved = conductorDao.save(conductor);
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
        String id = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String tel = txttel.getText();

        ConductorDTO conductor = new ConductorDTO(id, name, address, tel);
        try {
            conductorDaoImpl conductorDao = new conductorDaoImpl();
            boolean isUpdated = conductorDao.update(conductor);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Update Done!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void searchOnAction(ActionEvent event) throws SQLException {
        String id = txtid.getText();

        conductorDaoImpl conductorDao = new conductorDaoImpl();
       ArrayList<ConductorDTO> conductor = conductorDao.search(id);
        if (conductor != null) {
            for (ConductorDTO dto : conductor) {
                txtid.setText(dto.getId());
                txtname.setText(dto.getName());
                txtaddress.setText(dto.getAddress());
                txttel.setText(dto.getTel());
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "OOPS!! Not Found!").show();
        }
    }

    public void nameOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.NAME,txtname);
    }

    public void addressOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.ADDRESS,txtaddress);
    }

    public void idOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.CID,txtid);
    }

    public void telOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.TEL,txttel);
    }
    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.CID,txtid))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.NAME,txtname))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.ADDRESS,txtaddress))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.TEL,txttel))return false;
        return true;
    }
}


