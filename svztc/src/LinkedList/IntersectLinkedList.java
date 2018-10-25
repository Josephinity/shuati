package LinkedList;

/**

 */
public class IntersectLinkedList {
    //assuming lists aren't cyclic
    Node intersect(Node h1, Node h2) {
        if(h1 == null || h2 == null) return null;
        Node n1 = h1, n2 = h2;
        while(n1 != n2) {
            if(n1 == null) n1 = h2;
            if(n2 == null) n2 = h1;
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }
}
