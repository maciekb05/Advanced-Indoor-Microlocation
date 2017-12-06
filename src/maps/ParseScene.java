package maps;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;

public class ParseScene {
    LinkedList<Obstacle> obstacles;

    public void parseObstacles(){
        try {

            File fXmlFile = new File("./src/maps/firstmap.fxml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Rectangle");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    obstacles.add(new Obstacle(eElement.getAttribute("layoutX"),eElement.getAttribute("layoutY"),eElement.getAttribute("width"),eElement.getAttribute("height")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


