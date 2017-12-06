package sample;

import java.io.File;

public class BeaconLoaderTest {

    public static void main(String[] args) {

        BeaconLoader myBeaconLoader = new BeaconLoader(new File("./src/files/beacons.csv"));

        if(myBeaconLoader.load()){

            for(Beacon beacon : myBeaconLoader.getBeacons()) {

                System.out.println(beacon.getMacAdress());

                for(Integer rssi : beacon.getRssiList()) {
                    System.out.println(rssi);
                }

            }

        }

    }

}
