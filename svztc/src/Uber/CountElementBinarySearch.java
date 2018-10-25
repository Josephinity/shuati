package Uber;

/**
find frequency of given number in a sorted array


 */
public class CountElementBinarySearch {

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{2}, 2));
    }

    static int binarySearch(int[] array, int num) {
        int numberStartsAt = -1, numberEndsAt = array.length - 1;

        int lo = 0, hi = array.length - 1;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(array[mid] < num) {
                lo = mid + 1;
            } else if(array[mid] > num) {
                hi = mid - 1;
                numberEndsAt = mid - 1;
            } else {
                if (mid == 0 || array[mid - 1] < num) {
                    numberStartsAt = mid;
                    break;
                }
                hi = mid - 1;
            }
        }
        if(numberStartsAt == -1) return 0;

        lo = numberStartsAt;
        hi = numberEndsAt;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(array[mid] > num) {
                hi = mid - 1;
            } else {
                if(mid == array.length - 1 || array[mid + 1] > num) {
                    return mid - numberStartsAt + 1;
                }
                lo = mid + 1;
            }
        }
        return 0;
    }


}
