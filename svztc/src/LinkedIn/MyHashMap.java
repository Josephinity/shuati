package LinkedIn;

/**
 Design black list. Implement hash map
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

class MyMapEntry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    MyMapEntry<K,V> next;

    public MyMapEntry(K key, V value) {
        this.key = key;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    public V setValue(V val) {
        V oldValue = value;
        value = val;
        return oldValue;
    }
}
public class MyHashMap<K, V> {
    int capacity; //extensible
    int load;
    List<List<MyMapEntry<K,V>>> map;
    int hash;

    public MyHashMap() {
        capacity = 128;
        map = new ArrayList<>(128);
        for(int i = 0; i < 128; i++) {
            map.add(new LinkedList<MyMapEntry<K, V>>());
        }
    }

    private MyMapEntry getEntry(K key, int index) {
        for(MyMapEntry<K,V> entry: map.get(index)) {
            if(key.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = getHashCode(key);
        return getEntry(key, index) != null;
    }

    public V get(K key) {
        int index = getHashCode(key);
        MyMapEntry<K,V> entry = getEntry(key, index);
        if(entry == null) {
            //throw exception or
            return null;
        } else {
            return entry.getValue();
        }
    }
    public void put(K key, V value) {
        int index = getHashCode(key);
        MyMapEntry<K,V> entry = getEntry(key, index);
        if(entry == null) {
            entry = new MyMapEntry<K, V>(key, value);
            map.get(getHashCode(key)).add(entry);
        } else {
            entry.setValue(value);
        }
        load++;
        if(load > .75 * capacity * 5) expand(); //allow on avg each linked list hold 5 elements. If map loads too much, extend the size
    }
    public void delete(K key) {
        int index = getHashCode(key);
        MyMapEntry<K,V> entry = getEntry(key, index);
        if(entry == null) {
            //exception or
        } else {
            map.get(index).remove(entry);
        }
    }

    private void expand() { //double map size
        capacity *= 2;
        List<List<MyMapEntry<K,V>>> newMap = new ArrayList<>();
        for(int i = 0; i < capacity; i++) {
            map.add(new LinkedList<MyMapEntry<K, V>>());
        }
        for(List<MyMapEntry<K,V>> list: map) {
            for(MyMapEntry<K,V> entry: list) {
                this.put(entry.key, entry.value);
            }
        }
        map = newMap;
    }

    private int getHashCode(K key) {
        //int code = myHash(key); //your own hash function here, for example ((int)key & Integer.MAX_VALUE)
        int code = 0;
        return code % capacity;
    }

}
