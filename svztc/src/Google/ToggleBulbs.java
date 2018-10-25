package Google;

/**

 第一轮：family trees里，给定任意两个节点，找出是否有blood relationship（看两个节点是否有共同的祖先）
 第二轮：已知一个linked list，给出linked list中的若干个节点，问这些节点中能够组成多少个直接相连的group。例如：1->2->3->4->5->6, (1,3,4,6)可以组成三个group
 第三轮：对N个小灯泡，实现两个method：1.isOn(int i)：判断第i个灯泡ON/OFF， 2.toggle(int start, int end)：turn on/off 从第start开始到end结束的灯泡
 第四轮：给定一个整数N，从0开始，每次增加2^i， i为任意整数，直到增加至N。打印从0到N的所有可能的路径。例如: N=4， 结果为：[0,1,2,3,4], [0,1,2,4], [0,1,3,4], [0,2,4], [0,2,3,4], [0,4]
 第五轮：给一个matrix，判断是否为沿对角线对称矩阵。follow up：single node上memory有限的情况如何优化算法，distributed cluster上如何优化。


 Round1
 Find if two people in a family tree are blood-related.

 Round2
 Given some nodes in a singly linked list, how many groups of consecutively connected nodes there is.
 For linked list
 0->1->2->3->4->5->6,
 given nodes 1, 3, 5, 6
 there are 3 groups [1], [3], and [5, 6].

 Round3
 For N light bulbs , implement two methods
 I. isOn(int i)  - find if the ith bulb is on or off.
 II. toggle(int i, int j) - i <= j. Switch state (switch on if it's off, turn off if it's on) of every bulb in range i to j.
 All bulbs are off initially.


 Round4
 Starting from num = 0, add 2^i (where i can be any non-negative integer) to num until num == N. Print all paths of how num turns from 0 to N.
 For example if N = 4,
 Paths printed are [0,1,2,3,4], [0,1,2,4], [0,1,3,4], [0,2,4], [0,2,3,4], [0,4].
 [0,2,4] is made from 0 + 2^1 + 2^1. [0,1,3,4] from 0 + 2^0 + 2^1 + 2^0

 */

//Binary Indexed Tree (O(logN) Toggle, O(logN) Get.
public class ToggleBulbs {

    int[] bulbs;

    public ToggleBulbs(int n) { //n bulbs given
        bulbs = new int[n + 1];
    }

    public boolean isOn(int i) {
        //a bulb is on if it's toggled an odd number of times
        return read(i) % 2 == 1;
    }

    // toggle(i, j) is equivalent to
    // toggle(i, n - 1) and then toggle(j, n - 1)
    public void toggle(int i,int j) {
        toggle(i);
        toggle(j + 1);
    }

    //toggle from ith to the last bulb (a standard update in BITree)
    private void toggle(int idx){
        int node = idx + 1;
        while (node < bulbs.length){
            bulbs[node] = (bulbs[node] + 1) % 2;
            node += (node & -node);
        }
    }

    //get the number of times bulb i toggled (read prefix sum from 0 to i in BITree)
    private int read(int idx) {
        int node = idx + 1;
        int sum = 0;
        while (node > 0) {
            sum += bulbs[node];
            node -= (node & -node);
        }
        return sum;
    }

}
