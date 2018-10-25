package DropBox;

/**
 Grid Illumination: Given an NxN grid with an array of lamp coordinates.
 Each lamp provides illumination to every square on their x axis,
 every square on their y axis, and every square that lies in their diagonal
 (think of a Queen in chess).
 Given an array of query coordinates,
 determine whether that point is illuminated or not. The catch is when checking a query all lamps adjacent to, or on,…

 */
import java.util.Set;
import java.util.HashSet;
//O(N) time and space for processing the board and lamps
//O(1) for finding if a cell is illuminated
public class GridIllumination {

    int N; //board size
    Set<Integer> illuminated_x = new HashSet<>();
    Set<Integer> illuminated_y = new HashSet<>();
    Set<Integer> illuminated_diag0 = new HashSet();
    Set<Integer> illuminated_diag1 = new HashSet();


    //@param lamps - a list of (x,y) locations of lamps
    public GridIllumination(int N, int[][] lamps) {
        this.N = N;
        for(int[] lamp: lamps) { //this lamp illuminates 4 lines of cells
            illuminated_x.add(lamp[0]);               //the entire column
            illuminated_y.add(lamp[1]);               //the entire row
            illuminated_diag0.add(lamp[1] - lamp[0]); //diagonal line with a slope of 1
            illuminated_diag1.add(lamp[0] + lamp[1]); //diagonal lines with a slope of -1
        }
    }

    public boolean is_illuminated(int x, int y) {
        if(illuminated_x.contains(x) ||
            illuminated_y.contains(y) ||
            illuminated_diag0.contains(y - x) ||
            illuminated_diag1.contains(x + y)) {
                return true;
        }
        return false;
    }
}
