/**
 * Created by xiaobaby on 1/27/17.
 */
public class CheckLinkedListPalin {
    class Node {
        char c;
        Node next;
    }
    public boolean isPal(Node head) {
        if(head == null) return true;

        Node fast = head, slow = head;
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;

            if(fast != null) {
                fast = fast.next;
            }
        }
        if(slow == null) return true;

        Node half = reverse(slow);
        while(half != null) {
            if(head.c != half.c) {
                return false;
            }
            half = half.next;
            head = head.next;
        }
        return true;
    }

    private Node reverse(Node n) {
        return n;
    }
}
