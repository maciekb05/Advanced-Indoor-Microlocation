package maps;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sample.Beacon;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ParseScene {
    private LinkedList<Obstacle> obstacles;
    private Document doc;
    private String path = "./src/files/firstmap.fxml";

    public void parseObstacles(){
        try {
            loadFxml(path);
            obstacles = new LinkedList<>();

            NodeList nList = doc.getElementsByTagName("Rectangle");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    obstacles.add(new Obstacle(eElement.getAttribute("layoutX"),eElement.getAttribute("layoutY"),eElement.getAttribute("width"),eElement.getAttribute("height")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseBacons(LinkedList<Beacon> beacons){
        try {
            loadFxml(path);

            NodeList nList = doc.getElementsByTagName("Circle");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    for (Beacon beacon: beacons) {
                        if(beacon.getMacAdress().equals(eElement.getAttribute("id"))){
                            beacon.setLayoutX(Double.parseDouble(eElement.getAttribute("layoutX")));
                            beacon.setLayoutY(Double.parseDouble(eElement.getAttribute("layoutY")));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFxml(String path) throws IOException, SAXException, ParserConfigurationException {
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
    }
}


