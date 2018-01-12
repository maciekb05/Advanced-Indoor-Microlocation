package sample;

import world.*;
import javafx.application.Platform;
import javafx.scene.text.Text;

/**
 * SimulationThread is a thread of simulation of changing RSSI of one beacon.
 */
public class SimulationThread extends Thread{

    private Text text;
    private Beacon beacon;
    private DataFromBeacon data;

    /**
     * Constructor with parameters, and setting the thread to deamon
     * @param text text field to represent RSSI changes
     * @param beacon beacon
     * @param data data from beacon
     */
    SimulationThread(Text text, Beacon beacon, DataFromBeacon data) {
        this.text = text;
        this.beacon = beacon;
        this.data = data;
        setDaemon(true);
    }

    /**
     * method of simulation changes of RSSI
     */
    public void run() {
        Integer timeStamp = beacon.getTimeStamp();

        for(Double rssi : data.getRSSI()) {

            Platform.runLater ( () -> text.setText(rssi.toString()) );
            try{
                Thread.sleep(timeStamp);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted SimulationThread");
            }
        }
    }

}
