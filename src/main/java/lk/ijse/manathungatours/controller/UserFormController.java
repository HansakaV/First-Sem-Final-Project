package lk.ijse.manathungatours.controller;

import com.mysql.cj.x.protobuf.MysqlxNotice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.manathungatours.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFormController {

    public TextField txtOldPassword;
    public TextField txtNewPassword;
    public TextField txtReNewPassword;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnChangePassword;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPassword;

    @FXML
    private AnchorPane userPane;
    private String id;
    private String name;
    private String password;

    private void setUserIdOn(String id){lblId.setText(String.valueOf(id));}
    private void setUserName(String name){lblName.setText(String.valueOf(name));}

    private void setPassword(String password){lblPassword.setText(String.valueOf(password));}

    private String getUserID(String Id) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE user_id = ?";
       // System.out.println(Id);

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return resultSet.getString("user_id");
        }
        return null;
    }
    private String getPassword(String Id) throws SQLException {
        String sql = "SELECT password FROM users WHERE user_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return resultSet.getString("password");
        }
        return null;
    }
    private String getUserName(String Id) throws SQLException {
        String sql = "SELECT user_name FROM users WHERE user_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return resultSet.getString("user_name");
        }
        return null;
    }


    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.userPane.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

    }
    String userId;
    public void setUserID(String userId){
        this.userId = userId;
        try {
            id = getUserID(userId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setUserIdOn(id);

        try {
            name = getUserName(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setUserName(name);

        try {
            password =getPassword(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setPassword(password);
    }
    @FXML
    void passwordChangeOnAction(ActionEvent event) throws SQLException {
        String oldPassword = txtOldPassword.getText();
        String newPassword = txtNewPassword.getText();
        String reNewPassword = txtReNewPassword.getText();

        if (newPassword.equals(reNewPassword)){
            changePassword(newPassword,userId);
            new Alert(Alert.AlertType.CONFIRMATION,"PASSWORD CHANGE SUCCESSFULLY !!").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"PASSWORD DOES NOT MATCHING !!").show();
        }
    }
    public boolean changePassword(String password,String Id) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, password);
        pstm.setObject(2, Id);

        return pstm.executeUpdate() > 0;

    }

}
