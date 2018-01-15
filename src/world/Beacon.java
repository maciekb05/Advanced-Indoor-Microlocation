package world;

/**
 * Beacon is a class for representing beacons
 */
public class Beacon {
    private Double X;
    private Double Y;
    private final String macAddress;

    /**
     * Constructor with only mac address of beacon
     * @param macAddress mac address of beacon
     */
    public Beacon(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * Constructor with mac address and position of beacon
     * @param X horizontal position of beacon
     * @param Y vertical position of beacon
     * @param macAddress mac address of beacon
     */
    public Beacon(String X, String Y, String macAddress){
        this.X = Double.parseDouble(X);
        this.Y = Double.parseDouble(Y);
        this.macAddress = macAddress;
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
    public String getMacAddress() {
        return macAddress;
    }

}
