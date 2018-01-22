package world;

import javafx.scene.shape.Circle;

/**
 * Beacon is a class for representing beacons
 */
public class Beacon{
    private Double X;
    private Double Y;
    private String macAdress;
    private Integer timeStamp;

    /**
     * Constructor with only mac address of beacon
     * @param macAdress mac address of beacon
     */
    public Beacon(String macAdress) {
        this.macAdress = macAdress;
    }

    /**
     * Constructor with mac address and position of beacon
     * @param X horizontal position of beacon
     * @param Y vertical position of beacon
     * @param macAdress mac adress of beacon
     */
    public Beacon(String X, String Y, String macAdress){
        this.X = Double.parseDouble(X);
        this.Y = Double.parseDouble(Y);
        this.macAdress = macAdress;
    }

    /**
     * Gets position x of beacon
     */
    public Double getX() {
        return X;
    }

    /**
     * Gets position y of beacon
     */
    public Double getY() {
        return Y;
    }

    /**
     * Gets beacon's mac address
     */
    public String getMacAdress() {
        return macAdress;
    }

    /**
     * Gets time stamp of beacon
     */
    public Integer getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets time stamp of beacon
     * @param timeStamp time stamp of beacon (frequency of signals)
     */
    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }
}
