package FB;

/**
 Clone a linked list with next and pointer that leads to an arbitrary node in the linked list
 Follow-up: constant extra space

 Naive Method: O(n) time, O(n) space using a hashmap, same as cloning a graph.
 Optimal Method: O(n) time, O(1) extra space.

 */

public class CopyLinkedListWithRandomPointer {
    class ListNode {
        int value;
        ListNode next;
        ListNode arbitrary;

        public ListNode(int val) {
            value = val;
        }
    }

    public ListNode clone(ListNode head) {
        ListNode node = head;
        //First traversal: clone every node and insert the clone between node and node.next
        while(node != null) {
            ListNode copy = new ListNode(node.value);
            copy.next = node.next;
            node.next = copy;
            node = copy.next;
        }
        //Now the list becomes node1->copyOf(node1)->node2->copyOf(node2)...

        node = head;
        //Second traversal: assign nodes to the arbitrary pointers of the cloned nodes
        //If a node X has an arbitrary pointer to node Y, then clone of X has an arbitrary pointer towards Y.next
        //since Y.next is the clone of Y
        while(node != null) {
            ListNode copy = node.next;
            if(node.arbitrary != null) {
                copy.arbitrary = node.arbitrary.next;
            }
            node = copy.next;
        }

        node = head;
        ListNode cloneDummy = new ListNode(0), cloned = cloneDummy;
        //Third traversal: split clones off the original list
        while(node != null) {
            cloned.next = node.next;
            node.next = node.next.next;
            node = node.next;
            cloned = cloned.next;
        }
        return cloneDummy.next;
    }
}
