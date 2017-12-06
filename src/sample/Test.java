package sample;

import maps.ParseScene;

import java.io.File;

public class Test {

    public static void main(String[] args) {

        BeaconLoader myBeaconLoader = new BeaconLoader(new File("./src/files/beacons.csv"));


        if(myBeaconLoader.load()){
            ParseScene parseScene = new ParseScene();
            parseScene.parseObstacles();
            parseScene.parseBacons(myBeaconLoader.getBeacons());

            for(Beacon beacon : myBeaconLoader.getBeacons()) {
                System.out.println(beacon.getMacAdress());
                for(Integer rssi : beacon.getRssiList()) {
                    System.out.println(rssi);
                }
                System.out.println("("+beacon.getLayoutX()+","+beacon.getLayoutY()+")");
            }
        }
    }

}
