package sample;

import java.io.*;
import java.util.*;

public class BeaconLoader {
    public static void loadRSSI(LinkedList<Beacon> beacons, File filePath) {
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
            LinkedList<LinkedList<Integer>> listOfRssi = new LinkedList<>();
            for(int i=0; i<lineSplitted.length;++i) {
                listOfRssi.add(new LinkedList<>());
            }
            while((line = fileIn.readLine())!=null) {
                lineSplitted = line.split(",");
                for(int i=0; i<lineSplitted.length;++i) {
                    if(lineSplitted[i]!="") {
                        try{
                            listOfRssi.get(i).add(Integer.parseInt(lineSplitted[i]));
                        } catch(NumberFormatException ex){
                            //Jesli trafimy tu to znaczy ze ktorys Beacon ma mniej RSSI (moze miec mniejszy TimeStamp)
                        }
                    }
                }
            }
            for(int i=0;i<lineSplitted.length;++i) {
                for(int j=0;j<lineSplitted.length;++j) {
                    if (beacons.get(i).getMacAdress().equals(macSplitted[j])) {
                        beacons.get(i).setRssiList(listOfRssi.get(j));
                    }
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
