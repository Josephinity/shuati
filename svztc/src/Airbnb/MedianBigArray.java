package Airbnb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 Find the median of a very big array of integer. Only iterator is available

 There are 3 common ways to find median of an array.
 1. Quick/Merge Sort
 2. Quick Select
 3. Counting Sort

 1 and 2 demands WRITE access to the arrays if the array is too big to fit in memory. However only the iterator (READ) is given.

 Since the numbers are integer, the range of possible numbers is [-min_int, max_int]. Create a counter array or map to store frequency of numbers in array. Then find median from counter array in O(N).


 More Optimal Solution:
 Still this method requires storing the full range of integers, which has high demand for memory.
 Another approach is distributed quick select - divide arrays into trunks that fits in memory. Find median of each trunk by in-memory sorting or quick select.
 Next round ignore all numbers less than minimum median or bigger than maximum median. Divide remaining elements into trunks that fits in memory.
 This way every round we'd be able to exclude one trunk of numbers and narrow down the search range, until eventually the range is small enough to fit-in memory.

 */
public class MedianBigArray {
    //code will take a long time to run since it is going through every integer value
    public static double getMedian(Iterator<Integer> iterator) {
        int size = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        while(iterator.hasNext()) {
            size++;
            int number = iterator.next();
            counter.put(number, counter.getOrDefault(number, 0) + 1);
        }
        int mid_idx1 = (size - 1) / 2, mid_idx2 = size / 2;
        int mid1 = Integer.MAX_VALUE, mid2 = Integer.MAX_VALUE;
        for(int value = Integer.MIN_VALUE; value < Integer.MAX_VALUE; value++) {
            if(counter.containsKey(value)) {
                size -= counter.get(value);
                if(size <= mid_idx1 && mid2 < Integer.MAX_VALUE) { //array has even size
                    return (mid2 + value) / 2.0;
                } else if(size <= mid_idx1) {       //array has odd size
                    return value;
                } else if(size == mid_idx2) {       //found the first middle number for array with even size
                    mid2 = value;
                }
            }
        }
        return (mid1 + mid2) / 2;
    }
}
