package FB;
import java.util.Random;
/**
 Modify an array to move all 0s to the right side.

 Follow-up: do this with minimum assignment operations.
 */
public class MoveZeroes {

    public static void main(String[] args) {
        Random rand = new Random();
        int len = rand.nextInt(11);
        int[] array = new int[len];
        for(int i = 0; i < len; i++) {
            array[i] = rand.nextBoolean() ? 0: rand.nextInt(10);
        }
        for(int n: array) System.out.print(n + " ");
        System.out.println();
        moveZeroes(array);
        for(int n: array) System.out.print(n + " ");
    }

    public static void moveZeroes(int[] array) {
        int zero = 0, num = array.length - 1;
        while(zero <= num) {
            if(array[zero] != 0) zero++;
            if(array[num] == 0) num--;
            if(zero <= num && array[zero] == 0 && array[num] != 0) {
                array[zero] = array[num];
                zero++;num--;
            }
        }
        while(zero < array.length) {
            array[zero] = 0;
            zero++;
        }
    }
}
