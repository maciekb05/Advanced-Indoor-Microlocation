package sample;

import beacons.Beacon;
import javafx.application.Platform;
import javafx.scene.text.Text;

public class SimulationThread extends Thread {

    Text text;
    Beacon beacon;
    Integer timeStamp;
    Integer numberOfRSSI;

    SimulationThread(Text text, Beacon beacon) {
        this.text = text;
        this.beacon = beacon;
        setDaemon(true);
    }

    public void run() {
        timeStamp = beacon.getTimeStamp();
        numberOfRSSI = beacon.getRssiList().size();
        int i = 0;
        while(true) {
            final int finalI = i++;

            Platform.runLater ( () -> text.setText(beacon.getRssiList().get(finalI).toString()));
            try{
                Thread.sleep(timeStamp);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted SimulationThread");
            }
            if(finalI == numberOfRSSI - 1 ){
                break;
            }
        }
    }
}
