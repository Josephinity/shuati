package BinarySearch;

/**
 find the median of two sorted arrays a1, a2

 Solution:

 Find median of a1, L1 and R1 (if a1.length is odd, then L1 == R1)
 Find median of a2, L2 and R2

 if(L1 > R2) {
    //can discard right half of a1 or left half of a2, which ever is shorter
    if([R1, a1.length] < [0, L2]) discard [R1,a1.length] in a1 and discard [a2.length - [R1, a1.length]] in a2

    else if(L2 > R1)
    //discard left half of a1 or right half of a2

    else //L2 < R1 and R2 > L1

    median found: is the median of the 4 number


 }
 */
public class FindMedianInTwoArrays {

    public static void main(String[] args) {

        FindMedianInTwoArrays f = new FindMedianInTwoArrays();

        int[] a1 = new int[]{1, 3,5,7,9,12};

        int[] a2 = new int[]{2,8,10,11};


        System.out.println(f.findMedianSortedArrays2(a1, a2));

    }


    public double findMedianSortedArrays(int[] a1,int[] a2) {

        if(a2.length < a1.length) return findMedianSortedArrays(a2, a1);

        int s1 = 0, s2 = 0, e1 = a1.length - 1, e2 = a2.length - 1;

        while(s1 <= e1 || s2 <= e2) {

            int mid1 = (s1 + e1) / 2, mid2 = (s2 + e2) / 2;

            int L1 = e1 < s1 ? Integer.MIN_VALUE : a1[(s1 + e1) / 2];
            int L2 = e2 < s2 ? Integer.MIN_VALUE : a2[(s2 + e2) / 2];

            int R1 = e1 < s1 ? Integer.MAX_VALUE : a1[(s1 + e1 + 1) / 2];
            int R2 = e2 < s2 ? Integer.MAX_VALUE : a2[(s2 + e2 + 1) / 2];

            System.out.println(L1+" "+R1+" "+L2+" "+R2);

            if(L1 > R2) {

                s2 = s2 + (e1 - (s1 + e1)/2 + 1); //(s1 + e1) / 2 is index of L1
                e1 = (s1 + e1)/ 2 - 1;

            } else if(L2 > R1){

                e2 = e2 - ((s1 + e1) / 2 + 1 - s1); //(s1 + e1) / 2 is index of L1
                s1 = (s1 + e1) / 2 + 1;

            } else {

                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;

            }

            System.out.println("s1="+s1+" e1="+e1+" s2="+s2+" e2="+e2);

        }
        return -1;

    }




    //leetcode
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if(nums1.length < nums2.length) return findMedianSortedArrays2(nums2, nums1);
        //m > n
        int m = nums1.length, n = nums2.length;
        if(n == 0) return (nums1[m / 2] + nums1[(m - 1) / 2]) / 2.0;
        int lo = 0, hi = 2 * n;
        while(lo <= hi) {
            System.out.println("lo "+lo+", hi"+ hi);
            int mid2 = (lo + hi) / 2;   //mid2 is mid of shorter array
            int mid1 = m + n - mid2;    //mid1 is mid of longer array
            System.out.println(mid2 + " " + mid1);
            int L1 = (mid1 == 0) ? Integer.MIN_VALUE: nums1[(mid1 - 1) / 2]; //L1=2
            int L2 = (mid2 == 0) ? Integer.MIN_VALUE: nums2[(mid2 - 1) / 2];//L2=1
            int R1 = (mid1 == 2 * m) ? Integer.MAX_VALUE: nums1[mid1 / 2];//R1 = 3
            int R2 = (mid2 == 2 * n) ? Integer.MAX_VALUE: nums2[mid2 / 2];//R2 = 2
            System.out.println(L1+", "+R1+" "+ L2 +", "+ R2);

            if(L1 > R2) lo = mid2 + 1;
            else if(R1 < L2) hi = mid2 - 1;
            else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
        }
        return -1;
    }
}
