package DSDesign;
import java.util.*;


class Wrapper {
    int freq = 1;
    int value;
    public Wrapper(int val) {
        value = val;
    }
}

public class LFUCache {

    int size;
    Map<Integer, Wrapper> map;              //stores key: <value, frequency of key>
    Map<Integer, Set<Integer>> frequency;   //stores sets of keys grouped by their frequencies
    int minFreq = 1;                        //keeps track of current least frequent element in map

    public LFUCache(int capacity) {
        size = capacity;
        map = new HashMap<>();
        frequency = new HashMap<>();
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            Wrapper w = map.get(key);
            //increment minimum frequency by 1 if no key is called less than minFreq times.
            if(minFreq == w.freq && frequency.get(w.freq).size() == 1) minFreq++;
            w.freq++;
            setFrequencyForKey(w.freq, key);    //increment frequency for key
            return w.value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        if(size == 0) return;

        if(map.containsKey(key)) {
            map.get(key).value = value;
            get(key);
        } else {
            Wrapper w = new Wrapper(value);
            if(map.size() == size) {
                int lrkey = frequency.get(minFreq).iterator().next();   //get the least frequent and recent key
                frequency.get(minFreq).remove(lrkey);                   //remove least frequent and recent key
                map.remove(lrkey);
            }
            minFreq = 1;                                                //reset minimum frequency to 1 when a new key is added
            map.put(key, w);
            setFrequencyForKey(w.freq, key);
        }
    }

    private void setFrequencyForKey(int freq, int key) {    //increment frequency by 1 for 'key'
        if(!frequency.containsKey(freq)) {
            frequency.put(freq, new LinkedHashSet<>());
        }
        if(freq != 1 && frequency.get(freq - 1).contains(key)) {
            frequency.get(freq - 1).remove(key);
        }
        frequency.get(freq).add(key);
    }
}

