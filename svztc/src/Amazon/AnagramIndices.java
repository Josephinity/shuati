package Amazon;
import java.util.*;
/**
 Find the indices of all anagrams of a given word in a another word.
 For example: Find the indices of all the anagrams of AB in ABCDBACDAB (Answer: 0, 4, 8)
 */
public class AnagramIndices {

    public static void main(String args[]) {
        System.out.println(getAnagrams("ABCDBACDAB","ACB"));
    }

    public static List<Integer> getAnagrams(String s, String word) {
        Map<Character, Integer> letters = new HashMap<>();
        int distinct_letters = 0;
        for(char c: word.toCharArray()) {
            if(!letters.containsKey(c)) distinct_letters++;
            letters.put(c, letters.getOrDefault(c, 0) + 1);
        }

        //search for anagrams with two pointers
        List<Integer> res = new ArrayList<>();
        int lo = 0, hi = 0;
        while(hi < s.length()) {
            if(!letters.containsKey(s.charAt(hi))) {
                while(lo < hi) {
                    char c = s.charAt(lo);
                    if(letters.get(c) == 0) distinct_letters++;
                    letters.put(c, letters.get(c) + 1);
                    lo++;
                }
                hi++;
                lo = hi;
            } else if(letters.get(s.charAt(hi)) == 0){
                while(s.charAt(lo) != s.charAt(hi)) {
                    char c = s.charAt(lo);
                    if(letters.get(c) == 0) distinct_letters++;
                    letters.put(c, letters.get(c) + 1);
                    lo++;
                }
                lo++;
            } else {
                char c = s.charAt(hi);
                letters.put(c, letters.get(c) - 1);
                if(letters.get(c) == 0) distinct_letters--;
                if(distinct_letters == 0) {
                    res.add(lo);
                }
                hi++;
            }
        }
        return res;
    }
}
