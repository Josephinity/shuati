package LinkedList;

/** Adobe
 Return the value of the item k away from the end of a  LinkedList
 */
public class KAwayFromEnd {
    Node kFromEnd(Node head, int k) {
        Node fast = head;
        k--;
        while(fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        if(fast == null) return null;
        Node slow = head;
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
