package Graph;
import java.util.*;
/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

        Only one letter can be changed at a time.
        Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
        For example,

        Given:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log","cog"]
        As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
        return its length 5.
 */
public class WordLadder {

    private Map _definitions = Collections.unmodifiableMap(new HashMap());

   int ladderLengthdfs(String beginWord, String endWord, Set<String> wordList) {
        //DFS
        Set<String> path = new HashSet<String>(); // current path
        Map<String, Integer> length = new HashMap<String, Integer>();
        if(beginWord.equals(endWord)) return 1;
        wordList.add(endWord);
        return ladderLength(path, length, beginWord, endWord, wordList);
    }

    private int ladderLength(Set<String> path, Map<String, Integer> length, String beginWord, String endWord, Set<String> wordList) {
        if(beginWord.equals(endWord)) return 1;
        if(length.containsKey(beginWord)) return length.get(beginWord);
        int min = Integer.MAX_VALUE;
        path.add(beginWord);
        for(int i = 0; i < beginWord.length(); i++) {
            for(char x = 'a'; x <= 'z'; x++) {
                if(x == beginWord.charAt(i)) continue;
                String next = ladderWord(beginWord, i, x);
                if(!wordList.contains(next) || path.contains(next)) continue;
                int llen = ladderLength(path, length, next, endWord, wordList);
                if(llen == 0) continue;
                else {
                    min = Math.min(llen, min);
                }
            }
        }
        path.remove(beginWord);
        if(min == Integer.MAX_VALUE) {
            length.put(beginWord, 0);
            return 0;
        } else {
            length.put(beginWord, min + 1);
            return min + 1;
        }
    }
    private String ladderWord(String beginWord, int i, char x){
        StringBuilder str = new StringBuilder(beginWord);
        str.setCharAt(i, x);
        return str.toString();
    }

    //BFS
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        Set<String> reached = new HashSet<String>();
        reached.add(beginWord);
        wordDict.add(endWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<String>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.size() == 0) return 0;
            reached = toAdd;
        }
        return distance;
    }

    //BFS double directions
    public int ladderLengthBFS(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || beginWord.isEmpty() || endWord == null || endWord.isEmpty() || wordList == null || wordList.size() == 0) {
            return 0;
        }
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>(); //avoid going back to visited words
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
