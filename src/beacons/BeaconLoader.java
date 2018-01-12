package beacons;

import world.Beacon;
import world.DataFromBeacon;
import world.Receiver;

import java.io.*;
import java.util.*;

/**
 * BeaconLoader class is to load beacon's data from csv file.
 */
public class BeaconLoader {
    /**
     * loadRSSI is loading beacon's data from csv file, setting mac adresses, timestamps, and RSSI's
     * @param beacons it is a list of beacons to add data
     * @param receiver it is a receiver to add beacons with data
     * @param filePath path to csv file with data from beacons
     */
    public void loadRSSI(LinkedList<Beacon> beacons, Receiver receiver, File filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader fileIn = new BufferedReader(fileReader);
            String line;

            //Setting mac Adresses
            line = fileIn.readLine();
            String[] macSplitted = line.split(",");

            //Adding TimeStamps
            line = fileIn.readLine();
            String lineSplitted[] = line.split(",");
            for(int i = 0; i<lineSplitted.length; ++i) {
                for(int j = 0; j<lineSplitted.length; ++j) {
                    if(beacons.get(i).getMacAdress().equals(macSplitted[j])) {
                        beacons.get(i).setTimeStamp(Integer.parseInt(lineSplitted[j]));
                    }
                }
            }

            //Adding RSSI's
            LinkedList<DataFromBeacon> data = new LinkedList<>();
            for(int i=0; i<lineSplitted.length;++i) {
                data.add(new DataFromBeacon());
            }
            while((line = fileIn.readLine())!=null) {
                lineSplitted = line.split(",");
                for(int i=0; i<lineSplitted.length;++i) {
                    if(!lineSplitted[i].equals("")) {
                        try{
                            data.get(i).getRSSI().add(Double.parseDouble(lineSplitted[i]));
                        } catch(NumberFormatException ex){
                            //Jesli trafimy tu to znaczy ze ktorys Beacon ma mniej RSSI (moze miec mniejszy TimeStamp)
                        }
                    }
                }
            }
            for(int i=0;i<lineSplitted.length;++i) {
                for(int j=0;j<lineSplitted.length;++j) {
                    if (beacons.get(i).getMacAdress().equals(macSplitted[j])) {
                        receiver.setDataFromBeacon(data);
                    }
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
