package ui.util;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void showInformationAlert(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.show();
    }

    public static void showConfirmingAlert(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(text);
        alert.show();
    }

    public static void showWarningAlert(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(text);
        alert.show();
    }

    public static void showErrorAlert(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(text);
        alert.show();
    }

    public static void main(String[] args){
        showConfirmingAlert("Information");
    }
}
