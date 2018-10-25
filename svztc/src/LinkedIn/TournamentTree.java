package LinkedIn;

/**
LinkedIn
 No Offer
 Positive Experience
 Difficult Interview
 Application
 I applied through a recruiter. The process took 3 weeks. I interviewed at LinkedIn (Sunnyvale, CA) in April 2017.

 Interview
 HR Phone screen where I talked to the recruiter about my background, nothing special. Then Followed by Phone Technical Interview. Didn't get to pass this one. I regret not asking for more clarification questions about the second problem.

 Interview Questions
 1. You have n doors in a row that are all initially closed. You make n passes by the doors starting with the first door every time. The first time through you visit every door and toggle the door (if the door is closed, you open it, if its open, you close it). the second time you only visit every 2nd…
 2 Answers
 2.
 /**
 * A tournament tree is a binary tree
 * where the parent is the minimum of the two children.
 * Given a tournament tree find the second minimum value in the tree.
 * A node in the tree will always have 2 or 0 children.
 * Also all leaves will have distinct and unique values.
 * 2
 * / \
 * 2 3
 * / \ | \
 * 4 2 5 3
 *
 * In this given tree the answer is 3.
 */
class Node {
    Integer value;
    Node left, right;
    Node(Integer value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
//If the tree has no 2nd min value, return -1
public class TournamentTree {
    int secondMinValue(Node root) {
        if(root == null || root.left == null) {
            return -1;
        }
        if(root.left.value == root.value) {
            int leftSecondMin = secondMinValue(root.left);
            if(leftSecondMin == -1 || leftSecondMin > root.right.value) {
                return root.right.value;
            } else return leftSecondMin;
        } else {
            int rightSecondMin = secondMinValue(root.right);
            if(rightSecondMin == -1 || rightSecondMin > root.left.value) {
                return root.left.value;
            } else return rightSecondMin;
        }
    }
}
