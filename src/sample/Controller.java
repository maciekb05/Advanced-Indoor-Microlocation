package sample;

import world.*;
import beacons.BeaconLoader;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import maps.ParseScene;

import java.io.File;
import java.util.LinkedList;

public class Controller {

    World world;
    LinkedList<Text> labelRSSI;

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

    @FXML
    public void initialize() {
        chooseBeaconButton.setDisable(true);
        world = new World();
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

            for (Beacon beacon :parseScene.getBeacons()){
                addBeacon(beacon);
            }
            world.setBeacons(parseScene.getBeacons());

            for (Obstacle obstacle : parseScene.getObstacles()) {
                addObstacle(obstacle);
            }
            world.setObstacles(parseScene.getObstacles());
        }
        mainBorderPane.getScene().getWindow().sizeToScene();
        chooseBeaconButton.setDisable(false);
    }

    @FXML
    public void chooseBeacons(){
        Receiver receiver = new Receiver();
        world.getReceivers().add(receiver);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Beacons Files","*.csv"));
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("src/files"));
        File selectedFile =  fileChooser.showOpenDialog(mapPane.getScene().getWindow());
        labelRSSI = new LinkedList<>();
        if (selectedFile != null) {
            BeaconLoader beaconLoader = new BeaconLoader();
            beaconLoader.loadRSSI(world.getBeacons(), world.getReceivers().getLast(), selectedFile);
        }
        if (selectedFile != null) {
            hBoxRSSI.getChildren().clear();
            for(Beacon beacon : world.getBeacons()) {
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

        for(int i = 0; i < world.getBeacons().size(); ++i) {
            threads.add(new SimulationThread(labelRSSI.get(i), world.getBeacons().get(i), world.getReceivers().get(0).getDataFromBeacon().get(i)));
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
        if (beacon.getX() != null && beacon.getY() != null) {
            x = beacon.getX();
            y = beacon.getY();
            mapPane.getChildren().add(new Circle(x, y, 10));
        }
    }

}
