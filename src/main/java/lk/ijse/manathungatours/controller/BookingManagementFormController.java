package lk.ijse.manathungatours.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.manathungatours.dao.CrudDAO;
import lk.ijse.manathungatours.dao.custom.impl.*;
import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.dto.*;
import lk.ijse.manathungatours.dto.tm.BookingTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            CrudDAO<BookingDTO,String> bookingDTOStringCrudDAO = new bookingDaoImpl();
            String currentId = bookingDTOStringCrudDAO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            lblBookId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    private void getDrivers() {
        try {
            CrudDAO<DriverDTO,String> driverDao = new driverDaoImpl();
            ArrayList<String> driverList = driverDao.getIds();

            for (String code : driverList) {
                cmbDriver.getItems().add(code);
            }
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
            CrudDAO<PassengerDTO,String> passengerDTOStringCrudDAO = new customerDaoImpl();
            ArrayList<String> idList = passengerDTOStringCrudDAO.getIds();

            for (String id : idList) {
                obList.add(id);
            }

            cmbPassenger.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
//    void placeBookingOnAction(ActionEvent event) throws JRException, SQLException {
//       String bookingId = lblBookId.getText();
//       String passengerId = cmbPassenger.getValue();
//       Date date = Date.valueOf(LocalDate.now());
//       String desc = txtDescription.getText();
//
//        var booking = new BookingDTO(bookingId, passengerId, date,desc);
//
//        List<BookingDetailDTO> odList = new ArrayList<>();
//
//        for (int i = 0; i < tblBookings.getItems().size(); i++) {
//            BookingTm tm = obList.get(i);
//
//
//            BookingDetailDTO od = new BookingDetailDTO(
//                    bookingId,
//                    tm.getRegNumber(),
//                    tm.getDescription(),
//                    tm.getCost(),
//                    tm.getDate()
//            );
//
//            odList.add(od);
//        }
//
//        PlaceBookingDTO po = new PlaceBookingDTO(booking, odList);
//        try {
//           placeBookingDaoImpl placeBookingDao =  new placeBookingDaoImpl();
//            boolean isPlaced = placeBookingDao.placeOrder(po);
//            if(isPlaced) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Booking Placed!").show();
//            } else {
//                new Alert(Alert.AlertType.WARNING, "Booking Placed Unsuccessfully!").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//        printBill();
//    }

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





