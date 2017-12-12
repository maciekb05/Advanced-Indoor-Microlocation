package beacons;

import world.Beacon;
import world.DataFromBeacon;
import world.Receiver;

import java.io.*;
import java.util.*;

public class BeaconLoader {
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
                    data.get(i).setMacAdress(macSplitted[i]);
                    if(!lineSplitted[i].equals("")) {
                        try{
                            data.get(i).getRSSI().add(Integer.parseInt(lineSplitted[i]));
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
