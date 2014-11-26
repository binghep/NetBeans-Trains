package trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class Data {

    //a list of stations containing station name and a map of 
    //all the data of destination stations reachable one stop 
    //away from that station:
    ArrayList<Station> DepStationsList = new ArrayList<>();
    ArrayList<String> AllStationNames = new ArrayList<>();
    //Trains.main() build this edgeList from the input text file:
    ArrayList<String> edgeList = new ArrayList<>();

    public Data() {

    }

    public Integer StationPresentOrNot(String stationName) {
        for (int i = 0; i < AllStationNames.size(); i++) {
            if (AllStationNames.get(i).equals(stationName)) {
                return i;
            }
        }
        return -1;
    }

    //returns -1 if not present. otherwise, return its index in stationsList
    public Integer DepStationPresentOrNot(String stationName) {
//        for (Station s : stationsList) {
//            if (s.name.equals(stationName)) {
//                return 
//            }
//        }
        for (int i = 0; i < DepStationsList.size(); i++) {
            if (DepStationsList.get(i).name.equals(stationName)) {
                //do something
                return i;
            }
        }
        return -1;
    }

    public Data(ArrayList<String> edgeList) {
        this.edgeList = edgeList;
        Build_DepStationsList();
        Build_AllStationsNames();
        System.out.println("stationList Size: " + DepStationsList.size());
    }

    private void Build_AllStationsNames() {
        Integer numEdges = this.edgeList.size();
        for (int i = 0; i < numEdges; i++) {
            String edge = this.edgeList.get(i);
            //=====================================

            String station_1 = Character.toString(edge.charAt(0));
            int resultIndex = StationPresentOrNot(station_1);
            if (resultIndex == -1) {
                //station not in the allstationNames yet:
                AllStationNames.add(station_1);
            } else {
                //already in the list. do nothing
            }
            //=====================================
            String station_2 = Character.toString(edge.charAt(1));
            resultIndex = StationPresentOrNot(station_2);
            if (resultIndex == -1) {
                //station not in the allstationNames yet:
                AllStationNames.add(station_2);
            } else {
                //already in the list. do nothing
            }
        }
    }

    private void Build_DepStationsList() {
        for (String edge : this.edgeList) {
            //examineEdge(edge);
            String stationName = Character.toString(edge.charAt(0));
            Integer resultIndex = DepStationPresentOrNot(stationName);
            if (resultIndex != -1) {
                //present in stationsList: add this to the map.
                DepStationsList.get(resultIndex).AddDirectDest(Character.toString(edge.charAt(1)), Integer.parseInt(Character.toString(edge.charAt(2))));
            } else {
                //not exist in stationsList: 
                Station toAdd = new Station(stationName);
                toAdd.AddDirectDest(Character.toString(edge.charAt(1)), Integer.parseInt(Character.toString(edge.charAt(2))));
                DepStationsList.add(toAdd);
            }
        }
    }

    //get the number of valid routes from the arrayList:
    //if one String element in routes contains String destStation at some index 
    //larger than 0 and not greater than maxIndex, then count it. 
    public Integer numSanitizedRoutes(ArrayList<String> routes, String destStation, Integer maxIndex) {
        int num = 0;
        for (int i = 0; i < routes.size(); i++) {
            for (int j = 1; j <= maxIndex; j++) {
                String jthCharacter = Character.toString(routes.get(i).charAt(j));
                if (jthCharacter.equals(destStation)) {
                    num++;
                    break;
                }
            }
        }
        return num;
    }

    public Integer numSanitizedRoutes_P7(String depStation, String destStation, Integer requiredStops) {
        ArrayList<String> routes = getAllRoutes(depStation, requiredStops);
        Integer num = 0;
        for (int i = 0; i < routes.size(); i++) {
            String CharacterAtIndex = Character.toString(routes.get(i).charAt(requiredStops));
            if (CharacterAtIndex.equals(destStation)) {
                num++;
            }
        }
        return num;
    }
    //an expansion on P7
    public Integer numSanitizedRoutes_P6(String depStation, String destStation, Integer maxStops) {
        Integer num=0;
        for (int i=1;i<=maxStops;i++){
            num+=numSanitizedRoutes_P7(depStation, destStation,i);
        }
        return num;
    }
    //Used as helper function in problems starting from P6:
    public ArrayList<String> getAllRoutes(String depStation, Integer numReqStops) {
        ArrayList<String> routes = new ArrayList<>();
        int indexOfDepStation = DepStationPresentOrNot(depStation);
        if (indexOfDepStation != -1) {
            //String[] map_keys_array=data.DepStationsList.get(indexOfDepStation).get_map_keys_array();
            Map<String, Integer> map = DepStationsList.get(indexOfDepStation).map;

            if (routes.isEmpty()) {
                for (Integer i = 0; i < map.size(); i++) {
                    routes.add(depStation); //set the amount of all possible routes. add the depStation as the first char if the route is empty
                }
            }
            //iterate through HashMap:
            Integer i = 0;

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
                routes.set(i, routes.get(i) + entry.getKey());
                ++i;
            }
            return routes;
        } else {
            //no such route:
            return null;
        }
    }
    //find all available destination stations one stop away from station s:
//    public String findAllAvailDest(String s) {
//    }

    public static void examineEdge(String threeChars) {
        System.out.println("-----start examining " + threeChars);
        System.out.println("item length: " + threeChars.length());
        for (int i = 0; i < threeChars.length(); i++) {
            char aChar = threeChars.charAt(i);
            //System.out.println(aChar.toString());
            switch (aChar) {
                case 'A':
                    System.out.println("A");
                    break;
                case 'B':
                    System.out.println("B");
                    break;
                case 'C':
                    System.out.println("C");
                    break;
                case 'D':
                    System.out.println("D");
                    break;
                case 'E':
                    System.out.println("E");
                    break;
                case ' ':
                    System.out.println(" ");
                    break;
                case '5':
                    System.out.println("5");
                    break;
                case '7':
                    System.out.println("7");
                    break;
                default:
                    System.out.println("_");
                    break;
            }
        }
        System.out.println("-----end examining-----");
    }

    //check if we can go from station A to B, including indirect paths:
//    public boolean checkIfPathExists(Station A, Station B){
//        
//    }
//    public int calcPathDistance(String path){
//        
//    }
}
