package sample;

import java.io.*;
import java.util.*;

public class BeaconLoader {
    private File filePath;
    private LinkedList<Beacon> beacons;

    public BeaconLoader(File filePath) {
        this.filePath = filePath;
    }

    public boolean load() {
        boolean done;
        System.out.println("Loader");
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader fileIn = new BufferedReader(fileReader);
            String line;

            //Adding mac Adresses
            line = fileIn.readLine();

            String[] lineSplitted = line.split(",");
            beacons = new LinkedList<>();
            for(int i = 0; i<lineSplitted.length; ++i) {
                beacons.add(new Beacon(lineSplitted[i]));
            }
            //Adding TimeStamps
            line = fileIn.readLine();
            lineSplitted = line.split(",");
            for(int i = 0; i<lineSplitted.length; ++i) {
                beacons.get(i).setTimeStamp(Integer.parseInt(lineSplitted[i]));
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
                            System.out.println(ex.getMessage());
                        }

                    }
                }
            }
            for(int i=0;i<lineSplitted.length;++i) {
                beacons.get(i).setRssiList(listOfRssi.get(i));
            }

        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public LinkedList<Beacon> getBeacons() {
        return beacons;
    }
}
