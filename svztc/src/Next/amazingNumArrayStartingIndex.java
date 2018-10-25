package Next;
import java.util.*;
/**
 Define amazing number as: its value is less than or equal to its index. Given a circular array, find the starting position,
 such that the total number of amazing numbers in the array is maximized.
 Example 1: 0, 1, 2, 3
 Ouptut: 0. When starting point at position 0, all the elements in the array are equal to its index. So all the numbers are amazing number.
 Example 2: 1, 0, 0
 Output: 1. When starting point at position 1, the array becomes 0, 0, 1. All the elements are amazing number.
 If there are multiple positions, return the smallest one.

 O(n)
 since it is a circular array we need to find the max difference between index and value
 so the result should be the difference plus current index of the max difference
 */
public class amazingNumArrayStartingIndex {
    public int findAmazingNumberIndex(int[] a)
    {
        int maxDiff= 0, currDiff = 0, index = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] > i) {
                currDiff = a[i] - i;
            }
            if(maxDiff < currDiff) {
                maxDiff = currDiff;
                index = i;
            }
        }
        return maxDiff + index;
    }

    public int amazingStart(int[] a) {
        Map<Integer, Integer> sub = new HashMap<>();
        int count = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] >= 0 && a[i] < a.length) {
                if(a[i] <= i) {
                    count++;
                    sub.put(i - a[i] + 1, sub.getOrDefault(i - a[i] + 1, 0) - 1);
                } else {
                    int index = i + (a.length - a[i]) + 2;
                    sub.put(index, sub.getOrDefault(index, 0) - 1);
                }
                sub.put(i + 1, sub.getOrDefault(i + 1, 0) + 1); //when index
            }
        }
        int max = 0, start = 0;
        for(int i = 1; i < a.length; i++){
            count += sub.getOrDefault(i, 0);
            if(count > max) {
                max = count;
                start = i;
                System.out.println(count +"  start=" + i);
            }
        }
        return start;
    }
    // 0,0,1,2,3

}