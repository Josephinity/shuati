package Simple;
import java.util.*;
/*
# There's a room with a TV and people are coming in and out to watch it. The TV is on only when there's at least a person in the room.
# For each person that comes in, we record the start and end time. We want to know for how long the TV has been on. In other words:
# Given a list of arrays of time intervals, write a function that calculates the total amount of time covered by the intervals.
# For example:

# input = [(1,4), (2,3)]
# > 3
# input = [(4,6), (1,2)]
# > 3
# input = {{1,4}, {6,8}, {2,4}, {7,9}, {10, 15}}
# > 11
*/

public class MergeSegments {
    public static int mergeSegments(int[][] segments) {
        if(segments.length == 0) return 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int[] s: segments) {

            int start, end;
            Integer sKey = map.floorKey(s[0]);
            if(sKey == null || map.get(sKey) < s[0]) {
                start = s[0];
                end = s[1];
            } else {
                start = sKey;
                end = Math.max(s[1], map.get(sKey));
            }
            
            Integer next = map.higherKey(start);
            while(next != null && map.get(next) <= end) {
                end = Math.max(map.get(next), end);
                map.remove(next);
                next = map.higherKey(next);
            }
            map.put(start, end);
        }
        
        int sum = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            sum += entry.getValue() - entry.getKey();
        }
        return sum;
    }
}
