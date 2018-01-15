package world;

import java.util.LinkedList;

/**
 * Receiver class represents receiver object (smartphone etc)
 */
public class Receiver {
    private LinkedList<DataFromBeacon> dataFromBeacon;

    public Receiver(){
        dataFromBeacon = new LinkedList<>();
    }
    /**
     * Gets data from beacon
     */
    public LinkedList<DataFromBeacon> getDataFromBeacon() {
        return dataFromBeacon;
    }

    /**
     * Sets data from beacon
     */
    public void setDataFromBeacon(LinkedList<DataFromBeacon> dataFromBeacon) {
        this.dataFromBeacon = dataFromBeacon;
    }
}
