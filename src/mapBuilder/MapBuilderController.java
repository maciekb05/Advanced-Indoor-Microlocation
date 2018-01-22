package mapBuilder;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import world.*;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedList;


public class MapBuilderController{
    private LinkedList<Beacon> beacons;
    private LinkedList<Obstacle> obstacles;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    private double mapWidth, mapHeight;
    private Bounds boundsInScene, circleBoundsInScene, rectangleBoundsInScene;
    double mapMinX, mapMinY, mapMaxX, mapMaxY;
    Circle currentCircle;
    Rectangle currentRectangle;

    @FXML
    Pane mapPane;

    @FXML
    TextField widthTextField;

    @FXML
    public void initialize(){
        beacons=new LinkedList<>();
        obstacles=new LinkedList<>();
        mapHeight = 350;
        mapWidth = 400;
        boundsInScene = mapPane.localToScene(mapPane.getBoundsInLocal());
        mapMinY = boundsInScene.getMinY();
        mapMinX = boundsInScene.getMinX();
        mapMaxX = mapMinX + mapWidth;
        mapMaxY = boundsInScene.getMaxY() + mapHeight;
    }

    @FXML
    public void obstacleButtonPressed() {
        addObstacle();
    }

    @FXML
    public void beaconButtonPressed(){
        addBeacon();
    }

    @FXML
    public void saveButtonPressed(){
        FXMLMapCreator.createMap(mapPane,"we");
    }

    @FXML
    public void changeMapWidth(){
        mapPane.setPrefWidth(Integer.parseInt(widthTextField.getText()));
    }  @FXML
    public void changeMapHeight(){
        mapPane.setPrefWidth(Integer.parseInt(widthTextField.getText()));
    }

    private void addObstacle(){
        Rectangle rectangle;
        //rectangle.setFill(Color.web("blue"));
        rectangle = setObstacleParametres();
        rectangle.setOnMousePressed(rectangleOnMousePressedEventHandler);
        rectangle.setOnMouseDragged(rectangleOnMouseDraggedEventHandler);
        mapPane.getChildren().addAll(rectangle);
        fixPositionOfNode(rectangle);
    }


    private void addBeacon(){
        Circle circle;

        circle = setBeaconParametres();
        circle.setOnMousePressed(circleOnMousePressedEventHandler);
        circle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        circle.setFill(Color.web("black"));
        mapPane.getChildren().addAll(circle);

    }
    // Popup window with obstacle form

    private Rectangle setObstacleParametres() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //it blocks interaction with othres scenes
        window.setTitle("Obstacle setter");
        window.setMinWidth(250);
        window.setMinHeight(200);

        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(6);
        grid.setHgap(6);

        //Defining the width text field
        final TextField layoutX = createTextField("X layout");
        GridPane.setConstraints(layoutX, 0, 0);

        //Defining the width text field
        final TextField layoutY = createTextField("Y Layout");
        GridPane.setConstraints(layoutY, 0, 1);

        //Defining the width text field
        final TextField width = createTextField("Enter obstacle width.");
        GridPane.setConstraints(width, 0, 2);

        //Defining the height text field
        final TextField height = createTextField("Enter obstacle height");
        GridPane.setConstraints(height, 0, 3);

        //Defining the transparency text field
        final TextField transparency = createTextField("Enter transparency");
        GridPane.setConstraints(transparency, 0, 4);

        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLUE);

        GridPane.setConstraints(colorPicker,0,5);

        //Defining the Submit button
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 5);
        submit.setOnAction(e->window.close());

        grid.getChildren().addAll(layoutX,layoutY,width,height,transparency,submit,colorPicker);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

        Rectangle rectangle = new Rectangle(Double.parseDouble(layoutX.getText()),Double.parseDouble(layoutY.getText()),Double.parseDouble(width.getText()),Double.parseDouble(height.getText()));
        rectangle.setFill(colorPicker.getValue());
        return rectangle;
    }

    private TextField createTextField(String promptText){
        TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        textField.setPromptText(promptText);
        return textField;
    }

    //Popup window with beacon form
    private Circle setBeaconParametres(){
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


            final TextField layoutX = createTextField("X layout");
            GridPane.setConstraints(layoutX, 0, 0);

            //Defining the width text field
            final TextField layoutY = createTextField("Y Layout");
            GridPane.setConstraints(layoutY, 0, 1);

            //Defining the macAddress text field
            final TextField macAddress = createTextField("MAC Adress");
            macAddress.setPrefColumnCount(10);
            GridPane.setConstraints(macAddress, 0, 2);


            //Defining the Submit button
            Button submit = new Button("Submit");
            GridPane.setConstraints(submit, 1, 1);
            submit.setOnAction(e->window.close());

            grid.getChildren().addAll(macAddress,submit,layoutX,layoutY);


            Scene scene = new Scene(grid);
            window.setScene(scene);
            window.showAndWait();
            Circle beacon = new Circle(Double.parseDouble(layoutX.getText()),Double.parseDouble(layoutY.getText()),10);
            beacon.setId(macAddress.getText());
            return beacon;
        }
    }


    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            t -> {
                currentCircle = (Circle)t.getSource();
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
                orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    circleBoundsInScene = currentCircle.localToScene(currentCircle.getBoundsInLocal());
                    if(t.getSceneX()>mapMinX && t.getSceneY()>mapMinY && t.getSceneX()<mapMaxX && t.getSceneY()<mapMaxY) {
                        if (checkForCollisions(circleBoundsInScene)) {
                            ((Circle) (t.getSource())).setTranslateX(newTranslateX);
                            ((Circle) (t.getSource())).setTranslateY(newTranslateY);
                        }
                    }
                    fixPositionOfNode(currentCircle);


                }
            };

    EventHandler<MouseEvent> rectangleOnMousePressedEventHandler =
            t -> {
                currentRectangle = (Rectangle) t.getSource();

                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Rectangle)(t.getSource())).getTranslateX();
                orgTranslateY = ((Rectangle)(t.getSource())).getTranslateY();
            };

    EventHandler<MouseEvent> rectangleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    rectangleBoundsInScene = currentRectangle.localToScene(currentRectangle.getBoundsInLocal());

                    if(t.getSceneX()>mapMinX && t.getSceneY()>mapMinY && t.getSceneX()<mapMaxX && t.getSceneY()<mapMaxY) {
                        if (checkForCollisions(rectangleBoundsInScene)) {
                            ((Rectangle) (t.getSource())).setTranslateX(newTranslateX);
                            ((Rectangle) (t.getSource())).setTranslateY(newTranslateY);
                        }
                    }
                    fixPositionOfNode(currentRectangle);


                }
            };

            private boolean checkForCollisions(Bounds nodeBounds){
                System.out.println(mapMaxX);
               return nodeBounds.getMaxX()<mapMaxX && nodeBounds.getMaxY()<mapMaxY && nodeBounds.getMinY()>mapMinY && nodeBounds.getMinX()>mapMinX ;
            }

            private void fixPositionOfNode(Node node){
                Bounds nodeBounds = node.localToScene(node.getBoundsInLocal());
                while (nodeBounds.getMinX()<=mapMinX){
                    node.setLayoutX(node.getLayoutX()+1);
                    nodeBounds = node.localToScene(node.getBoundsInLocal());
                }
                while (nodeBounds.getMinY()<=mapMinY){
                    node.setLayoutY(node.getLayoutY()+1);
                    nodeBounds = node.localToScene(node.getBoundsInLocal());
                }
                while (nodeBounds.getMaxX()>=mapMaxX){
                    node.setLayoutX(node.getLayoutX()-1);
                    nodeBounds = node.localToScene(node.getBoundsInLocal());
                }
                while (nodeBounds.getMaxY()>=mapMaxY){
                    node.setLayoutY(node.getLayoutY()-1);
                    nodeBounds = node.localToScene(node.getBoundsInLocal());
                }
            }

}

