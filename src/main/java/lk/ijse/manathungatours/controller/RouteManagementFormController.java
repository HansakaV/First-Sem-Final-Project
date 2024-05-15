package lk.ijse.manathungatours.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.manathungatours.model.Route;
import lk.ijse.manathungatours.model.tm.RouteTm;
import lk.ijse.manathungatours.repository.BusRepo;
import lk.ijse.manathungatours.repository.ConductorRepo;
import lk.ijse.manathungatours.repository.DriverRepo;
import lk.ijse.manathungatours.repository.RouteRepo;

import java.sql.SQLException;
import java.util.List;

public class RouteManagementFormController {

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
    private ComboBox<String> cmbBus;

    @FXML
    private ComboBox<String> cmbConductor;

    @FXML
    private ComboBox<String> cmbDriver;

    @FXML
    private TableColumn<?, ?> colBus;

    @FXML
    private TableColumn<?, ?> colConductor;

    @FXML
    private TableColumn<?, ?> colDriver;

    @FXML
    private TableColumn<?, ?> colRoute;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<RouteTm> tblRoutes;

    @FXML
    private TextField txtRoute;

    public void initialize() {
        if (colRoute == null || colBus == null || colConductor == null || colDriver == null) {
            System.err.println("One or more TableColumn objects are not properly initialized! bus");
            return;
        }

        setCellValueFactory();
        loadAllRoutes();
        getbuses();
        getConductors();
        getDrivers();
    }

    private void setCellValueFactory() {
        colRoute.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        colBus.setCellValueFactory(new PropertyValueFactory<>("seats"));
        colConductor.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDriver.setCellValueFactory(new PropertyValueFactory<>("service"));
    }

    private void loadAllRoutes() {
        ObservableList<RouteTm> obList = FXCollections.observableArrayList();

        try {
            List<Route> routeList = RouteRepo.getAll();
            for (Route r1 : routeList) {
                RouteTm tm = new RouteTm(
                       r1.getRoute(),
                        r1.getBusReg(),
                        r1.getDriverId(),
                        r1.getConductorId()

                );

                obList.add(tm);
            }

            tblRoutes.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void getDrivers() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> driverList = DriverRepo.getIds();

            for (String code : driverList) {
                obList.add(code);
            }
            cmbDriver.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getbuses() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> busList = BusRepo.getAvailbleBuses();

            for (String code : busList) {
                obList.add(code);
            }
            cmbBus.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getConductors() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> conductorList = ConductorRepo.getIds();

            for (String code : conductorList) {
                obList.add(code);
            }
            cmbConductor.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void backOnAction(ActionEvent event) {

    }

    @FXML
    void clearOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
