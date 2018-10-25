package Twitter;

/**
 Count number of each character in a string and print out the counts from highest to lowest.
 */
import java.util.*;

public class BucketSort {

    public void sortOccurrences(String s) {
        int n = s.length();
        Map<Character, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for(char c: s.toCharArray()) {      //count occurrences of chars
            int frequency = freq.getOrDefault(c, 0) + 1;
            freq.put(c, frequency);
            if(frequency > maxFreq) maxFreq = frequency;
        }

        List<Character>[] buckets = new List[maxFreq + 1];
        for(Map.Entry entry: freq.entrySet()) {   //create buckets on occurrences of chars
            int i = (int)entry.getValue();
            if(buckets[i] == null) {
                buckets[i] = new LinkedList<>();
            }
            buckets[i].add((char)entry.getKey());
        }

        for(int k = maxFreq; k > 0; k--) { //print in descending
            if(buckets[k] != null) {
                for(char c: buckets[k]) {
                    System.out.println(c + ":" + k);
                }
            }
        }
    }
}
