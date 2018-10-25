package Google;

/**
number of lonely pixels in picture.
 lonely - the only pixel of its kind in entire row and col.
 */
import java.util.*;
public class LonelyPixel {

    public static void main(String[] args) {
        System.out.println(lonelyPixelCount(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        }));

    }

    static int lonelyPixelCount(int[][] picture) {
        int m = picture.length, n = picture[0].length;
        //First traversal sum up the count of black pixels by column
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                picture[i][j] += picture[i - 1][j];
            }
        }
        int result = 0;
        //Then traverse row by row from the bottom, count the black pixels in current row.
        //If there is only 1 black pixel in current row, verify whether it is also the only in the column.
        for(int i = m - 1; i >= 0; i--) {
            int pixel_count_in_row = 0;
            boolean only_pixel_in_column = false;
            for(int j = n - 1; j >= 0; j--) {
                if(picture[i][j] > 0 && (i == 0 || picture[i - 1][j] + 1 == picture[i][j])) {	//verify if current cell number is a black pixel, by method in blue text above
                    pixel_count_in_row++;
                    if((i < m - 1 && picture[i + 1][j] == 1) || (i == m - 1 && picture[i][j] == 1)) {
                        only_pixel_in_column = true;
                    }
                }
                if(i < m - 1) {
                    //overwrite current cell with the number below it
                    picture[i][j] = picture[i + 1][j];
                }
            }
            if(pixel_count_in_row == 1 && only_pixel_in_column) {
                result++;
            }
        }
        return result;
    }

}
