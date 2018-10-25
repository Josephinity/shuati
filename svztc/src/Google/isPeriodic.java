package Google;

/**
 bool isperiodic(string s, int p), p是重复的字符串的长度
 eg. isperiodic(“ABABAB”, 2) = true.


 Given string S, find if it's periodic.
 Periodic indicates S = nP.
 e.g.
 S = "ababab", then n = 3, and P = "ab"
 S = "xxxxxx", then n = 1, and P = "x"
 S = "aabbaaabba", then n = 2, and P = "aabba"

 follow up:
 Given string S, find out the P (repetitive pattern) of S.


 给你一个s，不给p，判断它是不是periodic。枚举p？如何能更快？用factor？没有卵用的感觉（因为我写的isperiodic第一行就会用mod判断s.size()是否能整除p）

 follow up2: 那如果每次给你一个char（s的输入流的感觉）如何判断是不是periodic
 */
public class isPeriodic  {

    boolean isPeriod(String s) { //O(n)
        StringBuilder str = new StringBuilder(s + s);
        str.deleteCharAt(0);
        str.deleteCharAt(str.length() - 1);
        return strStr(str.toString(), s); //KMP strStr(T, S) to find if T has S in it.
    }

    //follow-up
    //Following method looks for the repeating pattern in string
    private static String getPeriod(String string) { // O(n * n)
        //for every possible period size i, check if it's valid
        for (int i = 1; i <= string.length() / 2; i++) {
            if (string.length() % i == 0) {
                String period = string.substring(0, i);
                int j = i;
                while(j + i < string.length()) {
                    if (period.equals(string.substring(j, j + i))) {
                        j = j + i;
                        if(j == string.length()) { //period valid through entire string
                            return period;
                        }
                    } else {
                        break;
                    }
                }
            }

        }
        return null; //string is not periodic
    }



    //assume needle and haystack are non-empty strings
    public static boolean strStr(String haystack, String needle) {

        int[] next = table(needle);
        int i = 0, j = 0;

        while(i <= haystack.length() - needle.length()) {

            if(haystack.charAt(i + j) == needle.charAt(j)) {

                j++;

                if(j == needle.length()) {

                    System.out.println(i);
                    return true;

                    //i = i + j - next[j - 1];
                    //j = 0;

                }

            } else {

                if(j == 0) i++;

                else {

                    i = i + j - next[j - 1];
                    j = 0;

                }

            }


        }
        return false;
    }

    //aabaabaaa

    //010123452

    static int[] table(String s) {

        int[] t = new int[s.length()];

        int i = 1, len = 0;

        while(i < s.length()) {

            if(s.charAt(i) == s.charAt(len)) {

                len++;

                t[i] = len;

                i++;

            } else {

                if(len == 0) {

                    i++; //t[i] = 0;

                } else {

                    len = t[len - 1 ];

                }

            }

        }

        return t;
    }

}
