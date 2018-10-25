package Tree;

import java.util.List;
import java.util.ArrayList;

/**
 Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 */

// class TreeNode(object):
//        #     def __init__(self, x):
//        #         self.val = x
//        #         self.left = None
//        #         self.right = None



public class NBST {
        class TreeNode {
            int val;
            TreeNode left, right;
            TreeNode(int x) {val = x;}
        }



        public static void main(String args[]) {
            NBST x = new NBST();
            List<TreeNode> trees = x.generateTrees(3);
            System.out.println("ans");
            for(TreeNode tree: trees) {
                System.out.println(x.printTree(tree));
            }
        }

        public List<TreeNode> generateTrees(int n) {
            List<TreeNode> res = new ArrayList<>();
            res.add(null);
            for(; n > 0; n--) {
                List<TreeNode> next = new ArrayList<>();
                for(TreeNode node: res) {
                    TreeNode root = new TreeNode(n); // the case when Tree.Node(n) is root of new tree.
                    root.right = node;
                    next.add(root);
                    while(node != null) {
                        TreeNode cRoot = new TreeNode(root.right.val); //curr[0] is the copy of root, curr[1] is the copy of node
                        cRoot.left = copyTree(root.right.left);
                        cRoot.right = root.right.right;
                        TreeNode curr = getValNode(cRoot, node.val);
                        TreeNode tmp = curr.left;
                        curr.left = new TreeNode(n);
                        curr.left.right = tmp;

                        next.add(cRoot);
                        node = node.left;
                    }
                }
                res = next;
            }
            return res;
        }
        private TreeNode getValNode(TreeNode n, int val) {
            while(n != null) {
                if(n.val == val) break;
                n = n.left;
            }
            return n;
        }
        private TreeNode copyTree(TreeNode root) {
            if(root == null) return null;
            TreeNode cRoot = new TreeNode(root.val);
            cRoot.left = copyTree(root.left);
            cRoot.right = copyTree(root.right);
            return cRoot;
        }
        private List<Integer> printTree(TreeNode root) {
            List<TreeNode> nodes = new ArrayList<>();
            List<Integer> res = new ArrayList<>();
            nodes.add(root);
            while(!nodes.isEmpty()) {
                TreeNode n = nodes.remove(0);
                if(n == null) res.add(0);
                else {
                    res.add(n.val);
                    nodes.add(n.left);
                    nodes.add(n.right);
                }
            }
            return res;
        }

}
