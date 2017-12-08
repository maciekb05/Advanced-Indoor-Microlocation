package maps;

import beacons.Beacon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ParseScene {
    private LinkedList<Obstacle> obstacles;
    private LinkedList<Beacon> beacons;
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
                    obstacles.add(new Obstacle(eElement.getAttribute("layoutX"),eElement.getAttribute("layoutY"),eElement.getAttribute("width"),eElement.getAttribute("height"),eElement.getAttribute("fill")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void parseBeacons() {
        try {
            loadFxml(path);
            beacons = new LinkedList<>();

            NodeList nList = doc.getElementsByTagName("Circle");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                        beacons.add(new Beacon(eElement.getAttribute("layoutX"),eElement.getAttribute("layoutY"), eElement.getAttribute("id")));
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }

    private void loadFxml(String path) throws IOException, SAXException, ParserConfigurationException {
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public LinkedList<Obstacle> getObstacles() {
        return obstacles;
    }

    public LinkedList<Beacon> getBeacons() {
        return beacons;
    }
}


