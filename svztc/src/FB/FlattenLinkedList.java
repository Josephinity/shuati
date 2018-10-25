package FB;

/**
 Flatten a linked list.
 Each node in the list carries a piece of data and a pointer. The data could be in a normal data type such as an integer, or a pointer that points to another list node.

 The interviewer gave no specification on how the list node class was defined. You may look at each list node as if it has two pointers - one of them points to the next node; the other points to the ‘data’ which could be either a node or an integer. There also needs to be a function to tell you about the node data is actual data or a pointer.
 I solved the problem with recursion. During the process, any nodes that don’t carry an integer get removed.





 Solution

 Having come across a nested list node N. Recursively flatten the node into a plain list. Return the Head and Tail of the flattened list.
 Make the node before N point to Head. Make Tail.next point to N.next.
 */
public class FlattenLinkedList {

    public static void main(String args[]) {


        ListNode<Integer> head = new ListNode();

        head.list = new ListNode(5);

        ListNode<Integer> a = new ListNode();
        ListNode<Integer> b = new ListNode(6);
        ListNode<Integer> c = new ListNode();
        ListNode<Integer> d = new ListNode();
        ListNode<Integer> e = new ListNode(8);
        ListNode<Integer> f = new ListNode();
        ListNode<Integer> g = new ListNode(9);

        head.list.next = a;

        a.list = b;

        b.next = c;

        c.list = f;

        f.list = d;

        d.list = e;

        e.next = g;

        ListNode flat = flatten(head).head;

        while(flat != null) {

            System.out.println(flat.data);
            flat = flat.next;

        }

    }

    public static Wrapper flatten(ListNode head) {

        if(head == null) return null;

        ListNode dummy = new ListNode(0);

        dummy.next = head;

        ListNode node = dummy;

        while(node.next != null) {

            if(node.next.data != null) {

                node = node.next;

            } else {

                Wrapper flattened = flatten(node.next.list);

                if(flattened.tail != null) {

                    flattened.tail.next = node.next.next; //delete the node with nested list

                    node.next = flattened.head;

                    node = flattened.tail;

                } else {

                    node.next = node.next.next;

                }

            }

        }

        return new Wrapper(dummy.next, node);

    }

}

class ListNode<T> {

    ListNode next;
    T data; //when data is not null, this node carries actual data
    ListNode list; //when data is null, this node carries a nested list

    public ListNode(T data) {
        this.data = data;
    }

    public ListNode() {

    }

}

class Wrapper {
    ListNode head;
    ListNode tail;

    public Wrapper(ListNode h, ListNode t) {
        head = h;
        tail = t;
    }
}