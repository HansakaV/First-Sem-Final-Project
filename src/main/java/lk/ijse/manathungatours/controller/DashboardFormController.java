package lk.ijse.manathungatours.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.manathungatours.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DashboardFormController {

    public Button btnSettings;
    public Button btnUser;
    public Label lblName;
    public Label lblIncome;
    public Label lblEnginners;
    public Label lblConductors;
    public Label lblDrivers;
    public Label lblBus;
    public Label lblAvailableBus;
    public Button btnDashBoard;
    @FXML
    private Button btnBookingManage;

    @FXML
    private Button btnBusManage;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLaborManage;

    @FXML
    private Button btnPassengerManage;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane mainNode;

    @FXML
    private AnchorPane middleNode;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane11;

    @FXML
    private Button btnFinancialManage;

    @FXML
    private Pane pane12;

    @FXML
    private Button btnRouteManage;


    @FXML
    private AnchorPane sideNode;

    private  int busCount;
    private int availableBusCount;
    private int driverCount;
    private int conductorCount;
    private int engineerCount;
    private int  income;
    private String user;

    public void initialize() {
        setDate();
        setTime();


        try {
            busCount = getBusCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setBusCount(busCount);

        try {
            availableBusCount = getAvaialbleBusCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setLblAvailableBusCount(availableBusCount);

        try {
            driverCount = getDriversCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setDriversCount(driverCount);

        try {
            conductorCount = getConductorsCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setConductorsCount(conductorCount);

        try {
            engineerCount = getEngineerCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setEngineersCount(engineerCount);

        try {
            income = getIncome();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setIncome(income);
    }
    private void setUserName(String user){lblName.setText(String.valueOf(user));}
    private void setIncome(int income) {
        lblIncome.setText(String.valueOf(income));
    }

    private void setBusCount(int busCount) {
        lblBus.setText(String.valueOf(busCount));
    }

    private void setLblAvailableBusCount(int availableBusCount) {
        lblAvailableBus.setText(String.valueOf(availableBusCount));
    }

    private void setDriversCount(int customerCount) {
        lblDrivers.setText(String.valueOf(customerCount));
    }

    private void setConductorsCount(int customerCount) {
        lblConductors.setText(String.valueOf(customerCount));
    }
    private void setEngineersCount(int customerCount) {
        lblEnginners.setText(String.valueOf(customerCount));
    }


    private int getBusCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS bus_count FROM buses";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("bus_count");
        }
        return 0;
    }

    private int getAvaialbleBusCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS Available_count FROM buses WHERE Status ='Available'";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Available_count");
        }
        return 0;
    }

    private int getDriversCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS Driver_count FROM drivers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Driver_count");
        }
        return 0;
    }

    private int getConductorsCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS Conductor_count FROM conductors";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Conductor_count");
        }
        return 0;
    }
    private int getEngineerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS Enginner_count FROM enginners";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Enginner_count");
        }
        return 0;
    }
    private String getUserName(String id) throws SQLException {
        String sql = "SELECT user_name FROM users WHERE user_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return  resultSet.getString("user_name");
        }
        return null;
    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void setTime(){
        LocalTime now = LocalTime.now();
        lblTime.setText(String.valueOf(now));
    }
    private int getIncome() throws SQLException {
        String sql = "SELECT SUM(income) FROM  financial;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("SUM(income)");
        }
        return 0;


    }

    @FXML
    void bookingManageOnAction(ActionEvent event) throws IOException {
        AnchorPane bookingManageForm = FXMLLoader.load(this.getClass().getResource("/view/bookingmanagement_form.fxml"));

        middleNode.getChildren().clear();
        middleNode.getChildren().add(bookingManageForm);

    }

    @FXML
    void busManageOnAction(ActionEvent event) throws IOException {
        AnchorPane busManageForm = FXMLLoader.load(this.getClass().getResource("/view/busmanagement_form.fxml"));

        middleNode.getChildren().clear();
        middleNode.getChildren().add(busManageForm);

    }

    @FXML
    void exitOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/logout_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.mainNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

    }

    @FXML
    void laborManageOnAction(ActionEvent event) throws IOException {
        AnchorPane employeeManageForm = FXMLLoader.load(this.getClass().getResource("/view/employeemanagement_form.fxml"));

        middleNode.getChildren().clear();
        middleNode.getChildren().add(employeeManageForm);


    }

    @FXML
    void passengerManageOnAction(ActionEvent event) throws IOException {
        AnchorPane passengerManageForm = FXMLLoader.load(this.getClass().getResource("/view/passengersrmanagement_form.fxml"));

        middleNode.getChildren().clear();
        middleNode.getChildren().add(passengerManageForm);

    }

    @FXML
    void financialManageOnAction(ActionEvent event) throws IOException {
        AnchorPane financialManageForm = FXMLLoader.load(this.getClass().getResource("/view/financialmanagement_form.fxml"));

        middleNode.getChildren().clear();
        middleNode.getChildren().add(financialManageForm);

    }

    @FXML
    void routeManageOnAction(ActionEvent event) throws IOException {
        AnchorPane routeManageForm = FXMLLoader.load(this.getClass().getResource("/view/routemanagement_form.fxml"));

        middleNode.getChildren().clear();
        middleNode.getChildren().add(routeManageForm);

    }

    public void settingsOnAction(ActionEvent actionEvent) {
    }

    public void userOnAction(ActionEvent actionEvent) {
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane dashBoardForm = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));


        mainNode.getChildren().clear();
        mainNode.getChildren().add(dashBoardForm);
    }

    String userId;

    public void setUserID(String userId) {
        this.userId=userId;

        try {

            user = getUserName(userId);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        setUserName(user);
    }
}
