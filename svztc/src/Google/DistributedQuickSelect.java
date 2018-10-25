package Google;
import java.util.*;
/**
 给一个数组找median。O(N)的quickselect
 follow up: 如果给你很多台机器，每台机器都有一堆数，如何求中间值。
 （你不能同时得到这些数因为数量很大）我想不到于是问这些数字是排序的么？
 然后面试官说好那就先当他们是排序的。所以那就是用二分找一个数，到每台机器里头再用二分找有几个数比它大。
 */

class Machine {

    int ID;
    List<Integer> nums;

    Median getMedian(int start, int end) {

        //quickselect
        return new Median();

    };

    Median getMedian() {

        //quickselect
        return new Median();

    }

    void add(List<Integer> nums) {


    }

    List<Integer> read(int start, int end) {

        return new ArrayList<Integer>();

    }

    void deleteRange(int start, int end) {}

}

class Median {

    int median;
    Integer index1;
    Integer index2; //if median comes from even length array, this is the index of the greater number
    //int start;    //range start
    //int end;    //range end
    int ID;   //machine ID

}

public class DistributedQuickSelect {

    public int median(List<Machine> machines) {

        if(machines.size() == 1) return machines.get(0).getMedian().median;

        List<Median> medians = new ArrayList<>();

        Collections.sort(medians, (o1, o2) -> (o1.median - o2.median));

        int lo = 0, hi = medians.size() - 1;

        while(lo < hi) {

            Median m1 = medians.get(lo), m2 = medians.get(hi);

            Machine machine1 = machines.get(m1.ID);
            Machine machine2 = machines.get(m2.ID);

            int discard_size = Math.min(m1.index1,
                    m2.index2 == null ? machine2.nums.size() - m2.index1: machine2.nums.size() - m2.index2 - 1);

            machine1.deleteRange(0, discard_size);
            machine1.add(machine2.read(machine2.nums.size() - discard_size - 1, machine2.nums.size() - 1));

            machines.remove(hi);

            lo++;
            hi--;
        }

        return median(machines);


    }


    //s = re * n
    //s1 = re * (2 * n - 2)

    //if (n == 3)
    // s = 3 re
    // re * 4

    // s = 4 re
    // s1 = 6

    // s = 5 re
    // s1 = 8 re

    //s = 6re
    // s1 = 10 re

    //s = 8 re
    // s1 = 14re
}
