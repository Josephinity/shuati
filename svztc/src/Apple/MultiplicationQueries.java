package Apple;


/*
1）唯一不是印度人的面试官。中东小哥，人挺nice。给一个数组a和一些queries, 每个query Q(i,j), 返回ai*...*aj的乘积。第一个问题，所有数都是正数。第二个问题，如果有零存在怎么办。

3）Q1: design data structure 来支持以下操作：update(i, j, d) : 对所有在[i,j]之间的数字进行 ak += d，包括ai, aj. get(i) : 返回数字i的值。0<=i<=j。j可以很大，所以你的数据结构要支持resize或者用其他办法来有效处理这个情况。
   Q2: 给定一个链表，每个节点可以是一个数或者head of another linkedlist。要求把这个链表摊平了。
4）系统设计：问了工作项目和设计POI search suggestions.
5) 由于2）此时已经眼冒金星，大脑根本不转了。幸好这两道题见过，要不估计挂的妥妥的。Q1: LC 238 Q2: LC279
6) 和director聊半小时，他让我问他问题，没问题了就他问我。为了节省体力，我就一直问他问题，然后负责点头，最后留了三分钟让他问了我一个问题 what do you care about？

 Apple Map Team, On-site
 1. Given an array A and some queries, query(i, j) returns the result of Ai*...*Aj, in other words the multiplication from Ai to Aj.
 The numbers in A are non-negative. Implement query(i, j).
 */
public class MultiplicationQueries {

//    since query(i, j) will be called multiple times
//    ideally it should take O(1) time
//    I. The key is when there is 0 between i, j, the query can return 0
//      Initial another array to keep track of at the current index i, where is the closest 0 to the left of i (including i).
//      This array enables finding 0 between i and j in constant time.
//    II. For any given range if there is no zero, initialize an array of cumulative product from the first positive number to
//    the left up to current index i. So that if there is no zero in between, query(i, j) equals to product[j] / product[i - 1]  (constant time).
//    Remember to discuss with the interviewer if the product can get integer overflow
    int[] array;
    int[] zeroToLeft;
    int[] product;

    public MultiplicationQueries(int[] a) {
        array = a;
        if(array.length > 0) {
            zeroToLeft = new int[array.length];
            product = new int[array.length];
            zeroToLeft[0] = array[0] == 0 ? 0: -1;
            product[0] = array[0] == 0 ? 0: array[0];
        }
        for(int i = 1; i < array.length; i++) {
            zeroToLeft[i] = array[i] == 0 ? i: zeroToLeft[i - 1];
            product[i] = array[i] == 0 ? 0: array[i - 1] == 0 ? array[i]: array[i] * product[i - 1];
        }
    }

    public int query(int i, int j) {
        if(i > j || i >= array.length || j >= array.length || i < 0 || j < 0) return -1;
        if(i == j) return array[i];
        return zeroToLeft[j] >= i ? 0: (i == 0 || array[i - 1] == 0) ? product[j]: product[j] / product[i - 1];
    }
}
