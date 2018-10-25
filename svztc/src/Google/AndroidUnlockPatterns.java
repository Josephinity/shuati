package Google;

/**
 http://www.cnblogs.com/grandyang/p/5541012.html

 Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

 Rules for a valid pattern:

 Each pattern must connect at least m keys and at most n keys.
 All the keys must be distinct.
 If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 The order of keys used matters.

 | 1 | 2 | 3 |
 | 4 | 5 | 6 |
 | 7 | 8 | 9 |

2, 2, 0 ,1
 1, 1
 1, 2


 */
import java.util.*;
public class    AndroidUnlockPatterns {

    public static void main(String args[]) {
        AndroidUnlockPatterns aup = new AndroidUnlockPatterns();
        System.out.println(aup.unlockPatterns(3,3));
    }

    public int unlockPatterns(int m, int n) {
        int count = 0;
        boolean[] taken = new boolean[10];
        taken[0] = true;
        count += patternCount(5, taken, m, n, 0, 0, new ArrayList<>());
        count += 4 * patternCount(1, taken, m, n, 0, 0, new ArrayList<>());
        count += 4 * patternCount(2, taken, m, n, 0, 0, new ArrayList<>());
        return count;
    }

    public int patternCount(int key, boolean[] taken, int m, int n, int len, int count, List<Integer> code) {
        code.add(key);
        taken[key] = true;
        len++;
        if (len >= m) {
            System.out.println(code);
            count++;
        }
        if (len == n) {
            taken[key] = false;
            code.remove(code.size() - 1);
            return count;
        }

        for (int k = 1; k <= 9; k++) {
            if(!taken[k]) {
                int[] passThrough = passKeys(key, k);
                if(taken[passThrough[0]] && taken[passThrough[1]]) {
                    count = patternCount(k, taken, m, n, len, count, code);
                }
            }
        }
        code.remove(code.size() - 1);
        taken[key] = false;
        return count;
    }

    private int[] passKeys(int k, int p) {
        k--;
        p--;
        int kx = k / 3, ky = k % 3;
        int px = p / 3, py = p % 3;

        if (Math.abs(kx - px) <= 1 && Math.abs(ky - py) <= 1) {   // adjacent horizontally, vertically or diagonal
            return new int[]{0, 0};
        } else if (Math.abs(kx - px) == 1 || Math.abs(ky - py) == 1) {  //horse jump in chess
            int x1 = (kx + px) / 2, x2 = (kx + px + 1) / 2;
            int y1 = (ky + py) / 2, y2 = (ky + py + 1) / 2;
            return new int[]{x1 * 3 + y1 + 1, x2 * 3 + y2 + 1};
        } else {                                                //long jump horizontally, vertically or diagonal
            return new int[]{(k + p) / 2 + 1, 0};//1
        }
    }
}