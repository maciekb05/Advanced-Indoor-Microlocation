package sample;

import javafx.scene.layout.*;
import world.*;
import beacons.BeaconLoader;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import maps.ParseScene;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private World world;
    private LinkedList<Text> labelRSSI;

    @FXML
    BorderPane mainBorderPane;
    @FXML
    Pane mapPane;
    @FXML
    HBox hBoxRSSI;
    @FXML
    Button chooseMapButton;
    @FXML
    Button chooseBeaconButton;

    /**
     * Method to initialize world and window
     */
    @FXML
    public void initialize() {
        chooseBeaconButton.setDisable(true);
        world = new World();
        labelRSSI = new LinkedList<>();
    }

    /**
     * OnChoose method for choosing file with map.
     * It loads, parses and makes beacon and receiver objects,
     * prints map on pane, makes button active for choosing file of data from beacons.
     */
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
            parseScene.parseWorld();
            world.setHeight(parseScene.getWorldHeight());
            world.setWidth(parseScene.getWorldWidth());

            for (Beacon beacon :parseScene.getBeacons()){
                addBeacon(beacon);
            }
            world.setBeacons(parseScene.getBeacons());

            for (Obstacle obstacle : parseScene.getObstacles()) {
                addObstacle(obstacle);
            }
            world.setObstacles(parseScene.getObstacles());
        }
        mapPane.setPrefHeight(world.getHeight());
        mapPane.setPrefWidth(world.getWidth());
        mapPane.setStyle("-fx-border-color: black");
        mainBorderPane.getScene().getWindow().sizeToScene();
        chooseBeaconButton.setDisable(false);
    }

    /**
     * Method for choosing file or files with data from beacons.
     * It loads, parses and adds data to beacons.
     */
    @FXML
    public void chooseBeacons(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Beacons Files","*.csv"));
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("src/files"));
        List<File> selectedFiles =  fileChooser.showOpenMultipleDialog(mapPane.getScene().getWindow());

        for(File selectedFile : selectedFiles){
            if (selectedFile != null) {
                BeaconLoader beaconLoader = new BeaconLoader();
                beaconLoader.loadRSSI(world.getBeacons(), world.getReceiver(), selectedFile);
                VBox vBox = new VBox();
                vBox.getChildren().add(new Text(world.getReceiver().getDataFromBeacon().getLast().getMacAddress()));
                labelRSSI.add(new Text("0"));
                vBox.getChildren().add(labelRSSI.getLast());
                vBox.setPadding(new Insets(10));
                hBoxRSSI.getChildren().add(vBox);
            }
            mainBorderPane.getScene().getWindow().sizeToScene();
        }
    }

    /**
     * OnClick method for Simulate! button.
     * It starts simulation thread.
     */
    @FXML
    public void simulate(){
        SimulationThread thread = new SimulationThread(world, labelRSSI);
        thread.start();
    }

    private void addObstacle(Obstacle obstacle) {
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

    private void addBeacon(Beacon beacon) {
        Double x, y;
        if (beacon.getX() != null && beacon.getY() != null) {
            x = beacon.getX();
            y = beacon.getY();
            mapPane.getChildren().add(new Circle(x, y, 10));
        }
    }
}
