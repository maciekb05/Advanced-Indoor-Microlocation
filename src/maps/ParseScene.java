package maps;

import world.Beacon;
import world.Obstacle;

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

/**
 * ParseScene class is parsing maps from fxml files
 */
public class ParseScene {
    private LinkedList<Obstacle> obstacles;
    private LinkedList<Beacon> beacons;
    private Document doc;
    private Double worldWidth;
    private Double worldHeight;

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
    public void parseWorld(){
        try {
            loadFxml(path);
            NodeList pane= doc.getElementsByTagName("Pane");
            Element element = (Element) pane.item(0);
            worldHeight = Double.valueOf(element.getAttribute("prefHeight"));
            worldWidth = Double.valueOf(element.getAttribute("prefWidth"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parsing beacons from fxml file
     */
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

    /**
     * Sets path to file with map in fxml
     * @param path path to file
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return Returns list of obstacles
     */
    public LinkedList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * @return Returns list of beacons
     */
    public LinkedList<Beacon> getBeacons() {
        return beacons;
    }

    public Double getWorldWidth() {
        return worldWidth;
    }

    public Double getWorldHeight() {
        return worldHeight;
    }
}


