package Bloomberg;
import java.util.*;
/**
 1. Three largest product in an array
 一個int array裡面 找出三個數相乘最大的數是多少 有正有負但沒有零.
 一開始這題就掛了 只想得出N^3 後來歐洲小哥提示說可以先sort array 再從array最前面跟最後面撈數字 depends on 有多少負數存在array裡面


Pick three numbers a, b, c from an array of integers to get the maximum product a * b * c.
 Began with the O(N^3) solution. Then the interviewer give clues on optimization by sorting the array.

 */
public class ThreeLargestProduct {

    public int largestProduct3(int[] array) {

        Arrays.sort(array);

        int n = array.length;
        int maxProduct;

        //case> pick the last 3 numbers(when the array has only negative, or only positive numbers)
        maxProduct = array[n - 1] * array[n - 2] * array[n - 3];

        //case> pick 2 numbers from the left end and 1 number from the right end
        maxProduct = Math.max(maxProduct, array[0] * array[1] * array[n - 1]);

        return maxProduct;
    }

}
