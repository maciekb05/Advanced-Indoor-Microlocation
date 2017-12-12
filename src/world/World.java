package world;

import java.util.LinkedList;

public class World {
    private LinkedList<Beacon> beacons;
    private LinkedList<Obstacle> obstacles;
    private LinkedList<Receiver> receivers;
    private Integer width;
    private Integer height;

    public World() {
        beacons = new LinkedList<>();
        obstacles = new LinkedList<>();
        receivers = new LinkedList<>();
        width = 0;
        height = 0;
    }

    public LinkedList<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(LinkedList<Beacon> beacons) {
        this.beacons = beacons;
    }

    public LinkedList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(LinkedList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public LinkedList<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(LinkedList<Receiver> receivers) {
        this.receivers = receivers;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
