package String;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 Find first none repeat character in a string.

introduce to LinkedHashMap.
 By default order-by insertion sequence
 Can be set to order-by most recent edited - new LinkedHashMap<>(int initialCapacity, float loadFactor, boolean accessOrder)

 follow up: LRU/LFU cache
 reverse order access for linkedhashmap
 List<Integer> keys = new ArrayList<>(map.keySet())

 */

public class FindFirstNonRepeatChar {
    public Character firstNoneRepeat(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(char c: s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for(char c: map.keySet()) {
            if(map.get(c) == 1) {
                return c;
            }
        }
        return null;
    }
}
