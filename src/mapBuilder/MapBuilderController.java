package mapBuilder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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

import java.io.File;
import java.util.LinkedList;


public class MapBuilderController{
    private static Integer beaconRadius= 10;

    private LinkedList<Beacon> beacons;
    private LinkedList<Obstacle> obstacles;
    private double orgSceneX, orgSceneY;
    private double orgTranslateY, orgTranslateX;
    private double mapMinX, mapMinY, mapMaxX, mapMaxY;
    private Circle currentCircle;
    private Rectangle currentRectangle;

    @FXML
    Pane mapPane;
    @FXML
    Button saveButton;
    @FXML
    Button obstacleButton;
    @FXML
    Button beaconButton;
    @FXML
    Button newButton;

    @FXML
    TextField widthTextField;

    @FXML
    public void initialize(){
        beacons=new LinkedList<>();
        obstacles=new LinkedList<>();
        double mapHeight = 350;
        double mapWidth = 400;
        Bounds boundsInScene = mapPane.localToScene(mapPane.getBoundsInLocal());
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Map files","*.fxml"));
        fileChooser.setInitialDirectory(new File("src/files"));

        Stage stage = (Stage) mapPane.getScene().getWindow();

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            System.out.println(file.getPath());
            FXMLMapCreator.createMap(mapPane,file.getPath());
            stage.close();
        }
    }

   /* @FXML
    public void changeMapWidth(){
        mapPane.setPrefWidth(Double.parseInt(widthTextField.getText()));
    }  @FXML
    public void changeMapHeight(){
        mapPane.setPrefWidth(Double.parseInt(widthTextField.getText()));
    }
*/

    private void addObstacle(){
        Rectangle rectangle;
        //rectangle.setFill(Color.web("blue"));
        rectangle = setObstacleParametres();
        rectangle.setOnMousePressed(rectangleOnMousePressedEventHandler);
        rectangle.setOnMouseDragged(rectangleOnMouseDraggedEventHandler);
        mapPane.getChildren().addAll(rectangle);
        fixPositionOfNode(rectangle);
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

        Text error = new Text();
        GridPane.setConstraints(error,0,6);
        error.setFill(Color.RED);

        Rectangle rectangle = new Rectangle();
        submit.setOnAction( e->{
            if(isStringDouble(width.getText()) && isStringDouble(height.getText()) && isStringDouble(layoutX.getText()) && isStringDouble(layoutY.getText()) && isStringDouble(transparency.getText()) )
            {
                Double heightDouble =Double.parseDouble(height.getText());
                Double widthtDouble =Double.parseDouble(width.getText());
                Double XDouble =Double.parseDouble(layoutX.getText());
                Double YDouble =Double.parseDouble(layoutY.getText());

                if(checkRectangleSizeAndPosition(heightDouble,widthtDouble,XDouble,YDouble)){
                    rectangle.setHeight(heightDouble);
                    rectangle.setWidth(widthtDouble);
                    rectangle.setX(XDouble);
                    rectangle.setY(YDouble);
                    window.close();
                }else {
                    error.setText("Invalid size or postion");
                }
            }
            else {
                error.setText("Values must be Doubles");
            }
        });

        grid.getChildren().addAll(layoutX,layoutY,width,height,transparency,submit,colorPicker,error);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

        rectangle.setFill(colorPicker.getValue());
        return rectangle;
    }

    private boolean checkRectangleSizeAndPosition(Double height, Double width, Double layoutX, Double layoutY) {
            if(height<0 || width<0 || layoutX<0 || layoutY<0 || layoutX+width+mapMinX>mapMaxX || layoutY+height+mapMinY>mapMaxY )
                return false;
            else
            return true;
    }


    private void addBeacon(){
        Circle circle;

        circle = setBeaconParametres();
        circle.setOnMousePressed(circleOnMousePressedEventHandler);
        circle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        circle.setFill(Color.web("black"));
        mapPane.getChildren().addAll(circle);

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


            Circle beacon =new Circle();

            Text error = new Text();
            GridPane.setConstraints(error,0,6);
            error.setFill(Color.RED);

            submit.setOnAction( e->{
                if( isStringDouble(layoutX.getText()) && isStringDouble(layoutY.getText()) ){
                    Double doubleX = Double.parseDouble(layoutX.getText());
                    Double doubleY = Double.parseDouble(layoutY.getText());
                    if(checkBeaconSizeAndPosition(doubleX,doubleY)){
                        beacon.setCenterX(doubleX);
                        beacon.setCenterY(doubleY);
                        beacon.setId(macAddress.getText());
                        beacon.setRadius(beaconRadius);
                        window.close();

                    }else {
                        error.setText("Invalid position");
                    }
                }
                else {
                    error.setText("Values must be Doubles");
                }
            });

            grid.getChildren().addAll(layoutX,layoutY,submit,macAddress,error);

            Scene scene = new Scene(grid);
            window.setScene(scene);
            window.showAndWait();
            return beacon;
        }
    }

    private boolean checkBeaconSizeAndPosition(Double doubleX, Double doubleY) {
        if(doubleX-beaconRadius<0 || doubleY-beaconRadius<0 || doubleX+mapMinX+beaconRadius>mapMaxX || doubleY+mapMinY+beaconRadius> mapMaxY)
            return false;
        else
            return true;
    }


    private EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            t -> {
                currentCircle = (Circle)t.getSource();
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
                orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
            };

    private EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    Bounds circleBoundsInScene = currentCircle.localToScene(currentCircle.getBoundsInLocal());
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
                    Bounds rectangleBoundsInScene = currentRectangle.localToScene(currentRectangle.getBoundsInLocal());

                    if(t.getSceneX()>mapMinX && t.getSceneY()>mapMinY && t.getSceneX()<mapMaxX && t.getSceneY()<mapMaxY) {
                        if (checkForCollisions(rectangleBoundsInScene)) {
                            ((Rectangle) (t.getSource())).setTranslateX(newTranslateX);
                            ((Rectangle) (t.getSource())).setTranslateY(newTranslateY);
                        }
                    }
                    System.out.println("Max:"+mapMaxX);
                    fixPositionOfNode(currentRectangle);


                }
            };

            private boolean checkForCollisions(Bounds nodeBounds){
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

    public void newMapButtonPressed(ActionEvent actionEvent) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //it blocks interaction with othres scenes
        window.setTitle("Map setter");
        window.setMinWidth(250);
        window.setMinHeight(200);

        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Defining the width text field
        Text widthText = new Text();
        widthText.setText("Map Width:");
        Text heightText = new Text();
        heightText.setText("Map Height:");

        final TextField width = createTextField("");
        GridPane.setConstraints(widthText, 0, 0);
        GridPane.setConstraints(width, 0, 1);

        //Defining the width text field
        final TextField height = createTextField("");
        GridPane.setConstraints(heightText, 0, 2);
        GridPane.setConstraints(height, 0, 3);

        //Defining the Submit button
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 0, 4);
        Text error = new Text();
        GridPane.setConstraints(error,0,5);
        submit.setOnAction(
                e->{
                    if(isStringDouble(width.getText()) && isStringDouble(height.getText())) {
                        window.close();
                        mapPane.setPrefWidth(Double.parseDouble(width.getText()));
                        mapPane.setPrefHeight(Double.parseDouble(height.getText()));
                        mapMaxX=mapMinX+Double.parseDouble(width.getText());
                        mapMaxY=mapMinY+Double.parseDouble(height.getText());

                    }
                    else {
                        error.setText("Values must be doubles");
                        error.setFill(Color.RED);
                    }
                }
        );

        grid.getChildren().addAll(widthText,heightText,width,height,submit,error);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

        newButton.setDisable(true);
        saveButton.setDisable(false);
        obstacleButton.setDisable(false);
        beaconButton.setDisable(false);
    }

    private boolean isStringDouble(String s)
    {
        try
        {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    private TextField createTextField(String promptText){
        TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        textField.setPromptText(promptText);
        return textField;
    }
}

