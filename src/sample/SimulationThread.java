package sample;

import world.*;
import javafx.application.Platform;
import javafx.scene.text.Text;

public class SimulationThread extends Thread {

    Text text;
    Beacon beacon;
    DataFromBeacon data;
    Integer timeStamp;
    Integer numberOfRSSI;

    SimulationThread(Text text, Beacon beacon, DataFromBeacon data) {
        this.text = text;
        this.beacon = beacon;
        this.data = data;
        setDaemon(true);
    }

    public void run() {
        timeStamp = beacon.getTimeStamp();
        numberOfRSSI = data.getRSSI().size();
        int i = 0;
        while(true) {
            final int finalI = i++;

            Platform.runLater ( () -> text.setText(data.getRSSI().get(finalI).toString()));
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
