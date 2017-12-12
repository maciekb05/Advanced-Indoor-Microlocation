package world;

import java.util.LinkedList;

public class DataFromBeacon {
    String macAdress;
    LinkedList<Integer> RSSI;

    public DataFromBeacon() {
        macAdress = new String();
        RSSI = new LinkedList<>();
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public LinkedList<Integer> getRSSI() {
        return RSSI;
    }

    public void setRSSI(LinkedList<Integer> RSSI) {
        this.RSSI = RSSI;
    }
}
