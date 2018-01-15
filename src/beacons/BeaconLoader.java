package beacons;

import world.Beacon;
import world.DataFromBeacon;
import world.Receiver;

import java.io.*;
import java.util.*;

/**
 * BeaconLoader class is to load beacon's data from csv files.
 */
public class BeaconLoader {
    /**
     * Loads beacon's data from csv files, setting mac addresses, timestamps, and RSSI's
     * @param beacons it is a list of beacons to add data
     * @param receiver it is a receiver to add beacons with data
     * @param filePath path to csv file with data from beacons
     */
    public void loadRSSI(LinkedList<Beacon> beacons, Receiver receiver, File filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader fileIn = new BufferedReader(fileReader);
            String line;

            //Current beacon
            Beacon currentBeacon = new Beacon("0");

            //Setting current Beacon
            line = fileIn.readLine();
            String[] macSplitted = line.split(",");
            for(Beacon beacon : beacons){
                if(beacon.getMacAddress().equals(macSplitted[0])){
                    currentBeacon = beacon;
                }
            }

            //Adding Data from file
            DataFromBeacon currentData = new DataFromBeacon();
            currentData.setMacAddress(currentBeacon.getMacAddress());
            String[] lineSplitted;
            while((line = fileIn.readLine())!=null) {
                lineSplitted = line.split(",");
                currentData.getTimeStamps().add(Long.parseLong(lineSplitted[0]));
                currentData.getRSSI().add(Double.parseDouble(lineSplitted[1]));
            }
            receiver.getDataFromBeacon().add(currentData);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
