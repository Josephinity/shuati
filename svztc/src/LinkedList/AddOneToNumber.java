package LinkedList;

/**
 Add 1 to the integer represented by a linked list with O(n) time and O(1) space.
 */

public class AddOneToNumber {
    Node add1(Node num) {
        Node dummy = new Node(0);
        dummy.next = num;
        Node lastNot9 = dummy, iter = num;
        while (iter != null) {
            if (iter.val < 9) {
                lastNot9 = iter;
            }
            iter = iter.next;
        }
        lastNot9.val += 1;
        iter = lastNot9.next;
        while (iter != null) {
            iter.val = 0;
            iter = iter.next;
        }
        return dummy.val == 0 ? dummy.next : dummy;
    }
}
