package Google;

/**
 给一个公式2^i*5^j
 给一个上限N，求得所有可能的数(i >= 0, j >= 0) 并且increasing order输出
 Print all numbers satisfying the expression 2^i * 5^j (where i, j are integers i >= 0 and j >= 0) in increasing order up to a given bound N.
 2^i stands for power(2, i).

 */
import java.util.HashSet;
import java.util.Set;
public class ExpOf5And2 {

    public static void main(String[] args) {
        ExpOf5And2 e = new ExpOf5And2();
        e.outputK(new int[]{2,5,6},1000);
    }

    public void output(int N) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        for(int num = 2; num <= N; num++) {
            if((num % 2 == 0 && set.contains(num / 2)) || (num % 5 == 0 && set.contains(num / 5))) {
                set.add(num);
                System.out.println(num);
            }
        }
    }

    //What if factors (such as 5 and 2) are not known numbers?
    //print all possible numbers by factor1^i * factor2^j * factor3^k * ...
    public void outputK(int[] a, int N) { //an array of factors are given
        Set<Integer> set = new HashSet<>();
        set.add(1);
        for(int num = 2; num <= N; num++) {
            for(int i = 0; i < a.length; i++) {
                if ((num % a[i] == 0 && set.contains(num / a[i]))) {
                    set.add(num);
                    System.out.println(num);
                    break;
                }
            }
        }
    }

}
