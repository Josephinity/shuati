package DP;

/**
 Q: Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 For example,
 Given:
 s1 = "aabcc",
 s2 = "dbbca",
 When s3 = "aadbbcbcac", return true.
 When s3 = "aadbbbaccc", return false.
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if ((s1.length()+s2.length())!=s3.length()) return false;
        boolean[][] align = new boolean[s2.length()+1][s1.length()+1];
        //Base Case
        align[0][0] = true;

        for (int i = 1; i < align[0].length; i++){
            align[0][i] = align[0][i-1]&&(s1.charAt(i-1)==s3.charAt(i-1));
        }

        for (int i = 1; i < align.length; i++){
            align[i][0] = align[i-1][0]&&(s2.charAt(i-1)==s3.charAt(i-1));
        }

        for (int i = 1; i < align.length; i++){
            for (int j = 1; j < align[0].length; j++){ // General Case
                align[i][j] = (align[i-1][j]&&(s2.charAt(i-1)==s3.charAt(i+j-1)))
                        || (align[i][j-1]&&(s1.charAt(j-1)==s3.charAt(i+j-1)));
            }
        }

        return align[s2.length()][s1.length()];

    }
}
