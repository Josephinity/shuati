/**
Rotate a matrix by 90 degrees

 1 1 1 1
 1 1 1 1
 1 1 1 1
 1 1 1 1
 */

import java.util.Random;


public class RotateMatrix {

    public static void main(String args[]) {

        Random rand = new Random();

        int s = 6;

        int[][] m = new int[3][6];

        for(int i = 0; i < m.length; i++) {

            for (int j = 0; j < m[0].length; j++) {

                m[i][j] = rand.nextInt(90) + 10;

                System.out.print(m[i][j] + "  ");

            }

            System.out.println();

        }

        m = rotateRectangle(m);

        System.out.println();
        System.out.println();

        for(int i = 0; i < m.length; i++) {

            for(int j = 0; j < m[0].length; j++) {

                System.out.print(m[i][j] + "  ");

            }

            System.out.println();

        }

    }

    public static void rotate(int[][] m) { //counter clock wise

        int layer = m.length / 2;

        for(int level = 0; level < layer; level++) {

            int start = level, end = m.length - 1 - level;

            for(int x = start; x < end; x++) {//level = 0, start = 0, end = 3

                int tmp = m[x][level];

                m[x][level] = m[level][m.length - 1 - x];

                m[level][m.length - 1 - x] = m[m.length - 1 - x][m.length - 1 - level];

                m[m.length - 1 - x][m.length - 1 - level] = m[m.length - 1 - level][x];

                m[m.length - 1 - level][x] = tmp;

            }

        }

    }


    //rotate a rectangle matrix
    public static int[][] rotateRectangle(int[][] o) {//clockwise

        int m = o.length, n = o[0].length;

        int[][] ro = new int[n][m];

        for(int i = 0 ; i < m; i++) {

            for(int j = 0; j < n; j++) {

                ro[j][i] = o[m - 1 - i][j];

            }

        }

        return ro;

    }

}
