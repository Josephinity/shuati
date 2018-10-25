package DP;

/**
 *
 * https://www.youtube.com/watch?v=_nCsPn7_OgI
 "agdbdda"

 answer should be "adddd"
 */
public class LongestPalindromicSubsequence {


    public int longestPalindromicSubsquence (String s) {

        if(s.isEmpty()) return 0;

        int n = s.length();

        int[][] LPS = new int[n][n];

        for(int i = 0; i < n; i++) LPS[i][i] = 1; // the length of LPS from i to i is 1

        for(int len = 1; len < n; len++) { //length of palindrome subsequence

            for(int i = 0; i + len < n; i++) {

                if(s.charAt(i)== s.charAt(i + len)) {


                    // if charAt(i) == charAt(j), the max subsequence is 2 + maxPalLen[i - 1 , j - 1]
                    LPS[i][i + len] = 2 + LPS[i + 1][i + len - 1];

                } else {
                    //if "abab" is not palindromic, check for max palindrome between "aba" and "bab"
                    LPS[i][i + len] = Math.max(LPS[i + 1][i + len], LPS[i][i + len - 1]);

                }

            }

        }

        for(int[] x : LPS) {
            for (int y : x) {

                System.out.print(y + " ");
            }
            System.out.println();
        }

        return LPS[0][n - 1];

    }
}
