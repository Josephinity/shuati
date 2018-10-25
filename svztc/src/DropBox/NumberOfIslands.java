package DropBox;

/**
 #of islands: matrix is read row by row（union find)




 */

import java.util.*;
public class NumberOfIslands {

    int rowLen;
    int rowCounter;
    int numberOfIslands;

    Integer[] islands;

    public NumberOfIslands(int rowLen) {
        this.rowLen = rowLen;
        islands = new Integer[(rowLen + 1) * 2];
    }

    public static void main(String[] args) {
        NumberOfIslands instance = new NumberOfIslands(10);
        System.out.println("number of islands " + instance.loadRow(new int[]{0,0,1,1,0,1,0,1,0,1}) + '\n');
        System.out.println("number of islands " + instance.loadRow(new int[]{1,1,1,1,1,1,0,1,1,1}) + '\n');
        System.out.println("number of islands " + instance.loadRow(new int[]{0,0,0,0,0,0,0,0,0,1}) + '\n');
        System.out.println("number of islands " + instance.loadRow(new int[]{1,0,0,1,0,1,0,0,0,1}) + '\n');
        System.out.println("number of islands " + instance.loadRow(new int[]{1,1,1,1,0,1,0,1,0,1}) + '\n');
    }

    public int loadRow(int[] row) {
        rowCounter++;
        for(int i = 1; i <= rowLen; i++) {
            if(row[i - 1] != 0) {
                int j = i + rowLen + 1;
                Integer top_root = find(i);
                Integer left_root = find(j - 1);

                //System.out.println("idx " + i );
                //System.out.println("top " + top_root + " left " + left_root);
                if(top_root == null && left_root == null) { //new island found
                    numberOfIslands++;
                    islands[j] = j;
                } else if(top_root == null) { //no top neighbor. join left neighbor
                    islands[j] = left_root;
                } else if(left_root == null){ //no left neighbor. join top neighbor
                    if(top_root <= rowLen) {  //top neighbor had no connection with new row
                        islands[top_root] = j;
                        islands[j] = j;
                    } else {                  //top neighbor's ancestor is in new row
                        islands[j] = top_root;
                    }
                } else {
                    if(top_root == left_root) { //no union since left neighbor and top neighbor were in same island
                        islands[j] = left_root;
                    } else {                    //union left and top neighbors
                        islands[top_root] = left_root;
                        islands[j] = left_root;
                        numberOfIslands--;
                    }
                }
            }
        }

        compression();

        for(int i = 0; i <= rowLen; i++) {
            int j = i + rowLen + 1;
            if(islands[j] != null) {
                islands[i] = islands[j] % (rowLen + 1);
                islands[j] = null;
            } else {
                islands[i] = null;
            }
        }
        System.out.println("union find array row " + rowCounter);
        for(int i = 0; i <= rowLen;i++) System.out.print(islands[i] + " ");
        System.out.println();

        return numberOfIslands;
    }

    private Integer find(int idx) {
        if(islands[idx] == null) return null;
        while(idx != islands[idx]) {
            idx = islands[idx];
        }
        return idx;
    }

    private void compression() {
        for(int i = rowLen + 1; i < islands.length; i++) {
            if(islands[i] != null) {
                islands[i] = find(i);
            }
        }
    }

}
