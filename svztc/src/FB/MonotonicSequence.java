package FB;

/**
 Check if a sequence of numbers is Monotonic


 */
public class MonotonicSequence {

    public boolean isMonotonicSequence(int[] seq) {
        if(seq.length <= 2) return true;
        int lo = 0, hi = seq.length - 1;
        if(seq[lo] < seq[hi] && isMonotonic(seq, lo, hi, 1)) { //increasing
            return true;
        } else if(seq[lo] > seq[hi] && isMonotonic(seq, lo, hi, -1)) { //decreasing
            return true;
        } else if(seq[lo] == seq[hi] && isMonotonic(seq, lo, hi, 0)){ //flat
            return true;
        }
        return false;
    }

    private boolean isMonotonic(int[] arr, int lo, int hi, int trend) {
        if(hi == lo || hi == lo + 1) return true;
        int mid = lo + (hi - lo) / 2;
        if((arr[hi] - arr[lo]) == trend * Math.abs(arr[hi] - arr[lo])) {//Assuming no integer overflow. If overflow possible, change all intermediate variables to long ints
            return isMonotonic(arr, hi, lo, trend);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMonotonicSeq(new int[]{1, 2, 2, 6,6,9,9,9,9}));
    }
    //Could do it without recursion and constant extra space.

    public static boolean isMonotonicSeq(int[] seq) {
        if(seq.length <= 2) return true;
        int n = seq.length - 1;
        int trend = 0;
        if(seq[0] < seq[n]) trend = 1;
        else if(seq[0] > seq[n]) trend = -1;
        int step = n / 2;
        while(step > 0) {
            int lo = 0, hi = step;
            while(hi <= n) {
                System.out.println(lo + "," + hi);
                if(seq[hi] - seq[lo] != trend * Math.abs(seq[hi] - seq[lo])) {
                    return false;
                }
                lo = hi;
                hi += step;
            }
            step >>= 1;
        }
        return true;
    }

}
