package mapBuilder;

import beacons.Beacon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import maps.Obstacle;

import java.util.LinkedList;


public class MapBuilderController{
    LinkedList<Beacon> beacons;
    LinkedList<Obstacle> obstacles;

    @FXML
    Pane mapPane;

    @FXML
    VBox parameters;

    @FXML
    public void initialize(){
        beacons=new LinkedList<>();
        obstacles=new LinkedList<>();

    }

    @FXML
    public void obstacleButtonPressed() {
        Obstacle obstacle;
        obstacle = setDefaultObstacleParametres();
        drawObstacle(obstacle);
        obstacles.add(obstacle);
    }

    @FXML
    public void beaconButtonPressed(){
        Beacon beacon;
        beacon = setDefaultBeaconParametres();
        drawBeacon(beacon);
        beacons.add(beacon);
    }
    @FXML
    public void saveButtonPressed(){
    //To Do
    }

    private void drawObstacle(Obstacle obstacle) {
        Rectangle rectangle;
        rectangle = new Rectangle(obstacle.getLayoutX(), obstacle.getLayoutY(), obstacle.getWidth(), obstacle.getHeight());
        rectangle.setFill(Color.web("green"));
        mapPane.getChildren().add(rectangle);
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                System.out.println("Rectangle: Mouse pressed event" +
                        ", synthesized: " + event.isSynthesized());
                setObstaclesParametres();
                rectangle.setFill(Color.web("red"));
                event.consume();
            }
        });
        //Probe to move obstacle
      /*  Wrapper<Point2D> mouseLocation = new Wrapper<>();
        final double handleRadius = 10 ;

        rectangle.setOnMouseDragged(event -> {
            System.out.println("proba");
            if (mouseLocation.value != null) {
                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();
                double newX = rectangle.getX() + deltaX ;
                if (newX >= handleRadius
                        && newX <= rectangle.getX() + rectangle.getWidth() - handleRadius) {
                    rectangle.setX(newX);
                    rectangle.setWidth(rectangle.getWidth() - deltaX);
                }
                double newY = rectangle.getY() + deltaY ;
                if (newY >= handleRadius
                        && newY <= rectangle.getY() + rectangle.getHeight() - handleRadius) {
                    rectangle.setY(newY);
                    rectangle.setHeight(rectangle.getHeight() - deltaY);
                }
                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            }
        });*/
    }

    public void drawBeacon(Beacon beacon){
        Circle circle;

        circle =new Circle(50,50,10);
        circle.setFill(Color.web("black"));
        mapPane.getChildren().addAll(circle);

    }
    // Popup window with obstacle form
    private Obstacle setDefaultObstacleParametres() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //it blocks interaction with othres scenes
        window.setTitle("Obstacle setter");
        window.setMinWidth(250);
        window.setMinHeight(200);


        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        //Defining the width text field
        final TextField width = new TextField();
        width.setPromptText("Enter obstacle width.");
        width.setPrefColumnCount(10);
        width.getText();
        GridPane.setConstraints(width, 0, 0);

        //Defining the height text field
        final TextField height = new TextField();
        height.setPromptText("Enter obstacle height");
        GridPane.setConstraints(height, 0, 1);

        //Defining the transparency text field
        final TextField transparency = new TextField();
        transparency.setPrefColumnCount(15);
        transparency.setPromptText("Enter transparency");
        GridPane.setConstraints(transparency, 0, 2);

        //Defining the Submit button
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 1);
        submit.setOnAction(e->window.close());

        grid.getChildren().addAll(width,height,transparency,submit);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
        Obstacle obstacle =new Obstacle("100","100",width.getText(),height.getText(),"black");
        return obstacle;
    }
    //Popup window with beacon form
    private Beacon setDefaultBeaconParametres(){
        {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL); //it blocks interaction with othres scenes
            window.setTitle("Beacon setter");
            window.setMinWidth(250);
            window.setMinHeight(200);


            //Creating a GridPane container
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(5);
            grid.setHgap(5);


            //Defining the macAddress text field
            Label label;
            label = new Label("Enter beacon MAC address.");
            final TextField macAddress = new TextField();
            macAddress.setPrefColumnCount(10);
            macAddress.getText();
            GridPane.setConstraints(macAddress, 1, 0);


            //Defining the Submit button
            Button submit = new Button("Submit");
            GridPane.setConstraints(submit, 1, 1);
            submit.setOnAction(e->window.close());

            grid.getChildren().addAll(macAddress,submit,label);

            Scene scene = new Scene(grid);
            window.setScene(scene);
            window.showAndWait();
            Beacon beacon =new Beacon(macAddress.getText());
            return beacon;
        }
    }
    //static class Wrapper<T> { T value ; }
    private void setObstaclesParametres(){
        Label label;
        label = new Label("Width:");
        final TextField width = new TextField();
        width.setPrefColumnCount(10);


        parameters.getChildren().addAll(label,width);
    }
}

