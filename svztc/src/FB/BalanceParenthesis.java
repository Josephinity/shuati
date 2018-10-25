package FB;

/**
 balance parentheses in a string

 e.g.：

 "(a)()" -> "(a)()"

 "((bc)" -> "(bc)"

 ")))a((" -> "a"

 "(a(b)" ->"(ab)" or "a(b)”

 balance here means making the parentheses valid and and paired. If there are multiple valid ways, just give one of them. (Stack)
 */

import java.util.Stack;


public class BalanceParenthesis {


    public static void main(String[] args) {

        System.out.println(balanceParenthesis("(a(b)"));

    }

    //first for-loop removes all invalid ')'. Second for-loop removes all invalid '('
    public static String balanceParenthesis(String s) {
        StringBuilder str = new StringBuilder(s);
        int left = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') {
                left++;
            } else if(str.charAt(i) == ')') {
                if(left > 0) {
                    left--;
                } else {
                    str.deleteCharAt(i--);
                }
            }
        }
        int right = 0;
        for(int i = str.length() - 1; i >= 0; i--) {
            if(str.charAt(i) == ')') {
                right++;
            } else if(str.charAt(i) == '('){
                if(right > 0) right--;
                else {
                    str.deleteCharAt(i);
                }
            }
        }
        return str.toString();
    }
}
