package Google;
import java.util.*;
/**
LC 556
 */
public class NextGreaterElement {

//    public static void main(String[] args) {
//        System.out.println(nextGreaterElement(324445321));
//    }
    public static int nextGreaterElement(int n) {
        Deque<Integer> digits = new ArrayDeque<>();
        digits.addLast(n % 10);
        n /= 10;
        while(n > 0) {
            int digit = n % 10;
            n /= 10;
            if(digit >= digits.peekLast()) {
                digits.addLast(digit);
            } else {
                int size = digits.size();
                int tail = 0;
                while(!digits.isEmpty()) {
                    if(digits.peekFirst() <= digit) {
                        tail *= 10;
                        tail += digits.pollFirst();
                    } else {
                        tail *= 10;
                        tail += digit;
                        n *= 10;
                        n += digits.pollFirst();
                        while(!digits.isEmpty()) {
                            tail *= 10;
                            tail += digits.pollFirst();
                        }
                    }
                }
                long res = (long)Math.pow(10,size) * n + tail;
                if(res > Integer.MAX_VALUE) return -1;
                return (int)res;
            }
        }
        return -1;
    }
}
