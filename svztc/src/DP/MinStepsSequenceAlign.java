package DP;

/**
What is the minimum steps to change one string to another by deleting, adding or replacing a character.
 */
public class MinStepsSequenceAlign {

    public int minsteps(String a, String b) {

        int[][] steps = new int[a.length() + 1][b.length() + 1];

        for(int i = 1; i < steps.length; i++) {
            steps[i][0] = i;
        }

        for(int j = 1; j < steps[0].length; j++) {
            steps[0][j] = j;
        }

        for(int i = 1; i < steps.length; i++) {
            for(int j = 1; j < steps[0].length; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    steps[i][j] = steps[i - 1][j - 1];
                } else {
                    steps[i][j] = 1 + Math.min(steps[i - 1][j], Math.min(steps[i - 1][j - 1], steps[i][j - 1]));
                }
            }
        }

        return steps[a.length()][b.length()];
    }
}
