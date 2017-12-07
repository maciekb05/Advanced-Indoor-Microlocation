package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import maps.Obstacle;
import maps.ParseScene;

import java.io.File;
import java.util.LinkedList;

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
    BorderPane mainBorderPane;
    @FXML
    Button mapChooserButton;
    @FXML
    Button beaconChooserButton;
    @FXML
    Pane mapPane;

    @FXML
    public void initialize() {
        RSSI1.setText("000");
        RSSI2.setText("000");
        RSSI3.setText("000");
        RSSI4.setText("000");

        // Dodac do drugiego choosera jak juz bedza nadane macadressy
        //BeaconLoader.loadRSSI(parseScene.getBeacons(),new File("./src/files/beacons.csv"));

 /*       BeaconLoader myBeaconLoader = new BeaconLoader(new File("./src/files/beacons.csv"));


        myBeaconLoader.load();
        ParseScene parseScene = new ParseScene();
        parseScene.parseObstacles();
        parseScene.parseBacons(myBeaconLoader.getBeacons());



            for(Beacon beacon : myBeaconLoader.getBeacons()) {

                addBeacon(beacon);
            }*/
    }

    @FXML
    public void chooseMap(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Map files","*.fxml"));
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("src"));
        File selectedFile =  fileChooser.showOpenDialog(mapPane.getScene().getWindow());

        if (selectedFile != null) {
            mapPane.getChildren().clear();
            ParseScene parseScene = new ParseScene();
            parseScene.setPath(selectedFile.getPath());
            parseScene.parseObstacles();
            parseScene.parseBeacons();

            for (Beacon beacon:parseScene.getBeacons()){
                addBeacon(beacon);
            }

            for (Obstacle obstacle : parseScene.getObstacles()) {
                addObstacle(obstacle);
            }

        }

    }

    public void addObstacle(Obstacle obstacle) {
        double x, y, width, height;
        String fill;
        Rectangle rectangle;
        x = obstacle.getLayoutX();
        y = obstacle.getLayoutY();
        width = obstacle.getWidth();
        height = obstacle.getHeight();
        fill = obstacle.getFill();
        rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(Color.web(fill));
        mapPane.getChildren().add(rectangle);
    }

    public void addBeacon(Beacon beacon) {
        Double x, y;
        Circle circle = new Circle();
        if (beacon.getLayoutX() != null && beacon.getLayoutY() != null) {
            x = beacon.getLayoutX();
            y = beacon.getLayoutY();

            circle.setCenterX(x);
            circle.setCenterY(y);
            mapPane.getChildren().add(new Circle(x, y, 10));
        }


    }

}
