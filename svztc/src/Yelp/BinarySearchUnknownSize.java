package Yelp;

/**
 *
 * Search for a target in a sorted array with unknown length.
 给一次排好序的数组和一个target，让你找target的index，前提是要求是不知道数组长度，并且也不能提前遍历数组
 */
public class BinarySearchUnknownSize {


    public static void main(String args[]) {

        BinarySearchUnknownSize bs = new BinarySearchUnknownSize();
        System.out.println(bs.binarySearch(new int[]{1,2,3,5,6,7,8,9}, 5));





    }

    public int binarySearch(int[] array, int target) {

        int lo = 0, hi = 1;

        while(lo <= hi) {

            int mid = (lo + hi) / 2;

            try  {

                if(array[mid] == target) {

                    return mid;

                } else if(array[mid] > target){

                    hi = mid - 1;

                } else {

                    if(array[hi] < target) hi *= 2;

                    lo = mid + 1;

                }

            } catch(IndexOutOfBoundsException e) {

                hi = mid - 1;

            }

        }

        return -1;

    }


}
