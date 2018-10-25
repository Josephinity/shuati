package Google;

import java.util.ArrayList;
/**
 */
public class Main {
    public static void main(String[] args) {

        SweepingRobot sr = new SweepingRobot(new int[][]{
                {1 ,0, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0},
        }, 1, 1);

        sr.sweep();
    }

}
