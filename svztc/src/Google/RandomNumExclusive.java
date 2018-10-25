package Google;
import java.util.Set;
import java.util.Random;
/**
generate a random number from [0,n) but exclude the numbers in exclude list
 such that the numbers remaining are selected in uniform distribution
 */
public class RandomNumExclusive {
    //Sol1: O(n) time, O(1) extra memory solution, suitable if the function is rarely called on the data set
    public int random(int n, Set<Integer> ex) {
        int idx = new Random().nextInt(n - ex.size());

        for(int num = 0; num < n; num++) {
            if(!ex.contains(num)) {
                idx--;
            }
            if(idx == -1) {
                return num;
            }
        }
        return -1; //no number is available for selection (n is 0 or every number in range is excluded
    }

    //Sol2: O(n) extra memory and O(n) time at initialization
    //create a list of numbers in [0,n) excluding the numbers in excluded set
    //O(1) time on successive calls on the same data set
}
