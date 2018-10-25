package DP;
import java.util.Set;
import java.util.HashSet;
/**
 Given three positive integers, print all sums from the three numbers under 1000 in order. Each number can be used repeatedly.
 */
public class PrintSum3Nums {
    public void printSum(int a, int b, int c) {
        Set<Integer> set = new HashSet<>();
        set.add(0);

        for(int n = 1; n < 1000; n++) {
            if(set.contains(n - a) || set.contains(n - b) || set.contains(n - c)) {
                set.add(n);
                System.out.print(n + " ");
            }
        }
    }
}
