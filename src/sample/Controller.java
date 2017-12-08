package sample;

import beacons.Beacon;
import beacons.BeaconLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import maps.Obstacle;
import maps.ParseScene;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.CyclicBarrier;

public class Controller {

    LinkedList<Beacon> beacons;
    LinkedList<Obstacle> obstacles;

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
    Pane mapPane;

    @FXML
    public void initialize() {
        RSSI1.setText("000");
        RSSI2.setText("000");
        RSSI3.setText("000");
        RSSI4.setText("000");

        // Dodac do drugiego choosera jak juz bedza nadane macadressy
        //BeaconLoader.loadRSSI(parseScene.getBeacons(),new File("./src/files/beacons.csv"));

    }

    @FXML
    public void chooseMap(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Map files","*.fxml"));
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("src/files"));
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
            beacons = parseScene.getBeacons();

            for (Obstacle obstacle : parseScene.getObstacles()) {
                addObstacle(obstacle);
            }
            obstacles = parseScene.getObstacles();
        }
        mainBorderPane.getScene().getWindow().sizeToScene();
    }

    @FXML
    public void chooseBeacons(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Beacons Files","*.csv"));
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("src/files"));
        File selectedFile =  fileChooser.showOpenDialog(mapPane.getScene().getWindow());

        if (selectedFile != null) {
            BeaconLoader beaconLoader = new BeaconLoader();
            beaconLoader.loadRSSI(beacons,selectedFile);
        }

    }

    @FXML
    public void simulate(){
        Thread simulator0 = new SimulationThread(RSSI1, beacons.get(0));
        Thread simulator1 = new SimulationThread(RSSI2, beacons.get(1));
        Thread simulator2 = new SimulationThread(RSSI3, beacons.get(2));
        Thread simulator3 = new SimulationThread(RSSI4, beacons.get(3));
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        simulator0.start();
        simulator1.start();
        simulator2.start();
        simulator3.start();
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
