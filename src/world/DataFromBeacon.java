package world;

import java.util.LinkedList;

/**
 * DataFromBeacon class represents data which beacon is sending to a receiver
 */
public class DataFromBeacon {
    private LinkedList<Double> RSSI;

    /**
     * Default constructor
     */
    public DataFromBeacon() {
        RSSI = new LinkedList<>();
    }

    /**
     * Gets linked list of RSSI
     */
    public LinkedList<Double> getRSSI() {
        return RSSI;
    }
}
