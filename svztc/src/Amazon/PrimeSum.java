package Amazon;

/**
 计算小于num的prime的sum，follow up：如果需要request 1b次，你如何优化，我说了一个先把所有的结果算出来，面试官不满意。
 然后给了面试官提示加入7 8 9 10，和都是17.最后搞了一个把数值都存在treemap中。
 问了一下时间复杂度。这个估计就想挂我了，然后问了一下parking lot的ood，问了需要啥硬件。。。不问如何class设计。。


 Phone Interview Amazon, Seattle
 I. Get the sum of all prime numbers up to N. primeSum(N).
 Follow-up: If primeSum(N) is frequently called, how to optimize it.

 II. OODesign Parking Lot

 I. At first find found all primes <= N (sieve of Eratosthenes). Getting the sum will be easy then.
 Follow-up:
 Cache the sums for any given N to save time. {N:SUM}
 Optimization: Don't have to store sums for every N.
 When N = 7, N = 8, N = 9, N = 10, the prime sum remains 17.
 For N between 11 to 12, the prime sum is 28.
 For N between 13 to 16, the sum is 41.
 Use a BST structure as the cache. For N = 16, cache:
 {2:3, 4:6, 6:11, 10:17, 12:28, 16:41}

 For a given N, call cache.ceilingKey(N) to find the bucket for N.

 N/log(n) * log(N)


 Complexity
 Time:
 sieve of Eratosthenes takes O(NloglogN) time.
 Insert an element into BST takes O(logN), there are N/logN primes in total to be added.
 So building the cache takes logN * N / LogN = O(N) time
 requesting primeSum(N) takes O(logN)

 Space:
 sieve of Eratosthenes takes O(N) extra space which will later be release after the cache is created.
 Cache: O(N/logN)
 */
import java.util.TreeMap;
public class PrimeSum {

    TreeMap<Integer, Integer> sums;

    public PrimeSum(int n) { //input the upper limit for all Ns
        sums = new TreeMap<>();
        // init an array to track prime numbers
        boolean[] primes = new boolean[n + 1];
        for (int i = 2; i < n; i++)
            primes[i] = true;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (primes[i]) {
                for (int j = i + i; j < n; j += i)
                    primes[j] = false;
            }
        }
        // insert sums into cache
        int sum = 0;
        for(int i = 2; i <= n; i++) {
            if(primes[i]) {
                sums.put(i - 1, sum);
                sum += i;
            }
        }
        if(primes[n]) {
            sums.put(n, sum);
        }
    }

    public int primeSum(int N) {
        Integer ceiling = sums.ceilingKey(N);
        //if(ceiling == null) {
            //Exception("input value overflows");
        //}
        return sums.get(ceiling);
    }

}
