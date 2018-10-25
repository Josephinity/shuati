package Google;

/**
 Round4
 Starting from num = 0, add 2^i (where i can be any non-negative integer) to num until num == N. Print all paths of how num turns from 0 to N.
 For example if N = 4,
 Paths printed are [0,1,2,3,4], [0,1,2,4], [0,1,3,4], [0,2,4], [0,2,3,4], [0,4].
 [0,2,4] is made from 0 + 2^1 + 2^1. [0,1,3,4] from 0 + 2^0 + 2^1 + 2^0
 */
import java.util.List;
import java.util.ArrayList;

public class PowerOf2Paths {


    public void powerOf2Paths(int N) {
        List<Integer> path = new ArrayList<>();
        path.add(0);
        powerOf2PathsHelper(4, 0, path);
    }

    public void powerOf2PathsHelper(int N, int num, List<Integer> path) {
        if(num == N) {
            System.out.println(path);
        }

        for(int i = 0; num + (1 << i) <= N; i++) {
            int sum = num + (1 << i);
            path.add(sum);
            powerOf2PathsHelper(N, sum, path);
            path.remove(path.size() - 1);
        }
    }

}
