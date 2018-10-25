/**
 Given a set of positive numbers,
 you can apply the xor operation on any two numbers that you already have to create new numbers.
 how many numbers at least that you'll need to create all of the n numbers.
 (
 the numbers don't have to be in the given set
 )

 test cases:
 [2, 4, 8, 10, 555, 568095, 22933939, 44758, 44250, 22933945, 559] = 8
 {2,4,8,10,555,568095,22933939,44758,555 ^ 44785,22933939 ^ 10, 4 ^ 555}

 [0,1,2] = 2

 [] = 0

 [232342434, 0] = 2
 */

import java.util.*;
public class ColorMix {
    public static void main(String[] args) {
        int[] nums = new int[] {232342434, 0};
        for(int n: nums) System.out.print(n+" ");
        System.out.println();
        System.out.println(colorMix(nums));
    }

    public static int colorMix(int[] nums) {
        if(nums.length <= 2) return nums.length;
        int count = 0;
        Set<Integer> set = new HashSet<Integer>();
        for(int n: nums) set.add(n);
        for(int i = 30; i >= 0; i--) {
            List<Integer> highestbit = new ArrayList<Integer>();
            for(int n: set) {
                if((1 & n >>> i) == 1) {
                    highestbit.add(n);
                }
            }
            int size = highestbit.size();
            if(size > 0) {
                count++;
                for(int j = 0; j < size; j++) {
                    set.remove(highestbit.get(j));
                    for(int k = j + 1; k < size; k++) {
                        set.add(highestbit.get(j) ^ highestbit.get(k));
                    }
                }
            }
        }
        return count;
    }
}
