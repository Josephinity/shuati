package DP;
import java.util.*;
/**
 * Created by xiaobaby on 1/20/17.
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || wordList == null || wordList.size() == 0) {
            return 0;
        }
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        int length = 1;
        beginSet.add(beginWord);
        endSet.add(endWord);
        visited.add(beginWord);
        visited.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            Set<String> temp = new HashSet<>();
            for (String s : beginSet) {
                char[] arr = s.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char original = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[i] = c;
                        String word = new String(arr);
                        if (endSet.contains(word)) {
                            return length + 1;
                        }
                        if (!visited.contains(word) && wordList.contains(word)) {
                            temp.add(word);
                            visited.add(word);
                        }
                    }
                    arr[i] = original;
                }
            }
            length++;
            beginSet = temp;
        }
        return 0;
    }
}
