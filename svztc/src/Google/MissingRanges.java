package Google;

/**
 *
 * 11年exp interviewer
 Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
 For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
 */

import java.util.*;

public class MissingRanges {

    List<String> findMissingRanges(int[] nums, int lower, int upper) {

        List<String> list = new ArrayList<>();

        for(int i = 0; i < nums.length && nums[i] <= upper; i++) {

            if(nums[i] > lower) {

                if(nums[i] == lower + 1) {

                    list.add(String.valueOf(lower));

                } else {

                    list.add(lower+ "->" + (nums[i] - 1));

                }

                if(nums[i] == Integer.MAX_VALUE) return list;

                lower =  nums[i] + 1;

            }

        }

        if(lower == upper) {

            list.add(String.valueOf(lower));

        }

        if(lower < upper) {

            list.add(lower + "->" + upper);

        }

        return list;

    }

}
