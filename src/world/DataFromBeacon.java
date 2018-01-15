package world;

import java.util.LinkedList;

/**
 * DataFromBeacon class represents data which beacon is sending to a receiver
 */
public class DataFromBeacon {
    private final LinkedList<Double> RSSI;
    private final LinkedList<Long> timeStamps;
    private String macAddress;

    /**
     * Default constructor
     */
    public DataFromBeacon() {
        RSSI = new LinkedList<>();
        timeStamps = new LinkedList<>();
    }

    /**
     * Gets linked list of RSSI
     */
    public LinkedList<Double> getRSSI() {
        return RSSI;
    }

    public LinkedList<Long> getTimeStamps() {
        return timeStamps;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }
}
