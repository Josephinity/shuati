package LinkedList;

/**
 Given a singly linked list  1->2->3->4->5->6->7
 Change it to 1->7->2->6->3->5->4
 */
public class LinkedListReorder {

    public Next.ListNode reorder(Next.ListNode head) {
        Next.ListNode slow = head, fast = head;
        while(slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next;
            if(fast == null) break;
            fast = fast.next;
        }
        if(slow == null) return head;
        Next.ListNode reversed = new Next.ListNode(0), n;
        while(slow != null) {
            n = slow;
            slow = slow.next;//slow is head of remaining second half of list
            n.next = reversed.next;
            reversed.next = n; // n is current element
        }
        n = reversed.next;
        fast = head;    //fast is the iterator for first half of list
        while(n != null) {
            slow = n.next;  //slow is head of remaining second half of list
            n.next = fast.next;     //n is iterator for second half of list
            fast.next = n;
            fast = fast.next.next;
            n = slow;
        }
        fast.next = null;
        return head;
    }

}
