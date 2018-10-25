package DP;

/**
 Leetcode 91
 */
public class DecodeWays {

    public int decodeWays(String code) {

        if(code.isEmpty() || code.charAt(0) == '0') return 0;


        int prev = 1, cur = 1;

        for(int i = 1; i < code.length(); i++) {

            if(code.charAt(i) == '0') {

                if(code.charAt(i- 1) == '0' || code.charAt(i- 1) > '2') return 0;

                cur = prev;   //when current number is 10 or 20

            } else {

                if(code.charAt(i - 1) == '1' ||   //when current number is between 11 - 26 (exclude 20)
                        (code.charAt(i - 1) == '2' && code.charAt(i) <= '6')) {

                    int next = prev + cur;
                    prev =cur;
                    cur = next;

                } else {

                    prev = cur;

                }

            }

        }

        return cur;

    }

}
