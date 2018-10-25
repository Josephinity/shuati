package Next; /**
 * Created by xiaobaby on 9/3/16.
 */
import java.util.Random;
public class RandomGenerator {
    static Random rand = new Random();

    public static boolean randomBoolean(double prob) {
        int num = rand.nextInt(100);
        if(num <= prob * 100) return true;
        return false;
    }

    public static int randomNumber(int lower, int upper) {
        return rand.nextInt(upper - lower) + lower;
    }
}
