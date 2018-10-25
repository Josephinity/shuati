package DP; /**
 * Created by xiaobaby on 2/3/17.
 */
import java.util.*;

class Item {
    int weight;
    int value;

    public Item(int w, int val){
        weight = w;
        value = val;
    }
}
public class KnapSack {


    //recursion
    public int knapSackRec(List<Item> items, int limit, int i) {

        //base case
        if(i == -1) return 0;
        //case1 not keep current item
        int case1 = knapSackRec(items, limit, i - 1);

        Item curr = items.get(i);
        if(limit < curr.weight) return case1;

        //case2 keep current item
        int case2 = knapSackRec(items, limit - curr.weight, i - 1) + curr.value;

        return Math.max(case1, case2);
    }

    //bottom up

    public int knapSack(List<Item> items, int limit) {

        int[][] mem = new int[items.size() + 1][limit + 1];

        for(int i = 0; i < items.size(); i++ ){
            for(int w = 0; w <= limit; w++) {
                int case1 = mem[i][w];
                if (w < limit) {
                    mem[i + 1][w] = mem[i][w];
                } else {
                    int case2 = mem[i][w - items.get(i).weight];
                    mem[i + 1][w] = Math.max(case1, case2);
                }
            }
        }
        return mem[items.size()][limit];
    }
}
