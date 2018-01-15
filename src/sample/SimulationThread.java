package sample;

import world.*;
import javafx.application.Platform;
import javafx.scene.text.Text;

import java.util.LinkedList;

/**
 * SimulationThread is a thread of simulation of changing RSSI of every beacon.
 */
public class SimulationThread extends Thread{

    private final World world;
    private final LinkedList<Text> labelRSSI;

    /**
     * Constructor with parameters World and LinkedList<Text>
     * @param world world
     * @param labelRSSI list of labels with RSSI to show how RSSI is changing
     */
    SimulationThread(World world, LinkedList<Text> labelRSSI) {
        this.world = world;
        this.labelRSSI = labelRSSI;
        setDaemon(true);
    }

    /**
     * Method of simulation changes of RSSI
     */
    public void run() {
        Long minimalFirstTimeStamp = world.getReceiver().getDataFromBeacon().getFirst().getTimeStamps().getFirst();
        Long maximalLastTimeStamp = world.getReceiver().getDataFromBeacon().getFirst().getTimeStamps().getLast();
        for(DataFromBeacon dataFromBeacon : world.getReceiver().getDataFromBeacon()){
            if(dataFromBeacon.getTimeStamps().getFirst()<minimalFirstTimeStamp){
                minimalFirstTimeStamp = dataFromBeacon.getTimeStamps().getFirst();
            }
            if(dataFromBeacon.getTimeStamps().getLast()>maximalLastTimeStamp){
                maximalLastTimeStamp = dataFromBeacon.getTimeStamps().getLast();
            }
        }
        LinkedList<Integer> currentTimeStamp = new LinkedList<>();
        for(int i=0; i<world.getReceiver().getDataFromBeacon().size(); i++){
            currentTimeStamp.add(0);
        }
        LinkedList<DataFromBeacon> dataList = world.getReceiver().getDataFromBeacon();
        for(Long currentTime = minimalFirstTimeStamp; currentTime <= maximalLastTimeStamp; currentTime++){
            try {
                Thread.sleep(0,100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int currentDataNumber=0; currentDataNumber<dataList.size(); currentDataNumber++) {
                if(currentTimeStamp.get(currentDataNumber)<dataList.get(currentDataNumber).getRSSI().size()-1){
                    if(dataList.get(currentDataNumber).getTimeStamps().get(currentTimeStamp.get(currentDataNumber)).equals(currentTime)){
                        final Integer currentFinalDataNumber = currentDataNumber;

                        Platform.runLater ( () -> labelRSSI.get(currentFinalDataNumber).setText(dataList.get(currentFinalDataNumber).getRSSI().get(currentTimeStamp.get(currentFinalDataNumber)).toString()) );
                        currentTimeStamp.set(currentDataNumber,currentTimeStamp.get(currentDataNumber)+1);
                    }
                }
            }
        }
    }
}
