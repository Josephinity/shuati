package FB;

import java.util.HashMap;
import java.util.Map;

/**
 finding the smallest continuous pangram in a sequence of characters given a specific dictionary of all possible letters to use.
 */
public class MinPangram {

    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('B', 1);
        map.put('C', 1);
        map.put('A', 2);
        System.out.println(MinPangram.shortestPangram("SBCFDAAAADDFBC",map));

    }
    static String shortestPangram(String s, Map<Character, Integer> dict) {
        if(dict.isEmpty()) return "";
        Map<Character, Integer> letters = new HashMap<>();
        int size = dict.size();
        String minPangram = null;
        int l = 0, r = 0;
        while(r < s.length()) {
            char c = s.charAt(r);
            if(dict.containsKey(c)) {
                letters.put(c, letters.getOrDefault(c, 0) + 1);
                if(letters.get(c) == dict.get(c)) {
                    size--;
                    if(size == 0) {
                        while(size == 0 || !dict.containsKey(s.charAt(l))) {
                            if (size == 0 && (minPangram == null || r - l + 1 < minPangram.length())) {
                                minPangram = s.substring(l, r + 1);
                            }
                            char discard = s.charAt(l);
                            if(dict.containsKey(discard)) {
                                letters.put(discard, letters.get(discard) - 1);
                                if(letters.get(discard) < dict.get(discard)) {
                                    size++;
                                }
                            }
                            l++;
                        }
                    }
                }
            }
            r++;
        }
        return minPangram;
    }
}
