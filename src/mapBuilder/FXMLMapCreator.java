package mapBuilder;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FXMLMapCreator {
    private static File file;

    public static void createMap(Pane mapPane, String path) {
        ObservableList<Node> mapElements = mapPane.getChildren();
        double layoutX, layoutY, width, height;
        String id;
        file = new File(path);
        beginFXMLFile();
        addPaneToMapFile(mapPane.getPrefHeight(),mapPane.getPrefWidth());

        for (Node node : mapElements) {
            Bounds nodeBounds = node.getBoundsInParent();
            if (node instanceof Circle) {
                layoutX = nodeBounds.getMinX() + nodeBounds.getWidth() / 2;
                layoutY = nodeBounds.getMinY() + nodeBounds.getHeight() / 2;
                id = node.getId();
                addBeaconToMapFile(layoutX, layoutY, id);
            }
            if (node instanceof Rectangle) {
                layoutX = nodeBounds.getMinX();
                layoutY = nodeBounds.getMinY();
                width = nodeBounds.getWidth();
                height = nodeBounds.getHeight();
                Paint fill = ((Rectangle) node).getFill();
                addObstacleToMapFile(layoutX,layoutY,width,height,fill);
            }

        }

        completeFXMLFile();
    }

    private static void addBeaconToMapFile(double layoutX, double layoutY, String id) {
        String text = "\n                <Circle id=\"" + id + "\" fill=\"#161616\" layoutX=\"" + layoutX + "\" layoutY=\"" + layoutY + "\" radius=\"10.0\" stroke=\"BLACK\" strokeType=\"INSIDE\" />";
        addToFile(text);
    }

    private static void addObstacleToMapFile(double layoutX, double layoutY, double width, double height, Paint fill) {

        String text = "\n                <Rectangle accessibleText=\"80\" arcHeight=\"5.0\" arcWidth=\"5.0\" fill=\""+fill.toString()+"\" height=\""+height+"\" layoutX=\""+layoutX+"\" layoutY=\""+layoutY+"\" stroke=\"BLACK\" strokeType=\"INSIDE\" width=\""+width+"\" />";
        addToFile(text);
    }

    private static void addToFile(String text) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private static void completeFXMLFile(){
        addToFile("            </children>\n" +
                "        </Pane>\n" +
                "    </children>\n" +
                "</GridPane>");
    }
    private  static void beginFXMLFile(){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\bula3\\OneDrive\\Dokumenty\\GitHub\\Advanced-Indoor-Microlocation\\src\\mapBuilder\\mapTemplate.fxml"));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while((line =bufferedReader.readLine())!=null){

                stringBuffer.append(line).append("\n");
            }
            addToFile(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private static void addPaneToMapFile(double height, double width){
        String text = "\n <Pane prefHeight=\""+height+"\" prefWidth=\""+width+"\"> \n <children>";
        addToFile(text);
    }


}
