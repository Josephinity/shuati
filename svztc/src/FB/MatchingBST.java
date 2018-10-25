package FB;
import java.util.Stack;
/**
 *
 * find first pair of mis-matching leaves (first pair as in in-order)
 * Given two pre-order traversal arrays of BSTs,
     5
  4     8
 2 4   6   9


 5324869
 5324879

 Can this be done on a general tree? Give reasons.
 */


public class MatchingBST {

    class Wrapper {
        int index;
        Integer c;

        Wrapper(int index, Integer c) {
            this.index = index;
            this.c = c;
        }
    }

    //get in-order sequence of BST from pre-order sequence
    public void recoverBST(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        while(index < preorder.length) {
            if(stack.isEmpty() || stack.peek() > preorder[index]) {
                stack.push(preorder[index]);
                index++;
            } else {
                System.out.print(stack.pop() + "  ");
            }
        }
        while(!stack.isEmpty()) System.out.print(stack.pop() + "  ");
    }



    public void firstNonMathcingLeaves(int[] o1, int[] o2) {
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();

        int c1 = 0, c2 = 0;
        for(int i = 0; i < o1.length; i++) {
            if(o1[c1] > o1[i]) {
                c1 = i;
            }
        }

        for(int i = 0; i < o2.length; i++) {
            if(o2[c2] > o2[i]) {
                c2 = i;
            }
        }

        Wrapper w1 = new Wrapper(0, c1), w2 = new Wrapper(0, c2);
        while(w1.c != null && w2.c != null && o1[w1.c] == o2[w2.c]) {
            w1 = findNext(o1, s1, w1.index);
            w2 = findNext(o2, s2, w2.index);
        }

        if(w1.c == null && w2.c == null) {
            System.out.println("same");
            return;
        }
        if(w1.c == null) {
            System.out.println(w1.c + " " + o2[w2.c]);
            return;
        }
        if(w2.c == null) {
            System.out.println(o1[w1.c] + " " + w2.c);
            return;
        } else {
            System.out.println(o1[w1.c] + " " + o2[w2.c]);
        }
    }

    public Wrapper findNext(int[] preorder, Stack<Integer> stack, int index) {
        while(index < preorder.length) {
            if (stack.isEmpty() || preorder[stack.peek()] > preorder[index]) {
                stack.push(index);
                index++;
            } else {
                return new Wrapper(index ,stack.pop());
            }
        }
        if(stack.isEmpty()) return new Wrapper(index, null);
        return new Wrapper(index, stack.pop());
    }

}
