package DP;

/**
 * Why do you want to work at facebook
 * Any questions for the team/
 *
 * Our team
 * test and maintain the financial/product usage data on daily basis
 * build  prototypes and predictive models and visualize the data as a proof of concepts for the product designers and sales
 * Create custom software components and analytics applications for internal usage
 *
Given an array of integers representing the vote weight of each state.
 Check whether it's possible to get a tie.


 Site Reliability Engineer

 slow down , speak up
 */
public class Vote {



    boolean tie(int[] weights, int half, int i) {

        if(half == 0) return true;
        if(half < 0 || i == weights.length) return false;

        return tie(weights, half - weights[i], i + 1) || tie(weights, half, i + 1);
    }

    boolean tie(int[] weights,int half) {

        boolean[][] mem = new boolean[weights.length + 1][half + 1];

        for(int j = 0; j <= weights.length; j++) mem[j][0] = true;

        for(int i = 1; i <= weights.length; i++) {

            for(int j = 0; j <= half; j++) {

                if(weights[i - 1] <= half) {

                    mem[i][j] = mem[i - 1][j] || mem[i - 1][j - weights[i - 1]];

                }
            }
        }

        return mem[weights.length][half];
    }
}
