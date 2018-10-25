import java.util.List;
import java.util.TreeMap;

/**
 count logged in users
 */
class Log {
    double login;
    double logout;

    public Log(double a, double b) {
        login = a;
        logout = b;
    }
}
public class WeightedSegments {

    public void printWeightedSegments(List<Log> logs) {
        //double stores start time. Integer stores end time
        TreeMap<Double, Integer> map = new TreeMap<>();
        map.put(0d, 0);
        for(Log log: logs) {
            double prev = map.floorKey(log.login);
            map.put(log.login, map.get(prev) + 1);  //initialize login
            Double next = map.higherKey(log.login);
            int count = map.get(prev);              //help reset count to before this user login
            while(next != null && next < log.logout) {
                count = map.get(next);
                map.put(next, count + 1);
                next = map.higherKey(next);
            }
            map.put(log.logout, map.getOrDefault(log.logout, count)); //log this person out
        }
    }


}
