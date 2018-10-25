/**

 */
public class SegmentTree {
    int[] arr;
    int[] tree;

    public SegmentTree(int[] a) {
        arr = a;
        tree = new int[2 * a.length - 1];

        buildTree(0, 0, a.length - 1);
    }

    private void buildTree(int root, int start, int end) {
        if(start > end) return;
        if(start == end) {
            tree[root] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        int leftChild = 2 * root + 1, rightChild = 2 * root + 2;
        buildTree(leftChild, start, mid);
        buildTree(rightChild, mid + 1, end);
        tree[root] = tree[leftChild] + tree[rightChild];
    }

    public void update(int idx, int value) {
        int diff = value - arr[idx];
        int root = 0, start = 0, end = arr.length - 1;
        while(start <= end) {
            tree[root] += diff;
            int mid = (start + end) / 2;
            if(idx <= mid) {
                end = mid;
                root = 2 * root + 1;
            } else {
                start = mid + 1;
                root = 2 * root + 2;
            }
        }
    }

    public int getRange(int start, int end) {
        return dfsGet(0, 0, arr.length - 1, start, end);
    }

    private int dfsGet(int root, int l, int r, int start, int end) {
        if(start > r || l > end) return 0;
        if(start >= l && r >= end) { //root in query range
            return tree[root];
        }
        int mid = (l + r) / 2;
        // query range covers left and right subtrees
        return dfsGet(root * 2 + 1, l, mid, start, mid) +
                dfsGet(root * 2 + 2, mid + 1, r, mid + 1, end);
    }


}
