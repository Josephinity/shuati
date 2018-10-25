package Google;
import java.util.ArrayDeque;
import java.util.Deque;
/**
 Given an array a[] and an integer k, a[i] means flower at position a[i] will blossom at day i.
 Find the first day that there are k slots between two blooming flowers.


 */
public class BlossomDay {
    /*
    # The question is about identifying any window size k that its numbers at both ends lesser than numbers in between
def bloomDay(a, k): # k must be >= 0
    window = [] # elements in window maintain descending order.
    firstDay = 10000000
    i = 0
    while i < len(a):
        if not window:
            window.append(i)
            i += 1
        elif i - window[0] <= k:
            if a[i] >= a[window[0]]:
                window = [i]
                i += 1
            elif a[i] >= a[window[-1]]:
                window.pop()
            else:
                window.append(i)
                i += 1
        else: #window size == k
            if len(window) == 1 or a[window[1]] < a[i]:#valid window with both end numbers biggest
                firstDay = min(firstDay, a[i], a[window[0]])
                window = [i]
                i += 1
            else:  #a[i] is less than a number in the middle of the window
                window.pop(0)
                if a[window[-1]] <= a[i]:
                    window.pop()
                else:
                    window.append(i)
                    i += 1
    return firstDay
     */


    /*
    There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

    Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

    For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

    Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

    If there isn't such day, output -1.

    Example 1:

    Input:
    flowers: [1,3,2]
    k: 1
    Output: 2
    Explanation: In the second day, the first and the third flower have become blooming.


    Example 2:

    Input:
    flowers: [1,2,3]
    k: 1
    Output: -1

     */

    public static int bloomday(int[] arr, int k) {
        int first = -1;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        for(int i = 1; i < arr.length; i++) {
            if(q.isEmpty() || arr[q.peekLast()] < arr[i]) {
                q.push(i);
                while(i - q.peekFirst() - 1 >= k) q.pollFirst();
            } else {
                if(i - q.peekFirst() - 1 == k) {
                    if(first == -1 || arr[q.peekFirst()] < first)
                        first = Math.max(arr[q.peekFirst()], arr[i]);
                    q.clear();
                }
                q.add(i);
            }
            System.out.println(q);
        }
        return first;
    }

    public static void main(String[] args) {
        System.out.println(bloomday(new int[]{1, 2, 3}, 1));
    }
}
