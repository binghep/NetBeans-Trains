
package trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Station {
    public String name;
    //available destination stations 1 stop far from this station
    Map<String, Integer> map = new HashMap<>(); 
    Set<String> map_keys=null;//use this as a medium to combine the keys in hashmap in an array

    public Station(){
        name=null;
    }
    public Station(String name){
        this.name=name;
    }
    public void AddDirectDest(String destStationName, Integer dist){
        map.put(destStationName, dist);
                
//                Integer value = map.get(key);
//            if (value != null) {
//                
//            } else {
//                // No such key
//                stationsList.add(new Station(key));
//            }
//        });
    }
 /* not useful anymore since we find another way of iterate through map key value paires
    public String[] get_map_keys_array(){
        map_keys=map.keySet();
        String[] map_keys_array = map_keys.toArray(new String[map_keys.size()]);
        return map_keys_array;
    }
*/
}
