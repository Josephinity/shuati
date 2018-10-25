package FB;

/**
 题目是decode ways
 但是input String可以包含 *

 比如 1*2，可以有  102 （1），112 （3）， 122（3），132(2), 142(2), 152(2), 162(2), 172(2) 182(2) 192 (2)  一共 21种解法。

 返回多少解, 刚开始想还挺简单的，写起来并不简单，比如 1**1 ，两个*都要判断。。
 */
public class DecodeWays {

    public static int decodeWays(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') dp[i] = 0;
            else if (s.charAt(i) != '*') {
                dp[i] += dp[i + 1];
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) != '*') {
                        int num = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                        if (num >= 10 && num <= 26) dp[i] += dp[i + 2];
                    }
                    else if (s.charAt(i) == '1') dp[i] += dp[i + 2] * 10;
                    else if (s.charAt(i) == '2') dp[i] += dp[i + 2] * 7;

                }
            } else {
                dp[i] += dp[i + 1] * 9;
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) != '*') {
                        if (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '6') dp[i] += dp[i + 2] * 2;
                        else dp[i] += dp[i + 2];
                    } else {
                        dp[i] += dp[i + 2] * 17;
                    }
                }
            }

        }
        return dp[0];
    }
}
