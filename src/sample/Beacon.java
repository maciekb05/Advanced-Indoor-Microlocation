package sample;

import java.util.LinkedList;

public class Beacon {
    private Integer layoutX;
    private Integer layoutY;
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

    public Integer getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(Integer layoutX) {
        this.layoutX = layoutX;
    }

    public Integer getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(Integer layoutY) {
        this.layoutY = layoutY;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setRssiList(LinkedList<Integer> rssiList) {
        this.rssiList = rssiList;
    }

    public LinkedList<Integer> getRssiList() {
        return rssiList;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }
}
