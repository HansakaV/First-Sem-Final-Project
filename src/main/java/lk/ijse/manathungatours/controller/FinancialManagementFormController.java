package lk.ijse.manathungatours.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.manathungatours.dao.CrudDAO;
import lk.ijse.manathungatours.dao.custom.impl.busDaoImpl;
import lk.ijse.manathungatours.dao.custom.impl.financialDaoImpl;
import lk.ijse.manathungatours.dao.custom.financialDAO;
import lk.ijse.manathungatours.dto.BusDTO;
import lk.ijse.manathungatours.dto.FinancialDTO;
import lk.ijse.manathungatours.dto.tm.FinancialTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class FinancialManagementFormController {

    @FXML
    private ComboBox<String> cmbBus; // Replacing TextField with ComboBox

    @FXML
    private TableColumn<?, ?> colBus;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colIncome;

    @FXML
    private TableView<FinancialTm> tblFinance;
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
    private TextField txtCost;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtIncome;
    private financialDAO financialDTOStringCrudDAO = new financialDaoImpl();

    public void initialize() {
        if (colBus == null || colDescription == null || colIncome == null || colCost == null) {
            System.err.println("One or more TableColumn objects are not properly initialized!");
            return;
        }

        setCellValueFactory();
        loadAllFinancials();
        getBuses();
    }

    private void setCellValueFactory() {
        colBus.setCellValueFactory(new PropertyValueFactory<>("busId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colIncome.setCellValueFactory(new PropertyValueFactory<>("income"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    private void getBuses() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
           CrudDAO<BusDTO,String> busDao =  new busDaoImpl();
            ArrayList<String> busList = busDao.getIds();

            for (String code : busList) {
                obList.add(code);
            }
            cmbBus.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllFinancials() {
        ObservableList<FinancialTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<FinancialDTO> financialList =financialDTOStringCrudDAO.getAll();
            for (FinancialDTO financial : financialList) {
                tblFinance.getItems().add(new FinancialTm(financial.getBusId(),financial.getDescription(),financial.getIncome(),financial.getCost()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
       // cmbBus.setValue(""); // Clearing ComboBox value
        txtDescription.setText("");
        txtIncome.setText("");
        txtCost.setText("");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = cmbBus.getValue(); // Retrieving selected value from ComboBox

        try {
            boolean isDeleted = financialDTOStringCrudDAO.delete(String.valueOf(id));
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Removed From System!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = String.valueOf(cmbBus.getValue()); // Retrieving selected value from ComboBox
        String desc = txtDescription.getText();
        String income = txtIncome.getText();
        String cost = txtCost.getText();

        FinancialDTO financial = new FinancialDTO(id, desc, income, cost);

        try {
            boolean isSaved = financialDTOStringCrudDAO.save(financial);
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
        String bid = String.valueOf(cmbBus.getValue()); // Retrieving selected value from ComboBox
        String description = txtDescription.getText();
        String income = txtIncome.getText();
        String cost = txtCost.getText();

        FinancialDTO financial = new FinancialDTO(bid, description, income, cost);

        try {
            boolean isUpdated = financialDTOStringCrudDAO.update(financial);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Update Done!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = String.valueOf(cmbBus.getValue()); // Retrieving selected value from ComboBox

        ArrayList<FinancialDTO> financial = financialDTOStringCrudDAO.search(id);
        if (financial != null) {
            for (FinancialDTO dto : financial) {
                cmbBus.setValue(dto.getBusId());
                txtDescription.setText(dto.getDescription());
                txtIncome.setText(dto.getIncome());
                txtCost.setText(dto.getCost());
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "OOPS!! Not Found!").show();
        }
    }
}
