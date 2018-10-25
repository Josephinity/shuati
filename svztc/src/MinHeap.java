import java.util.NoSuchElementException;

/**
 * Created by xiaobaby on 9/11/16.
 */
public class MinHeap {
    private int[] heap;
    private int len = 0;

    public MinHeap(int n) {
        heap = new int[n];
    }

    public boolean isEmpty() {
        if(len == 0) return true;
        return false;
    }

    public int size() {
        return len;
    }

    public int top() {
        if(isEmpty()) return Integer.MAX_VALUE;
        else return heap[0];
    }

    public boolean offer(int val) {
        if(len == heap.length) return false;
        int i = len;
        heap[i] = val;
        while(i > 0) {
            int p = (i - 1) / 2;
            if(heap[p] > val) swap(heap, i, p);
            i = p;
        }
        len++;
        return true;
    }

    public int poll() throws NoSuchElementException{
        if(len == 0) throw new NoSuchElementException();
        len--;
        if(len == 0) return heap[0];
        int ret = heap[0], val = heap[len], i = 0;
        while(i < len) {
            heap[i] = val;
            int lchild = 2 * i + 1, rchild = 2 * i + 2;
            if((lchild >= len)) return ret;
            if((heap[i] > heap[lchild] && (rchild >= len || heap[lchild] <= heap[rchild]))) {
                swap(heap, lchild, i);
                i = lchild;
            } else if(rchild < len && heap[lchild] > heap[rchild]) {
                swap(heap, rchild, i);
                i = rchild;
            }
        }
        return ret;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public void print() {
        int level = 1, i = 0;
        while(i < len) {
            int end = level + i;
            for (; i < end && i < len; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
            level <<= 1;
        }
    }
}
