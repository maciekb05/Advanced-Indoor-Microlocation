package sample;

import beacons.Beacon;
import javafx.scene.text.Text;

public class SimulationThread extends Thread {

    Text text;
    Beacon beacon;

    SimulationThread(Text text, Beacon beacon) {
        this.text = text;
        this.beacon = beacon;
        setDaemon(true);
    }

    public void run() {
        for(int i = 0; i<beacon.getRssiList().size();i++) {
            text.setText(beacon.getRssiList().get(i).toString());
            try{
                Thread.sleep(beacon.getTimeStamp());
            } catch (InterruptedException ex) {

            }
            i++;
        }
    }
}
