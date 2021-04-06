package de.wko.mdb.ui.dialog;

import javafx.scene.control.Alert;

public class MessageBox {
    public static void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setContentText("Can not connect to remote rest server");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public static void alert2() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setContentText("Client not connected");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
