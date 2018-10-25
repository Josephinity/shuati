package FB;

/**
 Calculate a moving average that considers the last N values.
 ##### March 2018 Phone Interview
 #### Circular Queue (Interviewer wasn’t happy with linked list said pointers take space)
 */
import java.util.*;
public class MovingAvg {

    int[] q;  // a circular queue of size N
    int head; //queue head
    int tail; //queue tail
    int size; // queue size
    int sum;

    public MovingAvg(int N) {
        q = new int[N];
    }

    //@param num - the next number from data stream
    //@return - new average with num included and expired number excluded
    public double getAverage(int num) {
        double avg = 0;
        sum += num;
        if(size == q.length) {
            sum -= q[head];
            head = (head + 1) % q.length;
        } else {
            size++;
        }
        q[tail] = num;
        tail = (tail + 1) % q.length;
        return 1.0 * sum / size;
    }
}
