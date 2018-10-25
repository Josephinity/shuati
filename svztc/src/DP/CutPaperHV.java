package DP;

/**
 Given a piece of paper with width W and Length L and a series of measurements of paper, each corresponds to a
 price.
 What is the maximum price that you can get by cutting the paper.
 */
public class CutPaperHV {

    class TargetPaper {
        int length; //length
        int width; //width
        int price; // price

    }

    public int maxProfitFromCutting(TargetPaper[] targetpapers, int L, int W) {

        int[][][] mem = new int[targetpapers.length + 1][L + 1][W + 1];
        for(int i = 1; i < targetpapers.length; i++) {
            for(int w = 0; w <= W; w++) {
                for(int l = 0; l <= L; l++) {
                    int case1 = mem[i - 1][w][l];   //not cut it out
                    if(targetpapers[i].length > l || targetpapers[i].width > w) {
                        mem[i][w][l] = case1;
                    }
                    int case2 = mem[i - 1][w - targetpapers[i].width][l - targetpapers[i].length]; //cut it out
                    mem[i][w][l] = Math.max(case1, case2 + targetpapers[i].price);
                }
            }
        }
        return mem[targetpapers.length][L][W];

    }
}
