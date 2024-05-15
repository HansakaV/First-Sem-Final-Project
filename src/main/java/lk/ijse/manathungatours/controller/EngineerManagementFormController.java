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
import lk.ijse.manathungatours.model.Driver;
import lk.ijse.manathungatours.model.Engineer;
import lk.ijse.manathungatours.model.tm.EngineerTm;
import lk.ijse.manathungatours.repository.DriverRepo;
import lk.ijse.manathungatours.repository.EngineerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EngineerManagementFormController {

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
    private TableView<EngineerTm> tblEnginners;

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
       loadAllEnginners();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllEnginners() {
        ObservableList<EngineerTm> obList = FXCollections.observableArrayList();

        try {
            List<Engineer> engineerList = EngineerRepo.getAll();
            for (Engineer engineer : engineerList) {
                 EngineerTm tm = new EngineerTm(
                        engineer.getId(),
                        engineer.getName(),
                        engineer.getAddress(),
                        engineer.getTel()


                );

                obList.add(tm);
            }

            tblEnginners.setItems(obList);
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
            boolean isDeleted = EngineerRepo.delete(id);
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

      Engineer engineer = new Engineer(id, name,address, tel);

        try {
            boolean isSaved = EngineerRepo.save(engineer);
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

        Engineer engineer = new Engineer(id, name, address, tel);

        try {
            boolean isUpdated = EngineerRepo.update(engineer);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Update Done!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();

        Engineer engineer = EngineerRepo.searchById(id);
        if (engineer != null) {
            txtId.setText(engineer.getId());
            txtName.setText(engineer.getName());
            txtAddress.setText(engineer.getAddress());
            txtTel.setText(engineer.getTel());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "OOPS!! Not Found!").show();
        }

    }

    public void idOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.EID,txtId);
    }

    public void nameOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.NAME,txtName);
    }

    public void addessOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.ADDRESS,txtAddress);
    }

    public void telOnRelaesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.TEL,txtTel);
    }
    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.EID,txtId))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.NAME,txtName))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.ADDRESS,txtAddress))return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.TEL,txtTel))return false;
        return true;
    }
}

