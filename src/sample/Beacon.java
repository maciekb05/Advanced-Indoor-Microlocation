package sample;

import java.util.LinkedList;

public class Beacon {
    private String macAdress;
    private Integer timeStamp;
    private LinkedList<Integer> rssiList;

    Beacon(String macAdress){
        this.macAdress = macAdress;
    }
    Beacon(String macAdress, Integer timeStamp, LinkedList<Integer> rssiList){
        this.macAdress = macAdress;
        this.timeStamp = timeStamp;
        this.rssiList = rssiList;
    }

    public void setRssiList(LinkedList<Integer> rssiList) {
        this.rssiList = rssiList;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LinkedList<Integer> getRssiList() {
        return rssiList;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }
}
