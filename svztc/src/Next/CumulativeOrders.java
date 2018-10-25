package Next;
import java.util.*;
/**
 * Created by xiaobaby on 11/27/16.
 */
public class CumulativeOrders {

    /*
       @real-time input : {time, orders}
       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       int[][] a = Stream.get(timestamp.getTime());
     */

    TreeMap<Integer, Integer> map;

    public CumulativeOrders() {
//        map = new TreeMap<>(new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return (int)o1 - (int)o2;
//            }
//        });
        map = new TreeMap<>((o1, o2) -> o1 - o2); //quotient 商
    }

    public void update(Stream stream) {
        int[][] data = stream.get();

        int cumulative = 0;
        if(!map.isEmpty()) cumulative = map.lastKey();

        for(int[] d: data) {
            cumulative += d[1];
            map.put(d[0], cumulative);
        }
    }

    public int getOrderCountInRange(int start, int end) {
        int l = map.floorKey(start), r = map.floorKey(end);
        return r - l;
    }

class Stream {
    int[][] a;
    public int[][] get() {
        return a;
    }
}
}
