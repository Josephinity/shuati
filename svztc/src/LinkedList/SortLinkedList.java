package LinkedList;
import Next.ListNode;

import java.util.*;
/**
 Quick select on linkedlist
 */
public class SortLinkedList {


    public static void main(String[] args) {
        Random rand = new Random();
        ListNode head = new ListNode(rand.nextInt(100));
        ListNode n = head;
        System.out.print(n.val + "  ");
        int c = rand.nextInt(25);
        for(int i = 0; i < c; i++) {
            n.next = new ListNode(rand.nextInt(100));
            n = n.next;
            System.out.print(n.val + "   ");
        }
        System.out.println();
        head = sort(head);
        while(head != null) {
            System.out.print(head.val + "  ");
            head = head.next;
        }

//        System.out.println(", n = " + (c + 1));
//        int x = (1 + rand.nextInt(c));
//        System.out.println("the top " + x + "th element is " + topK(head, x));
//
//        sort(head);

    }

    public static int topK(ListNode head, int k) {
        ListNode small = new ListNode(0), large = new ListNode(0);

        int count = 0;

        while(head != null) {
            int pivot = head.val;
            head = head.next;
            ListNode s = small, l = large;
            while(head != null) {
                if(head.val > pivot) {
                    l.next = head;
                    l = l.next;
                    count++;
                } else {
                    s.next = head;
                    s = s.next;
                }
                head = head.next;
            }
            if(count == k - 1) {
                return pivot;
            } else if(count > k - 1) {
                head = large.next;
            } else {
                head = small.next;
                k = k - count - 1;
            }
            count = 0;
        }
        return -1;
    }

    public static ListNode sort(ListNode head) {
        if(head == null) return null;
        ListNode pivot = head;
        head = head.next;
        ListNode large = null, small = null;
        while(head != null) {
            ListNode curr = head;
            head = head.next;
            if(curr.val > pivot.val) {
                curr.next = large;
                large = curr;
            } else {
                curr.next = small;
                small = curr;
            }
        }
        large = sort(large);
        small = sort(small);
        pivot.next = small;
        if(large == null) return pivot;
        ListNode l = large;
        while(l.next != null) l = l.next;
        l.next = pivot;
        return large;
    }
}
