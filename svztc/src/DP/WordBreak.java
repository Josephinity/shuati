package DP;
import java.util.Set;
/**
 Word Break
 Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 For example, given
 s = "leetcode",
 dict = ["leet", "code"].
 */
public class WordBreak {

    public boolean wordBreak(Set<String> dict, String word) {
        int n = word.length();
        boolean[] breakable = new boolean[n + 1];
        breakable[0] = true;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                if(breakable[j] && dict.contains(word.substring(j + 1, i))) {
                    breakable[i] = true;
                    break;
                }
            }
        }

        return breakable[n];
    }
}
