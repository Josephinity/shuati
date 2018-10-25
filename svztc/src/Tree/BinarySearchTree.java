package Tree;

/**
construct BST [not balanced]
 */
class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int v) {
        val = v;
    }
}
public class BinarySearchTree {

    BSTNode root;

    boolean search(int val) {
        BSTNode find = root;
        while(find != null) {
            if (find.val == val) return true;
            if (val > find.val) find = find.right;
            else find = find.left;
        }
        return false;
    }

    void insert(int val) {
        BSTNode n = new BSTNode(val);
        BSTNode parent = root;
        while(parent != null) {
            if(parent.val >= val) {
                if(parent.left == null) {
                    parent.left = n;
                    System.out.println("inserted " + n.val);
                    return;
                } else {
                    parent = parent.left;
                }
            } else {
                if(parent.right == null) {
                    parent.right = n;
                    System.out.println("inserted " + n.val);
                    return;
                } else {
                    parent = parent.right;
                }
            }
        }
        root = n;
        System.out.println("inserted " + n.val);
    }

    void delete(int val) {
        if(!search(val)) {
            System.out.println(val + " isn't in tree");
            return;
        }
        BSTNode dummy = new BSTNode(root.val + 1);
        dummy.left = root;
        BSTNode parent = dummy;
        BSTNode find = root;
        while(find != null && find.val != val) {
            parent = find;
            if (val > find.val) find = find.right;
            else find = find.left;
        }
        BSTNode at = find.left;
        if(find.left == null) {
            at = find.right;
        } else {
            BSTNode rightMost = find.left;
            while (rightMost.right != null) rightMost = rightMost.right;
            rightMost.right = find.right;
        }

        if(find.val > parent.val) {
            parent.right = at;
        }else {
            parent.left = at;
        }
        root = dummy.left;
        System.out.println("deleted " + find.val);
    }
}


