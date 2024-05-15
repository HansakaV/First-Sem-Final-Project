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
import lk.ijse.manathungatours.model.Bus;
import lk.ijse.manathungatours.model.Conductor;
import lk.ijse.manathungatours.model.tm.ConductorTm;
import lk.ijse.manathungatours.repository.BusRepo;
import lk.ijse.manathungatours.repository.ConductorRepo;

import java.io.IOException;
import java.sql.SQLException;
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
        ObservableList<ConductorTm> obList = FXCollections.observableArrayList();

        try {
            List<Conductor> conductorList = ConductorRepo.getAll();
            for (Conductor conductor : conductorList) {
                ConductorTm tm = new ConductorTm(
                        conductor.getId(),
                        conductor.getName(),
                        conductor.getAddress(),
                        conductor.getTel()

                );

                obList.add(tm);
            }

            tblconductors.setItems(obList);
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
            boolean isDeleted = ConductorRepo.delete(id);
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

        Conductor conductor = new Conductor(id, name,address, tel);

        try {
            boolean isSaved = ConductorRepo.save(conductor);
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

        Conductor conductor = new Conductor(id, name, address, tel);

        try {
            boolean isUpdated = ConductorRepo.update(conductor);
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

       Conductor conductor = ConductorRepo.searchById(id);
        if (conductor != null) {
            txtid.setText(conductor.getId());
            txtname.setText(conductor.getName());
            txtaddress.setText(conductor.getAddress());
            txttel.setText(conductor.getTel());
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


