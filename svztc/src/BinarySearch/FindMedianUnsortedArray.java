package BinarySearch;

import java.util.*;
/**
 * Find Median in Unsorted Array
 */
public class FindMedianUnsortedArray {
    public static void main(String args[]) {
        Random rand = new Random();
        int[] a = new int[1 + rand.nextInt(50)];
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(100);
            System.out.print(a[i] + " ");
        }

        System.out.println("length = " + a.length);
        System.out.println(median(a));
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        if (a.length % 2 == 0) System.out.println((1.0 * a[a.length / 2 - 1] + a[a.length / 2]) / 2);
        else System.out.println(a[a.length / 2]);
    }


    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    //Priority Queue
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        Queue<Integer> pq = new PriorityQueue<Integer>(k);
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                pq.add(nums[i]);
            } else {
                if (nums[i] > pq.peek()) {
                    pq.poll();
                    pq.add(nums[i]);
                }
            }
        }
        return pq.peek();
    }

    public static double median(int[] a) {
        int len = a.length;
        if(len % 2 == 1) {
            return topK(a, len / 2);
        } else {
            return (topK(a, len / 2 - 1)+ topK(a, len / 2)) * .5;
        }
    }

    private static int topK(int[] a, int k) {
        int l = 0, r = a.length - 1;
        while(l <= r) {
            int pivot = a[l];
            int i = l + 1, j = r;
            while(i <= j) {
                if(a[i] <= pivot) i++;
                else if(a[j] > pivot) j--;
                else {
                    swap(a, i, j);
                    i++;
                    j--;
                }
            }
            if(j == k) return pivot;
            else if(j < k) l = i;
            else {
                swap(a, l, j);
                r = j - 1;
            }
        }
        return a[r];
    }


}
