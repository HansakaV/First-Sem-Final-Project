package lk.ijse.manathungatours.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.manathungatours.Util.Regex;
import lk.ijse.manathungatours.db.DbConnection;
//import lk.ijse.manathungatours.Util.TextField;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink hypSignUp;

    @FXML
    private AnchorPane pane;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUserName;

    @FXML
    void lExitOnAction(ActionEvent event) {
        System.exit(0);

    }

    String userId;

    @FXML
    void loginOnAction(ActionEvent event) {
        userId = txtUserName.getText();
        String pw = pwdPassword.getText();
        isValied();

        try {
            checkCredential(userId, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException {
        String sql = "SELECT user_id, password FROM users WHERE user_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPw = resultSet.getString("password");

            if(pw.equals(dbPw)) {
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
        }
    }

    private void navigateToTheDashboard() throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
        AnchorPane anchorPane = loader.load();

        DashboardFormController dashboardFormController =loader.getController();
        dashboardFormController.setUserID(userId);

        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setScene(anchorPane.getScene());
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        stage.show();
        pane.getScene().getWindow().hide();
    }



    @FXML
    void signUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/registration_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");

        stage.show();
    }

    public void idOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.USERNAME,txtUserName);}

    public void passwordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.PASSWORD,pwdPassword);}

   public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.PASSWORD,pwdPassword)) return false;
        if (!Regex.setTextColor(lk.ijse.manathungatours.Util.TextField.USERNAME,txtUserName)) return false;
        return true;
    }

    public void loadDashBoardOnAction(KeyEvent keyEvent) throws IOException {

    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Start  Form");
    }
}



