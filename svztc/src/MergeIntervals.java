/**
 * FB
 given a list of tuples of movie watched times,
 find how  many unique minutes of the movie did the viewer watch e.g. [(0,15),(10,25)].
 The viewer watched 25 minutes of the movie.
 */
import java.util.Arrays;
import java.util.*;
public class MergeIntervals {
    public int mergeIntervals(int[][] array) {
        Arrays.sort(array, (int[] o1,int[] o2)-> (o1[0] - o2[0]));

        int start = 0, end = 0, sum = 0;
        for(int[] interval: array) {
            if(interval[0] > end) {
                sum += end - start;
                start = interval[0];
                end = interval[1];
            } else {
                end = Math.max(interval[1], end);
            }
        }
        sum += end - start;
        return sum;
    }

    public int sumIntervals(int[][] array) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int[] interval: array) {
            if(min > interval[0]) min = interval[0];
            if(max < interval[1]) max = interval[1];
        }
        boolean[] inUse = new boolean[max - min + 1];
        int sum = 0;
        for(int[] interval: array) {
            int end = interval[1] - min;
            for(int i = interval[0] - min; i <= interval[1] - min; i++) {
                if(!inUse[i]) {
                    inUse[i] = true;
                    sum++;
                }
            }
        }
        return sum;
    }

    List<Integer> getSumLevel(TreeNode root) {
        Queue<TreeNode> currLevel = new ArrayDeque<>();
        Queue<TreeNode> nextLevel = new ArrayDeque<>();
        currLevel.add(root);
        List<Integer> sumByLevel = new ArrayList<>();
        int sum = 0;
        while(!currLevel.isEmpty()) {
            while(!currLevel.isEmpty()) {
                TreeNode n = currLevel.poll();
                sum += n.val;
                if(n.left != null) nextLevel.add(n.left);
                if(n.right != null) nextLevel.add(n.right);
            }
            sumByLevel.add(sum);
            sum = 0;
            currLevel = nextLevel;
        }
        return sumByLevel;
    }


}
