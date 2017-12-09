package sample;

import beacons.Beacon;
import beacons.BeaconLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    LinkedList<Text> labelRSSI;

    @FXML
    BorderPane mainBorderPane;
    @FXML
    Pane mapPane;
    @FXML
    HBox hBoxRSSI;

    @FXML
    public void initialize() {

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
        labelRSSI = new LinkedList<>();
        if (selectedFile != null) {
            BeaconLoader beaconLoader = new BeaconLoader();
            beaconLoader.loadRSSI(beacons,selectedFile);
        }
        if (selectedFile != null) {
            hBoxRSSI.getChildren().clear();
            for(Beacon beacon : beacons) {
                VBox vBox = new VBox();
                vBox.getChildren().add(new Text(beacon.getMacAdress()));
                labelRSSI.add(new Text("0"));
                vBox.getChildren().add(labelRSSI.getLast());
                vBox.setPadding(new Insets(10));
                hBoxRSSI.getChildren().add(vBox);
            }

        }
        mainBorderPane.getScene().getWindow().sizeToScene();
    }

    @FXML
    public void simulate(){
        LinkedList<SimulationThread> threads = new LinkedList<>();

        for(int i = 0; i < beacons.size(); ++i) {
            threads.add(new SimulationThread(labelRSSI.get(i),beacons.get(i)));
        }
        try{
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted Simulation");
        }
        for(SimulationThread thread : threads) {
            thread.start();
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
//        Circle circle = new Circle();
        if (beacon.getLayoutX() != null && beacon.getLayoutY() != null) {
            x = beacon.getLayoutX();
            y = beacon.getLayoutY();

//            circle.setCenterX(x);
//            circle.setCenterY(y);
            mapPane.getChildren().add(new Circle(x, y, 10));
        }


    }

}
