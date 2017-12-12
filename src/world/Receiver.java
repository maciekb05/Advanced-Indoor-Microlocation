package world;

import java.util.LinkedList;

public class Receiver {
    LinkedList<DataFromBeacon> dataFromBeacon;
    LinkedList<Integer> currentRSSI;

    public LinkedList<DataFromBeacon> getDataFromBeacon() {
        return dataFromBeacon;
    }

    public void setDataFromBeacon(LinkedList<DataFromBeacon> dataFromBeacon) {
        this.dataFromBeacon = dataFromBeacon;
    }

    public LinkedList<Integer> getCurrentRSSI() {
        return currentRSSI;
    }

    public void setCurrentRSSI(LinkedList<Integer> currentRSSI) {
        this.currentRSSI = currentRSSI;
    }
}
