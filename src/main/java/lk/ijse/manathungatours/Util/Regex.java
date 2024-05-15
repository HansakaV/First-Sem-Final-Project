package lk.ijse.manathungatours.Util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";

        switch (textField){
            case USERNAME:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case PASSWORD:
                filed = "^([0-9]+)$";
                break;
            case BUS:
                filed = "^[A-Z]{2}\\s*-\\s*\\d{4}$";
                break;
            case CID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case DID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case EID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case PID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case NAME:
                filed = "^[a-zA-Z\\s]{1,10}$";
                break;
            case ADDRESS:
                filed = "^[a-zA-Z\\s]{1,10}$";
                break;
            case TEL:
                filed = "^\\d{10}$";
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;

    }
    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-border-color: GREEN");
            return true;
        } else {
            textField.setStyle("-fx-border-color: RED");
            return false;
        }
    }

}
