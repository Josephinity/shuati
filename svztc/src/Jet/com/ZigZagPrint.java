package Jet.com;

/**
Print a matrix in diagonal zigzag traversal.
For matrix
  1  2  3  4  5  6
  7  8  9 10 11 12
 13 14 15 16 17 18
 19 20 21 22 23 24

 Print
 1 2 7 13 8 3 4 9 14 19 20 15 10 5 6 11 16 21 22 17 12 18 23 24



 */
public class ZigZagPrint {

/*
                 North
            1  2  3  4  5  6
      West  7  8  9 10 11 12   East
            13 14 15 16 17 18
            19 20 21 22 23 24
                 South
*/

    public void print(int[][] matrix) {
        int len = matrix.length, width = matrix[0].length;
        int x = 0, y = 0;
        boolean goingUp = true;
        while(x < len && y < width) {
            System.out.print(matrix[x][y] + " ");
            if(goingUp) {
                if(y > 0 && x < len - 1) {//Proceed to top right cell if border isn't met
                    y -= 1;
                    x += 1;
                } else if(x == len - 1){ // Hit East border. Move to lower cell and start going down
                    y += 1;
                    goingUp = false;
                } else {                // Hit North border. Move to right cell and start going down
                    x += 1;
                    goingUp = false;
                }
            } else {
                if(y < width - 1 && x > 0) { //Proceed to bottom left cell if border if border isn't met
                    y += 1;
                    x -= 1;
                } else if(y == width - 1) {  //Hit South border. Move to the right neighbor and start going up
                    x += 1;
                    goingUp = true;
                } else {                    //Hit West border. Move to the lower neighbor and start going up
                    y += 1;
                    goingUp = true;
                }
            }
        }
    }
}
