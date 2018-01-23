package world;

import java.util.LinkedList;

/**
 * World class represent a room or some indoor location
 */
public class World {
    private LinkedList<Beacon> beacons;
    private LinkedList<Obstacle> obstacles;
    private final Receiver receiver;
    private Double height;
    private Double width;


    /**
     * Default constructor
     */
    public World() {
        beacons = new LinkedList<>();
        obstacles = new LinkedList<>();
        receiver = new Receiver();
    }

    /**
     * Gets linked list of beacons
     */
    public LinkedList<Beacon> getBeacons() {
        return beacons;
    }

    /**
     * Sets linked list of beacons
     * @param beacons linked list of beacons
     */
    public void setBeacons(LinkedList<Beacon> beacons) {
        this.beacons = beacons;
    }

    /**
     * Sets linked list of obstacles
     * @param obstacles linked list of obstacles
     */
    public void setObstacles(LinkedList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * Gets linked list of receivers
     */
    public Receiver getReceiver() {
        return receiver;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }
}
