package DSDesign;
import java.util.*;
/**
 LRU or LFU using LinkedHashMap
 */


public class LRUCache {
    int size;
    LinkedHashMap<Integer, Integer> map;

    public LRUCache(int capacity) { //capacity must be positive
        size = capacity;
        map = new LinkedHashMap<Integer, Integer>(size + 2, 1, true);
    }

    public int get(int key) {
        if(map.containsKey(key)) return map.get(key);
        return -1;
    }

    public void set(int key, int value) {
        map.put(key, value);
        if(map.size() > size) {
            Iterator<Integer> iter = map.keySet().iterator();
            map.remove(iter.next());
        }
    }

}
