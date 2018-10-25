package FB;

/**
 * Samatha Liu
 *
 * 1. BST to DLL
 *
 * 2. Merge Intervals
 interval [startTime, stopTime)   ----integral  time stamps

 given a series of these intervals  I1, I2......In

 Find time stamp t that exists most times in the intervals

 if startTime <= t< stopTime, then t is in this interval .

 example：  [1,3),  [2, 7),   [4,  8),   [5, 9)

 5 and 6 exist in 3 of the intervals，so return 5，6。  (Hard)



 */
import java.util.List;
import java.util.ArrayList;

import java.util.Arrays;

public class MergeWeightedIntervals {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {3, 5}, {4, 6}};
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        merge2(starts, ends);
        List<int[]> ls = maxOverlap(intervals);
        for (int[] interval : ls) {
            System.out.println(interval[0] + "," + interval[1]);
        }

        System.out.println(maxWeightInMerge(new int[][]{{1, 2, 1}, {3, 5, 2}, {4, 6, 5}}));//7 is maxweight
        mergeWeightIntervals(new int[][]{{1, 2, 1}, {3, 5, 2}, {4, 6, 5}});

    }

    public static void mergeWeightIntervals(int[][] intervals) { //output every merged interval with the weights
        int[][] startWeight = new int[intervals.length][2]; //{{s1, w1}, {s2, w2}, {s3, w3}...}
        int[][] endWeight = new int[intervals.length][2];   //{{e1, -w1}, {e2, -w2}, {e3, -w3}...}
        for(int i = 0; i < intervals.length; i++) {
            startWeight[i] = new int[]{intervals[i][0], intervals[i][2]};
            endWeight[i] = new int[]{intervals[i][1], -intervals[i][2]};
        }
        Arrays.sort(startWeight, (int[] o1, int[] o2)->(o1[0]- o2[0]));
        Arrays.sort(endWeight, (int[] o1, int[] o2)->(o1[0]- o2[0]));

        int curWeight = startWeight[0][1];
        int endItr = 0;
        int prevStart = startWeight[0][0];
        for(int startItr = 1; startItr < intervals.length || endItr < intervals.length;) {
            int curStart = startItr == intervals.length ? 0: startWeight[startItr][0], curEnd = endWeight[endItr][0];
            if(startItr == intervals.length || curStart >= curEnd) {
                System.out.print("{" + prevStart + "," + curEnd + "," + curWeight + "}, ");
                prevStart = curEnd;
                curWeight += endWeight[endItr][1];
                endItr++;
            } else {
                System.out.print("{" + prevStart + "," + curStart + "," + curWeight + "}, ");
                prevStart = curStart;
                curWeight += startWeight[startItr][1];
                startItr++;
            }
        }
    }


    public static int maxWeightInMerge(int[][] intervals) { //input is ({{s1,e1,w1}, {s2,e2,w2},{s3,e3,w3},...})
        int[][] startWeight = new int[intervals.length][2]; //{{s1, w1}, {s2, w2}, {s3, w3}...}
        int[][] endWeight = new int[intervals.length][2];   //{{e1, -w1}, {e2, -w2}, {e3, -w3}...}
        for(int i = 0; i < intervals.length; i++) {
            startWeight[i] = new int[]{intervals[i][0], intervals[i][2]};
            endWeight[i] = new int[]{intervals[i][1], -intervals[i][2]};
        }
        Arrays.sort(startWeight, (int[] o1, int[] o2)->(o1[0]- o2[0]));
        Arrays.sort(endWeight, (int[] o1, int[] o2)->(o1[0]- o2[0]));

        int curWeight = startWeight[0][1];
        int maxWeight = curWeight;
        int endItr = 0;
        for(int startItr = 1; startItr < intervals.length;) {
            int curStart = startWeight[startItr][0], curEnd = endWeight[endItr][0];
            if(curStart >= curEnd) {
                curWeight += endWeight[endItr][1];
                endItr++;
            } else {
                curWeight += startWeight[startItr][1];
                startItr++;
                if(maxWeight < curWeight) maxWeight = curWeight;
            }
        }
        return maxWeight;
    }


    public static List<int[]> merge2(int[] starts, int ends[]) {//print every interval and its weight after mergin
        if(starts.length == 0) return new ArrayList<>();
        int prev = starts[0], i = 1, j = 0;
        int weight = 1;
        List<int[]> res = new ArrayList<>();
        while(j < starts.length) {
            if(i < starts.length && starts[i] < ends[j]) {
                res.add(new int[]{prev, starts[i], weight});
                prev = starts[i];
                weight++;
                i++;
            } else {
                res.add(new int[]{prev, ends[j], weight});
                prev = ends[j];
                weight--;
                j++;
            }
        }
        for(int k = res.size() - 1; k >= 0; k--) {
            int[] r = res.get(k);
            if(r[2] == 0 || r[0] == r[1]) res.remove(k);
        }
        for(int k = 0 ; k < res.size() - 1;) {
            int[] r = res.get(k), next = res.get(k + 1);
            if(r[1] == next[0] && r[2] == next[2]) {
                res.get(k)[1] = next[1];
                res.remove(k + 1);
            } else k++;
        }
        for(int[] r: res) {
            System.out.print("("+ r[0] + "," + r[1]+ "," + r[2] + ")  ");
        }
        return res;
    }



    public static List<int[]> maxOverlap(int[][] intervals) { //input [[start1, end1], [start2, end2]...]
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0;
        int endsItr = 0;
        int mark = 1;
        for(int i=0; i<starts.length; i++) {
            if (starts[i] < ends[endsItr]) {
                rooms++;
                mark = endsItr; //mark where the first max overlap zone appears
            } else {
                endsItr++;
            }
        }
        List<int[]> points = new ArrayList<>(); //result
        while(mark <= ends.length - rooms) {
            if(ends[mark] > starts[mark + rooms - 1]) {
                points.add(new int[]{starts[mark + rooms - 1], ends[mark]});
            }
            mark++;
        }
        //points: the intervals that demand the max number of rooms
        //optional: convert non-overlap intervals into 'points' to collection of numbers if necessary
        return points;
    }


    //meeting room II
    public static int merge(int[][] intervals) { //input [[start1, end1], [start2, end2]...]

        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if (starts[i] < ends[endsItr]) {
                rooms++;
                System.out.print(rooms + " ");
            } else
                endsItr++;
        }
        return rooms;
    }


}
