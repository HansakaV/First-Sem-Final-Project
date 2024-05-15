package lk.ijse.manathungatours.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LogOutFormController {

    public ImageView rootNode;
    @FXML
    private Button btnexit;

    @FXML
    private Button btnlogin;

    @FXML
    void exitOnAction(ActionEvent event) {

    }

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();

        stage.setScene(scene);

        stage.setTitle("Login Form");

        stage.show();

    }

}
