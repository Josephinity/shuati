package Google;

/**
    Phone Interview May

Q1: FormatInt(1230000) = 123M, P, T —— negative number, overflow



 Q2: IsAnagram
 一道纠错题:目的是reverse一个vector

 */

import java.util.*;
public class Corrections {

    //should swap half of the array instead of the whole

    int[] reverse(int[] values, int n) {

        //for (int i = 0; i < n; i++)
            //swap(values, values[n - i - 1]);
        return values;
    }

    int main(String[] args) {
        int N = 4;
        int[] values = new int[]{1,20,53,42};
        int[] res = reverse(values, N);
        for (int i = 0; i < N; i++) {
            System.out.println(values + "->" + res);
        }
        return 0;
    }
}
