package Google;

/**
 Given two lowercase strings, S1 and S2, sort S1 in same order as S2.
 If a character in S1 doesn't exist in S2, put them at the end. If S1 is "program" and S2 is "grapo", then return "grrapom".
 */
import java.util.Map;
import java.util.HashMap;
public class SortStringByOtherString {

    public String countingSort(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c: s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        StringBuilder overlaps = new StringBuilder();
        StringBuilder nonoverlaps = new StringBuilder();
        for(char c: s2.toCharArray()) {
            if(map.containsKey(c)) {
                int count = map.get(c);
                while(count > 0) {
                    overlaps.append(c);
                    count--;
                }
                map.put(c, 0);
            } else {
                nonoverlaps.append(c);
            }
        }
        overlaps.append(nonoverlaps);
        return overlaps.toString();
    }
}
