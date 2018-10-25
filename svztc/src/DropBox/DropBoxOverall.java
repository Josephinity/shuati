package DropBox;

/**
 * Design
 https://www.evernote.com/shard/s424/sh/abe1558e-b512-4ff0-929b-f305221166e0/3feb3686c7b488d341ddfec1629a836f

 Coding
 https://www.evernote.com/shard/s424/sh/2804d7e0-c93c-455d-9b79-027caa1750c5/48c247d32264b0e5739c5fc27c7eaff

 Algo + follow ups!

 coin change gist
 Can do DP, but cannot do dfs and memoize
 word break gist
 Can return boolean, cannot return result
 Implement Bit-torrent (is File done) gist
 Not sure if best implementation
 2-D array of "sharpness" values gist
 Weird DP, can do smarter.
 cannot use DFS
 find byte[] in a file gist
 Kmp only returns one value, not list
 LC38 Count And Say gist
 Weird question

 Link to Evernote Dropbox Algo for follow up questions!

 Threading
 Overview gist
 Web crawler: single thread, multi-thread
 More threading problems

 Systems:
 Log Hits gist
 Web crawl gist
 Allocate ID gist
 Max photo count
 Token bucket
 Read write lock
 KV store transaction gist

 Link to Evernote Dropbox Systems Design for questions!




 Dropbox interview questions:
 1. coin change
 2. word pattern
 3. word break
 4. phone number + wordbreak
 5. design phone dictionary LC 379
 6. game of life:
 7. Number of islands
 8. Implement Bit-torrent (is File done)
 9. 2-D array of "sharpness" values
 10. find byte[] in a file
 11. LC38 Count And Say ：

 Learn:
 KMP (for dropbox #8)
 Rolling hash (for dropbox #8)
 Quickselect
 Threading
 System Design

*/
import java.util.*;
public class DropBoxOverall {

    /*
    from collections import defaultdict
    def matches(s,pattern,p):
       if not s and not pattern:
           return True
       if not s or not pattern:
           return False

       first_term, rest_term = pattern[0], pattern[1:]
       if first_term in p:
           return matches(s[len(p[first_term]):], rest_term, p)
       for i in range(1, len(s) + 1):
           first_word, rest_word = s[:i], s[i:]
           p[first_term] = first_word
           if matches(rest_word, rest_term, p):
               return True
           del p[first_term]
       return False

    */

    public boolean stringPatternMatch(String s, String pattern) {
        Map<Character, String> map = new HashMap<>();
        return stringPatternMatchHelper(s, 0, pattern, 0, map);
    }

    private boolean stringPatternMatchHelper(String s, int ss, String pattern, int ps, Map<Character, String> map) {
        if(ss == s.length() && ps == pattern.length()) return true;
        if(ss == s.length() || ps == pattern.length()) return false;

        char term = pattern.charAt(ps);
        ps++;
        if(map.containsKey(term)){    //term pattern occurred previously
            int len = map.get(term).length();
            return len <= s.length() - ss && map.get(term).equals(s.substring(ss, ss + len))
                    && stringPatternMatchHelper(s, ss + len, pattern, ps, map);
        } else {   //term pattern character never occur before. Try every possible match
            for(int i = 1; i + ss <= s.length(); i++) {
                String firstWord = s.substring(ss, ss + i);
                map.put(term, firstWord);
                if(stringPatternMatchHelper(s, ss + i, pattern, ps, map)) return true;
                map.remove(term);
            }
        }
        return false;
    }



    //top k viewed photo

}
