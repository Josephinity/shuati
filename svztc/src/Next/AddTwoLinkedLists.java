package Next;

/**
 calculate the sum of two linked lists without reversing a linked list.
 return a new linked list
 */
public class AddTwoLinkedLists {

    public static void main(String args[]) {

        AddTwoLinkedLists ad = new AddTwoLinkedLists();
        ListNode head1 = ad.create(999);
        ListNode head2 = ad.create(229);
        ListNode x = ad.add(head1, head2);
        ad.print(x);


    }

    public ListNode create(int number) {
        ListNode prev = null;
        ListNode curr;

        while(number > 0) {
            curr = new ListNode(number % 10);
            curr.next = prev;
            prev = curr;
            number /= 10;
        }

        return prev;
    }

    public void print(ListNode x) {
        while(x != null) {
            System.out.print(x.val + "  ");
            x = x.next;
        }
    }

    public ListNode add(ListNode head1, ListNode head2) {


        if(head1 == null || head2 == null) {
            return head1 == null? head2: head1;
        }

        int diff = 0; //get the difference in lengths of the two lists

        ListNode p1 = head1, p2 = head2;
        while(p1 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        ListNode longer = p1 == null? head2: head1;
        ListNode shorter = p1 == null? head1: head2;

        while(p1 != null) {
            p1 = p1.next;
            diff++;
        }

        while(p2 != null) {
            p2 = p2.next;
            diff++;
        }

        ListNode dump = new ListNode(0);  //create a dummy head for the result list
        ListNode curr= dump;

        while(diff > 0) {   //create the longer part of the longer list
            diff--;
            curr.next = new ListNode(longer.val);
            longer = longer.next;
            curr = curr.next;
        }


        while(longer != null) { //add the two lists
            curr.next = new ListNode(longer.val + shorter.val);
            curr = curr.next;
            longer = longer.next;
            shorter = shorter.next;
        }


        curr = dump;
        ListNode carry = dump;
        while(curr != null) {       //carry always points at the number smaller than 9
            if(curr.val < 9) {      //when a carry is found at current node, add 1 to carry and change anything after carry and before curr to 0
                carry = curr;
            } else if(curr.val > 9){
                carry.val++;
                carry = carry.next;
                while(carry != curr) {
                    carry.val = 0;
                    carry = carry.next;
                }
                curr.val %= 10;
            }
            curr = curr.next;
        }

        return dump.val == 0 ? dump.next: dump;

    }
}
