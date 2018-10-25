package Google;

/**
 create a class of integer collection,
 3 APIs:
 append(int x),
 get(int idx),
 add_to_all(int x)，
 in O(1) time。
 */

import java.util.List;
import java.util.ArrayList;

public class AddToAllCollection {

    List<Integer> collection = new ArrayList<>();
    List<Integer> divisors = new ArrayList<>();
    int factor = 1;
    int addition = 0;
    int set0 = -1;

    public void append(int x) {
        collection.add(x - addition);
        divisors.add(factor);
    }

    public int get(int index) {
        if(index >= collection.size()) throw new RuntimeException();
        if(divisors.get(index) <= set0) return addition;
        return collection.get(index) * (factor / divisors.get(index)) + addition;
    }

    public void add_to_all(int x) {
        addition += x;
    }

    public void multiply_to_all(int x) {
        if(x == 0) {
            addition = 0;
            factor = 1;
            set0 = collection.size() - 1;
        } else {
            addition *= x;
            factor *= x;
        }
    }

}
