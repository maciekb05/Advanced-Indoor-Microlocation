package sample;

import javafx.fxml.FXML;
import javafx.scene.text.*;

public class Controller {
    @FXML
    Text RSSI1;
    @FXML
    Text RSSI2;
    @FXML
    Text RSSI3;
    @FXML
    Text RSSI4;

    @FXML
    public void initialize() {
        RSSI1.setText("000");
        RSSI2.setText("000");
        RSSI3.setText("000");
        RSSI4.setText("000");
    }
}
