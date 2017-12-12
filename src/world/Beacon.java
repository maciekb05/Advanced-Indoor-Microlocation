package world;

public class Beacon {
    private Double X;
    private Double Y;
    private String macAdress;
    private Integer timeStamp;

    public Beacon(String macAdress) {
        this.macAdress = macAdress;
    }

    public Beacon(String X, String Y, String macAdress){
        this.X = Double.parseDouble(X);
        this.Y = Double.parseDouble(Y);
        this.macAdress = macAdress;
    }

    public Double getX() {
        return X;
    }

    public void setX(Double x) {
        X = x;
    }

    public Double getY() {
        return Y;
    }

    public void setY(Double y) {
        Y = y;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }
}
