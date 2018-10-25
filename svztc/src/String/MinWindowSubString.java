package String;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
/**
 Given a string A and B, find the smallest substring of A that contains all the characters from B.
 (implement solution in O(n), keep in mind chars in B can repeat)

 //String S = "ADOBECODEBANC";
 //String T = "ABC";
 */
public class MinWindowSubString {

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c: t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int counter = map.size(), left = 0, right = 0, len = s.length() + 1, start = 0;
        // count of characters yet to be found in the window : counter;
        // two pointers : left/right;  length of current min window : len; first index of current min window : start;
        while(right < s.length()){
            char r = s.charAt(right);
            if(map.containsKey(r)) {
                map.put(r, map.get(r) - 1);
                if(map.get(r) == 0) {
                    counter--;
                }
                while(counter == 0) {   //valid substring
                    if(right - left < len) {
                        len = right - left;
                        start = left;
                    }

                    char l = s.charAt(left);
                    if(map.containsKey(l)) {
                        if(map.get(l) == 0) counter++;  // invalidate substring
                        map.put(l, map.get(l) + 1);
                    }
                    left++;
                }
            }
            right++;
        }
        return len > s.length() ? "":s.substring(start, start + len + 1);
    }
}
