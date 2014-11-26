package trains;

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Trains {

    public static void main(String[] args) throws IOException {
        //System.out.println("Thanks for the name, " + userName);
        ArrayList<String> edgeList = new ArrayList<>();
        Scanner s = null;
        try {
            //by default, without useDelimiter, scanner read the input file sperated by white space.
            //With useDelimiter, we use regular expression as delimiter. \s* represents whitespaces. another \ escapes it.
            s = new Scanner(new BufferedReader(new FileReader("input.txt")));
            s.useDelimiter("\\s*,\\s*|Graph:\\s*"); //

            while (s.hasNext()) {
                //System.out.println(s.next());
                edgeList.add(s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        //Upon being constructed, the data class creates a DepStationsList
        //containing a stationsList contains all valid departure stations, which
        //contains the station name and all possible destination stations from this station.
        Data data = new Data(edgeList);

        System.out.println("Output #1: " + calcDist(data, "ABC"));//A-B-C
        System.out.println("Output #2: " + calcDist(data, "AD"));//A-D
        System.out.println("Output #3: " + calcDist(data, "ADC"));
        System.out.println("Output #4: " + calcDist(data, "AEBCD"));
        System.out.println("Output #5: " + calcDist(data, "AED"));
        System.out.println("=====I can't think of a fast way of doing "
                + "the rest of the problems without using a graph library====");

        //System.out.println("Output #6:" + data.numSanitizedRoutes_P6("C", "D", 1));
        //System.out.println("Output #7: " + data.numSanitizedRoutes_P7("C", "D", 1));
        //System.out.println("Output #8:");
        //System.out.println("Output #9:");
        //System.out.println("Output #10:");
        //System.out.println("=====Finished====");

    }



    //param: e.g. ADC (From A->B-C)

    public static String calcDist(Data data, String route) {
        if (route.length() < 2) {
            System.out.println("Error: the path has less than 2 chars. "
                    + "Should be at least 2 chars representing different "
                    + "starting and ending towns. ");
        }

        int[] distances = new int[route.length() - 1];//containing distances to add up.

        for (int i = 0; i < route.length() - 1; i++) {
            String station_1 = Character.toString(route.charAt(i));
            String station_2 = Character.toString(route.charAt(i + 1));
            int resultIndex = data.DepStationPresentOrNot(station_1);
            if (resultIndex == -1) {
                //System.out.println("NO SUCH ROUTE"); //that station doesn't have a way out to any other stations, or that stations is not mentioned in the input file.
                return ("NO SUCH ROUTE");
            } else {
                Integer dist = data.DepStationsList.get(resultIndex).map.get(station_2);
                if (dist == null) {
                    return ("NO SUCH ROUTE");
                } else {
                    distances[i] = dist;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < distances.length; i++) {
            sum = sum + distances[i];
        }

        return Integer.toString(sum);
    }


}
