package Apple;

/**
 maintain median of data stream
 */

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
public class MedianOfStream {

    PriorityQueue<Integer> maxheap; //stores smaller half of data
    PriorityQueue<Integer> minheap; //stores greater half of data

    public MedianOfStream() {
        maxheap = new PriorityQueue<>((n, m) -> (m - n));
        minheap = new PriorityQueue<>();
    }

    public void insert(int n) {
        int maxheapsize = maxheap.size(), minheapsize = minheap.size();
        if(maxheapsize == 0 || maxheap.peek() >= n) {
            maxheap.add(n);
            if(maxheapsize > minheapsize + 1) {
                minheap.add(maxheap.poll());
            }
        } else {
            minheap.add(n);
            if(minheapsize > maxheapsize + 1) {
                maxheap.add(minheap.poll());
            }
        }
    }

    public double getMedian() throws NoSuchElementException {
        if(maxheap.isEmpty() && minheap.isEmpty()) throw new NoSuchElementException();
        int maxheapsize = maxheap.size(), minheapsize = minheap.size();
        if(maxheapsize == minheapsize) return maxheap.peek() + (minheap.peek() - maxheap.peek()) / 2.0;
        if(maxheapsize > minheapsize) return maxheap.peek();
        return minheap.peek();
    }
}
