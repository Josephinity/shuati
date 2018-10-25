package DP;

import java.util.Random;
/**
 There is a fence with n posts, each post can be painted with one of the k colors.
 You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 Return the total number of ways you can paint the fence.

 Tim
 */
public class PaintFence {

    public static void main(String args[]) {
        Random rand = new Random();
        int n = rand.nextInt(20);
        System.out.println(paintFence(n));
        System.out.println(numWays(n, 2));

    }

    public static int paintFence(int n) {
        if(n == 1) return 2;
        int b1 = 1, r1 = 1, b2 = 0, r2 = 0, i = 2;
        while(i <= n) {
            int tmpb1 = b1, tmpr1 = r1;
            b1 = r1 + r2;
            r1 = tmpb1 + b2;
            b2 = tmpb1;
            r2 = tmpr1;
            i++;
        }
        return b1 + b2 + r1 + r2;
    }

    public static int numWays(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }

        if (n == 1) {
            return k;
        }

        int preSame = 0;
        int preDiff = k;

        for (int i = 1; i < n; i++) {
            int same = preDiff;
            int diff = (k - 1) * (preSame + preDiff);

            preSame = same;
            preDiff = diff;
        }

        return preSame + preDiff;
    }
}
