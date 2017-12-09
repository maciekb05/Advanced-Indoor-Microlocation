package beacons;

import java.util.LinkedList;

public class Beacon {
    private Double layoutX;
    private Double layoutY;
    private String macAdress;
    private Integer timeStamp;
    private LinkedList<Integer> rssiList;

    public Beacon(String macAdress){
        this.macAdress = macAdress;
    }

    Beacon(String macAdress, Integer timeStamp, LinkedList<Integer> rssiList){
        this.macAdress = macAdress;
        this.timeStamp = timeStamp;
        this.rssiList = rssiList;
    }

    Beacon(String layoutX, String layoutY) {
        this.layoutX = Double.parseDouble(layoutX);
        this.layoutY = Double.parseDouble(layoutY);
    }

    public Beacon(String layoutX, String layoutY, String macAdress){
        this.layoutX = Double.parseDouble(layoutX);
        this.layoutY = Double.parseDouble(layoutY);
        this.macAdress = macAdress;
    }

    public Double getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(Double layoutX) {
        this.layoutX = layoutX;
    }

    public Double getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(Double layoutY) {
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
