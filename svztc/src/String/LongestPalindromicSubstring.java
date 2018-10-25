package String;

/**
Manacher's


 012345678910
 abaxabaxabb

 13171915111

case1: copy the mirror pal size on left
 the center pal totally contains current pal

 case2: stop searching
 current pal expands till end of input


 case3: check for the real length of current pal, current pal becomes center
 the center pal totally contains current pal and left mirror of current pal is a prefix of center pal

 or

 center pal doesn't cover till current


 case4: current pal length is len(leftmirror - the part beyond)
 the left mirror pal expands out of center pal
 */


public class LongestPalindromicSubstring {


    public static void main(String args[]) {

        LongestPalindromicSubstring m = new LongestPalindromicSubstring();

        System.out.println(m.longestPalindromicSubstring("aaaaaaaaaaaaaaaaaaaaaaa"));

    }

    public String longestPalindromicSubstring(String s) {
        StringBuilder str = new StringBuilder('$');
        for(char c: s.toCharArray()) str.append(c + "$");
        s = str.toString();
        int[] LPS = new int[s.length()];
        int center = 0, centerLeft = 0, centerRight = 0;
        LPS[0] = 1;
        int current = 1;
        while(current < s.length()) {
            int mirror = center - (current - center);
            if(centerRight < current || mirror - LPS[mirror] / 2 == centerLeft) { //case3
                int len = centerRight < current ? 0 : LPS[mirror] / 2;
                while(current - len - 1 >= 0 &&
                        current + len + 1 < s.length() &&
                                s.charAt(current - len - 1) == s.charAt(current + len + 1)) {
                    len++;
                }
                LPS[current] = len * 2 + 1;
                if(centerRight < current || len > LPS[mirror] / 2) {
                    center = current;
                    centerLeft = current - len;
                    centerRight = current + len;
                }
                if(len + current == s.length() - 1) break; //case 2
            } else if(mirror - LPS[mirror] / 2 > centerLeft) {//case 1
                LPS[current] = LPS[mirror];
            } else { // case 4
                LPS[current] = (centerRight - current) * 2 + 1;
            }
            current++;
        }
        int max = 0, lps = 0;
        for(int i = 0; i < LPS.length; i++) {
            System.out.print(LPS[i] + " ");
            if(LPS[i] > max) {
                max = LPS[i];
                lps = i;
            }
        }
        str = new StringBuilder();
        for(int i = lps - max / 2; i <= lps + max / 2; i++) {
            if(s.charAt(i) != '$') str.append(s.charAt(i));
        }
        return str.toString();
    }




    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        s = process(s);
        int[] P = new int[s.length()];
        int mid = 0;
        int max = 0;
        for (int i = 1; i < s.length() - 1; i++) {
            P[i] = i < max ? Math.min(P[mid - (i - mid)], max - i) : 0;
            while (s.charAt(i - P[i] - 1) == s.charAt(i + P[i] + 1)) P[i]++;
            if (i + P[i] > max) {
                mid = i;
                max = P[i];
            }
        }
        int maxLen = 0;
        mid = 0;
        for (int i = 0; i < s.length(); i++) {
            if (P[i] > maxLen) {
                mid = i;
                maxLen = P[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = mid - maxLen; i <= mid + maxLen; i++) {
            if (s.charAt(i) != '#') sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    private String process(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('^');
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append("#$");
        return sb.toString();
    }



}
