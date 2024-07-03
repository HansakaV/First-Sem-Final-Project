package lk.ijse.manathungatours.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.manathungatours.dao.custom.impl.busDaoImpl;
import lk.ijse.manathungatours.dao.custom.impl.conductorDaoImpl;
import lk.ijse.manathungatours.dao.custom.impl.routeDaoImpl;
import lk.ijse.manathungatours.model.Conductor;
import lk.ijse.manathungatours.model.Route;
import lk.ijse.manathungatours.model.RouteDTO;
import lk.ijse.manathungatours.model.tm.RouteTm;
import lk.ijse.manathungatours.repository.BusRepo;
import lk.ijse.manathungatours.repository.ConductorRepo;
import lk.ijse.manathungatours.repository.DriverRepo;
import lk.ijse.manathungatours.repository.RouteRepo;

import java.sql.SQLException;
import java.util.ArrayList;
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
        colRoute.setCellValueFactory(new PropertyValueFactory<>("route"));
        colBus.setCellValueFactory(new PropertyValueFactory<>("busReg"));
        colConductor.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        colDriver.setCellValueFactory(new PropertyValueFactory<>("conductorId"));
    }

    private void loadAllRoutes() {
        ObservableList<RouteTm> obList = FXCollections.observableArrayList();

        try {
            routeDaoImpl routeDao =  new routeDaoImpl();
            ArrayList<RouteDTO> routeList = routeDao.getAll();
            for (RouteDTO dto : routeList) {
                tblRoutes.getItems().add(new RouteTm(dto.getRoute(), dto.getBusReg(), dto.getConductorId(), dto.getDriverId()));
            }
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
           busDaoImpl busDao = new busDaoImpl();
            ArrayList<String> busList = busDao.checkAvailability();

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
            conductorDaoImpl conductorDao =new conductorDaoImpl();
            List<String> conductorList = conductorDao.getIds();

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
        clearFields();
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String route = txtRoute.getText();
        try {
           routeDaoImpl routeDao =  new routeDaoImpl();
            boolean isDeleted = routeDao.delete(route);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Removed From System!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String route = txtRoute.getText();
        String bus = cmbBus.getValue();
        String driver = cmbDriver.getValue();
        String routeText = cmbConductor.getValue();

        RouteDTO route1 = new RouteDTO(route, bus,driver,routeText);
        try {
            routeDaoImpl routeDao =  new routeDaoImpl();
            boolean isSaved = routeDao.save(route1);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added to System!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void clearFields() {
        txtRoute.setText("");
        cmbConductor.setValue("");
       cmbDriver.setValue("");
        cmbBus.setValue("");
    }


    @FXML
    void searchOnAction(ActionEvent event) throws SQLException {
        String id = txtRoute.getText();

        routeDaoImpl routeDao =  new routeDaoImpl();
        ArrayList<RouteDTO> routeText = routeDao.search(id);
        if (routeText != null) {
            for (RouteDTO dto : routeText) {
                txtRoute.setText(dto.getRoute());
                cmbBus.setValue(dto.getBusReg());
                cmbDriver.setValue(dto.getDriverId());
                cmbConductor.setValue(dto.getConductorId());
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "OOPS!! Not Found!").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String routeText1 = txtRoute.getText();
        String busValue = cmbBus.getValue();
        String driverValue = cmbDriver.getValue();
        String conductorValue = cmbConductor.getValue();

        RouteDTO routeText = new RouteDTO(routeText1,busValue,driverValue,conductorValue);
        try {
            routeDaoImpl routeDao =  new routeDaoImpl();
            boolean isUpdated = routeDao.update(routeText);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Update Done!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

}
