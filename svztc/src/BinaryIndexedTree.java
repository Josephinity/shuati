/**
 *
 */
public class BinaryIndexedTree {

    int[] array;
    int[] BIT;

    public BinaryIndexedTree(int[] array) {
        BIT = new int[array.length + 1];
        this.array = array;

        for(int i = 0; i < array.length; i++) {
            update(i + 1, array[i]);
        }
    }

    private void update(int index, int num) { //num is the diff = (newVal - oldVal) at array[index]
        while(index < BIT.length) {
            BIT[index] += num;
            index = getNext(index);
        }
    }

    public int getSum(int a, int b) {//get range sum between (a, b) inclusive
        return getSum(b + 1) - getSum(a + 1); //a and b 0-based
    }

    private int getSum(int index) { //get prefix sum (0, index)
        int sum = 0;
        while(index > 0) {
            sum += BIT[index];
            index = flipRightMostSetBit(index);
        }
        return sum;
    }

    private int getNext(int n) {
        return n + (n & -n);
    }

    private int flipRightMostSetBit(int n) { //get parent
        return n & (n - 1);
    }
}
