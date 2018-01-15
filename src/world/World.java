package world;

import java.util.LinkedList;

/**
 * World class represent a room or some indoor location
 */
public class World {
    private LinkedList<Beacon> beacons;
    private LinkedList<Obstacle> obstacles;
    private final Receiver receiver;

    /**
     * Default constructor
     */
    public World() {
        beacons = new LinkedList<>();
        obstacles = new LinkedList<>();
        receiver = new Receiver();
        Integer width = 0;
        Integer height = 0;
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
}
