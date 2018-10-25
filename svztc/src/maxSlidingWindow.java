/**

 239. Sliding Window Maximum


 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 */
import java.util.Deque;
import java.util.ArrayDeque;

public class maxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> q = new ArrayDeque<>();

        int[] window_max = new int[k == 0 ? nums.length : nums.length - k + 1];

        int i = 0;

        while(i < nums.length) {

            if(q.isEmpty() || q.peekLast() >= nums[i]) {

                q.add(nums[i]);

                if(i - k >= 0 && q.peek() == nums[i - k]) { //the current max slides off the window

                    q.remove(0);

                }

                if(i >= k - 1) window_max[i + 1 - k] = q.peek(); //record current window max

                i++; //slide window

            } else {

                q.pollLast();

            }
        }

        return window_max;
    }
}
