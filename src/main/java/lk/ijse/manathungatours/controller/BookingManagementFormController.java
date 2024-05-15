package lk.ijse.manathungatours.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.*;
import lk.ijse.manathungatours.model.tm.BookingTm;
import lk.ijse.manathungatours.repository.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingManagementFormController {

    public Button btnConfirm;
    public TextField txtCost;
    public TableColumn colCost;
    public DatePicker datepick;
    @FXML
    private Button btnBook;

    @FXML
    private ComboBox<String> cmbBus;

    @FXML
    private ComboBox<String> cmbDriver;

    @FXML
    private ComboBox<String> cmbPassenger;

    @FXML
    private TableColumn<?, ?> colBId;

    @FXML
    private TableColumn<?, ?> colDId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableColumn<?, ?> colPId;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblDate;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<BookingTm> tblBookings;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtdate;

    private ObservableList<BookingTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();
        getCurrentOrderId();
        getPassengerIds();
        getBuses();
        getDrivers();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colPId.setCellValueFactory(new PropertyValueFactory<>("passengerId"));
        colBId.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        colDId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

    }



    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }



    private void getCurrentOrderId() {
        try {
            String currentId = BookingRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            lblBookId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getBuses() {
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

    private String generateNextOrderId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("O");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "O" + ++idNum;
        }
        return "O1";
    }

    private void getPassengerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = PassengerRepo.getIds();

            for (String id : idList) {
                obList.add(id);
            }

            cmbPassenger.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void placeBookingOnAction(ActionEvent event) throws JRException, SQLException {
       String bookingId = lblBookId.getText();
       String passengerId = cmbPassenger.getValue();
       Date date = Date.valueOf(LocalDate.now());
       String desc = txtDescription.getText();

        var booking = new Booking(bookingId, passengerId, date,desc);

        List<BookingDetail> odList = new ArrayList<>();

        for (int i = 0; i < tblBookings.getItems().size(); i++) {
            BookingTm tm = obList.get(i);


            BookingDetail od = new BookingDetail(
                    bookingId,
                    tm.getRegNumber(),
                    tm.getDescription(),
                    tm.getCost(),
                    tm.getDate()
            );

            odList.add(od);
        }

        PlaceBooking po = new PlaceBooking(booking, odList);
        try {
            boolean isPlaced = PlaceBookingRepo.placeOrder(po);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Booking Placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Booking Placed Unsuccessfully!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        printBill();
    }

    public void printBill() throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/Manathunga_Tours.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("bookingID",lblBookId.getText());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);


    }


    public void confirmOnAction(ActionEvent actionEvent) {
        String regNumber = cmbBus.getValue();
        String driver = cmbDriver.getValue();
        String pas = cmbPassenger.getValue();
        String des = txtDescription.getText();
        String cost = txtCost.getText();
        Date date = Date.valueOf(datepick.getValue());

        BookingTm tm = new BookingTm(pas,regNumber,driver,des,cost,date);
        obList.add(tm);

        tblBookings.setItems(obList);

    }
}





