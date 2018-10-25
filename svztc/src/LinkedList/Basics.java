package LinkedList;

/**

 */
public class Basics {

    //weave two linked lists into one
    public Node weave(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node n = dummy;
        while(l1 != null && l2 != null) {
            n.next = l1;
            l1 = l1.next;
            n = n.next;
            n.next = l2;
            l2 = l2.next;
            n = n.next;
        }
        if(l1 == null) n.next = l2;
        if(l2 == null) n.next = l1;
        return dummy.next;
    }
}
