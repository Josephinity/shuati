package String;

/**
find needle in haystack
 */
public class strStr {

    public static void main(String[] args) {



        strStr("abcdabce", "abce");

    }

    //assume needle and haystack are non-empty strings
    public static void strStr(String haystack, String needle) {

        int[] next = table(needle);
        int i = 0, j = 0;

        while(i <= haystack.length() - needle.length()) {

            if(haystack.charAt(i + j) == needle.charAt(j)) {

                j++;

                if(j == needle.length()) {

                    System.out.println(i);

                    i = i + j - next[j - 1];
                    j = 0;

                }

            } else {

                if(j == 0) i++;

                else {

                    i = i + j - next[j - 1];
                    j = 0;

                }

            }


        }
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
