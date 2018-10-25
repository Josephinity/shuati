/**
 Given sorted array of integers, return sorted array of squares of integers.
 */
public class sortSquares {
    //-8,-1,-1,0,2, 3,4,5,9,11,11
    //5,-1,-1,0,2,3,4,8
    //0,1,1,2,3,-7,-8,4,5,9,11,11

    public void mergeInPlace(int[] arr) {
        if(arr.length == 0) return;
        int mid = 0;
        while(mid < arr.length && arr[mid] < 0) {
            mid++;
        }

    }


    /*
    http://stackoverflow.com/questions/2126219/how-to-merge-two-sorted-integer-array-in-place-using-on-time-and-o1-space-co
    */

    public void merge(int A[], int m, int B[], int n) {

        while(m > 0 && n > 0){
            if(A[m-1] > B[n-1]){
                A[m+n-1] = A[m-1];
                m--;
            }else{
                A[m+n-1] = B[n-1];
                n--;
            }
        }

        while(n > 0){
            A[m+n-1] = B[n-1];
            n--;
        }
    }

}
