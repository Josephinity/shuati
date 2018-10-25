package DSDesign;
import java.util.*;
/**
 create a map  storing pairs of integers (key, value) and define following member functions
 in O(1) runtime: void insert(key, value), void delete(key), int get(key), int getRandomKey().
 */



public class O1Map {

    class Wrapper {
        int index;
        int value;

        public Wrapper(int i, int v) {
            index = i;
            value = v;
        }
    }


    private Map<Integer, Wrapper> map = new HashMap<>();
    private List<Integer> keys = new ArrayList<>();

    public void insert(int key, int value) {
        if(!map.containsKey(key)) {
            keys.add(key);
            map.put(key, new Wrapper(keys.size() - 1, value));
        } else {
            map.get(key).value = value;
        }
    }

    public Integer delete(int key) {
        if(!map.containsKey(key)) {
            return null;
        } else {
            Wrapper w = map.remove(key);
            int last = keys.size() - 1;
            keys.set(w.index, last); //swap with last element and then remove last element
            keys.remove(last);
            return w.value;
        }
    }

    public Integer get(int key) {
        if(!map.containsKey(key)) {
            return null;
        } else {
            return map.get(key).value;
        }
    }

    public Integer getRandomKey() {
        if(map.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        return keys.get(rand.nextInt(keys.size()));
    }
}

