package Google;
import java.util.Random;
/**
random selection with removal
 */
public class RandomSelectTree {

    int[] tree;
    int[] weights;
    int n; //number of balls

    public static void main(String args[]) {
        RandomSelectTree t = new RandomSelectTree(new int[]{2,9,1,6,10,4});
        for(int node: t.tree) {
            System.out.print(node + " " );
            System.out.println();
        }
        while(t.n > 0) {
            t.randomPoll();
        }
    }
//            32
//       12          20
//    11    1     16     4
//  2   9  0  0  6  10

    RandomSelectTree(int[] weights) {
        tree = new int[2 * weights.length + 1];
        this.weights = weights;
        n = weights.length;
        buildTree(0, 0, weights.length - 1);
    }

    private void buildTree(int root, int start, int end) {
        if(start > end) return;
        if(start == end) {
            tree[root] = weights[start];
            return;
        }
        int mid = (start + end) / 2;
        int leftChild = 2 * root + 1, rightChild = 2 * root + 2;
        buildTree(leftChild, start, mid);
        buildTree(rightChild, mid + 1, end);
        tree[root] = tree[leftChild] + tree[rightChild];
    }

    public int randomPoll() {
        if(n == 0) {
            //Exception
        }
        int rand = new Random().nextInt(tree[0]);
        System.out.print(rand + "  random  ");
        int start = 0, end = weights.length - 1, node = 0;
        while(start < end) {
            if(rand < tree[node * 2 + 1]) {//chosen ball is between 0 to (start + end) / 2
                node = node * 2 + 1;
                end = (start + end) / 2;
            } else {
                rand -= tree[node * 2 + 1];  //!!! tricky here: randomize in remaining range
                node = node * 2 + 2;
                start = (start + end) / 2 + 1;
            }
        }
        delete(start, node);
        System.out.println(start);
        return start;
    }

    private void delete(int idx, int node) { //remove ball idx.
        n--;
        while(node > 0) {
            tree[node] -= weights[idx];
            node = (node - 1) / 2;
        }
        tree[0] -= weights[idx];
    }
}
