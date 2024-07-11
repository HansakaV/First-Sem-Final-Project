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
import lk.ijse.manathungatours.dao.custom.busDAO;
import lk.ijse.manathungatours.dao.custom.impl.busDaoImpl;
import lk.ijse.manathungatours.dto.BusDTO;
import lk.ijse.manathungatours.dto.tm.BusTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusManagementFormController {

    public AnchorPane busPane;
    public ComboBox cmbSeats;
    public ComboBox cmbService;
    public ComboBox cmbStatus;
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
    private TableColumn<BusTm, String> colRegNumber;

    @FXML
    private TableColumn<BusTm, String> colSeats;

    @FXML
    private TableColumn<BusTm, String> colService;

    @FXML
    private TableColumn<BusTm, String> colStatus;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BusTm> tblBus;

    @FXML
    private TextField txtRegNumber;

    @FXML
    private TextField txtSeats;

    @FXML
    private TextField txtService;

    @FXML
    private TextField txtStatus;

    private String[] statusList = new String[]{"Available", "Not Available"};

    private String[] seatsList = new String[]{"42", "48" ,"54" ,"59"};

    private String[] serviceStatus = new String[]{"Done", "Not Done"};
    private busDAO busDao = new busDaoImpl();


    public void initialize() {
        if (colRegNumber == null || colSeats == null || colService == null || colStatus == null) {
            System.err.println("One or more TableColumn objects are not properly initialized! bus");
            return;
        }

        setCellValueFactory();
        loadAllBuses();
        showStatus();
        chooseSeats();
        chooseService();
    }

    private void setCellValueFactory() {
        colRegNumber.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        colSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colService.setCellValueFactory(new PropertyValueFactory<>("service"));
    }

    private void loadAllBuses() {
        try {
                ArrayList<BusDTO> dtos = busDao.getAll();
            for (BusDTO dto : dtos) {
                tblBus.getItems().add(new BusTm(dto.getRegNumber(),dto.getSeats(),dto.getStatus(),dto.getService()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void showStatus(){
        List<String> listS = new ArrayList();
        String[] var2 = this.statusList;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String data = var2[var4];
            listS.add(data);
        }

        ObservableList listStatus = FXCollections.observableArrayList(listS);
        this.cmbStatus.setItems(listStatus);

    }
    public void chooseSeats(){
        List<String> listS = new ArrayList();
        String[] var2 = this.seatsList;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String data = var2[var4];
            listS.add(data);
        }

        ObservableList listStatus = FXCollections.observableArrayList(listS);
        this.cmbSeats.setItems(listStatus);
    }

    public void chooseService(){
        List<String> listS = new ArrayList();
        String[] var2 = this.serviceStatus;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String data = var2[var4];
            listS.add(data);
        }

        ObservableList listStatus = FXCollections.observableArrayList(listS);
        this.cmbService.setItems(listStatus);
    }


    @FXML
    void backOnAction(ActionEvent event) {

    }

    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtRegNumber.setText("");
        cmbSeats.setValue("");
        cmbService.setValue("");
        cmbStatus.setValue("");

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtRegNumber.getText();

        try {
           busDaoImpl busDao =  new busDaoImpl();
            boolean isDeleted = busDao.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Removed From System!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        if (txtRegNumber == null || cmbSeats == null || cmbStatus == null || cmbService == null) {
            System.err.println("One or more TextField objects are not properly initialized!");
            return;
        }

        String regNumber = txtRegNumber.getText();
        String seats = cmbSeats.getValue().toString();
        String status = cmbStatus.getValue().toString();
        String service = cmbService.getValue().toString();

        BusDTO bus = new BusDTO(regNumber, seats, status, service);

        try {
                boolean isSaved = busDao.save(bus);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added to System!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchOnAction(ActionEvent event) throws SQLException {
        String regNumber = txtRegNumber.getText();

       ArrayList<BusDTO>  dtos =  busDao.search(regNumber);

        if (dtos != null) {
            for (BusDTO dto : dtos) {
                txtRegNumber.setText(dto.getRegNumber());
                txtSeats.setText(dto.getSeats());
                txtStatus.setText(dto.getStatus());
                txtService.setText(dto.getService());
            }
            } else{
                new Alert(Alert.AlertType.INFORMATION, "OOPS!! Not Found!").show();
            }
        }


    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtRegNumber.getText();
        String seats = cmbSeats.getValue().toString();
        String status = cmbStatus.getValue().toString();
        String service = cmbService.getValue().toString();

        BusDTO bus = new BusDTO(id, seats, status, service);

        try {
                boolean isUpdated = busDao.update(bus);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " Update Done!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void busKeyOnReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.BUS,txtRegNumber);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.BUS,txtRegNumber))return false;
        return true;
    }

}
