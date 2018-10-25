package Graph;

/**
 * Created by xiaobaby on 1/21/17.
 */
class ListNode {
    ListNode next;
}
public class CycleLinkedList {
    public boolean hasCycle(ListNode head) {
        if(head==null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while(runner.next!=null && runner.next.next!=null) {
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner) return true;
        }
        return false;
    }
}
