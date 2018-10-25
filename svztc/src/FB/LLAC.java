package FB;
import java.util.Map;
import java.util.HashMap;
/**
 finding the length of longest arithmetic progression in array, DP.

 For example, in the array {1, 6, 3, 5, 9, 7}, the longest arithmetic sequence is 1, 3, 5, and 7
 */
public class LLAC {

    public static void main(String args[]) {

        int[] seq = llac(new int[]{1, 6, 3, 5, 9, 7, 9});
        for(int i = 0; i < seq.length; i++) {
            System.out.print(seq[i] + " ");
        }

    }


    public static int[] llac(int[] array) {

        if(array.length == 0) return new int[]{};

        int maxLen = 1, //len of longest sequence
            end = array[0], //end number of sequence
            step = 0;

        Map<Integer,Map<Integer, Integer>> index_to_llac = new HashMap<>(); //Map<currentIndex, Map<difference, length>>

        for(int current = 1; current < array.length; current++) {

            index_to_llac.put(current, new HashMap<>());

            for(int prev = 0; prev < current; prev++) {

                int diff = array[current] - array[prev];

                int length = 2;

                if(index_to_llac.containsKey(prev) && index_to_llac.get(prev).containsKey(diff)) {

                    length = index_to_llac.get(prev).get(diff) + 1;

                }

                Map<Integer, Integer> diff_to_llac = index_to_llac.get(current);

                //if this is a arithmetic progression with [diff] exists before current, add 1 to the length to include current in the progression
                //otherwise, [prev, current] forms a progression of size 2
                diff_to_llac.put(diff, length);

                if(maxLen < length) {
                    maxLen = length;
                    end = array[current];
                    step = diff;
                }

            }

        }

        int[] sequence = new int[maxLen];
        for(int i = maxLen - 1; i >= 0; i--) {
            sequence[i] = end;
            end -= step;
        }
        return sequence;

    }

}
