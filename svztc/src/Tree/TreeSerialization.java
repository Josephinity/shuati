package Tree;
import java.util.*;
/**
 Tree Serialization and Deserialization
 def __init__(self, x):
     self.val = x
     self.left = None
     self.right = None


 */

class Node{
    int val;
    List<Node> children;
    public Node(int n) {
        val = n;
        children = new ArrayList<>();
    }
}
public class TreeSerialization {
    public static void main(String[] args) {
//        String s = "1#2,3,4#5,7#8#9#10,12###";
//        Node root = deserializeTree(s);
//        printTree(deserializeTree(s));
//        System.out.println(serializeTree(root));


        String s = "1,2,3,6,#,#,#,4,#,#,5,#,7,8,#,#,#,";
        TreeNode tree = deserialize(s);
        System.out.println(serialize(tree));

    }

    public static void printTree(Node root) {
        List<Node> parents = new LinkedList<>();
        parents.add(root);
        System.out.println(root.val);
        List<Node> children;
        while(!parents.isEmpty()) {
            children = new LinkedList<>();
            for(Node p: parents) {
                for(Node c: p.children) {
                    children.add(c);
                    System.out.print(c.val + ",");
                }
                System.out.print("  ");
            }
            System.out.println();
            parents = children;
        }
    }



    public static String serializeTree(Node root) {
        if(root == null) return "";
        StringBuilder str = new StringBuilder();
        List<Node> queue = new LinkedList<>();
        queue.add(root);
        str.append(root.val);
        str.append('#');
        while(!queue.isEmpty()) {
            Node n = queue.remove(0);
            for(Node child: n.children) {
                queue.add(child);
                str.append(child.val);
                str.append(',');
            }
            if(str.charAt(str.length() - 1) == ',') str.deleteCharAt(str.length() - 1);
            str.append('#');
        }
        return str.toString();
    }



    //    "1#2,3,4,5##6##7,8###"
    public static Node deserializeTree(String s) {
        if(s.isEmpty()) return null;
        List<Node> queue = new LinkedList<>();
        Node dummy = new Node(0);
        queue.add(dummy);
        int i = 0, len = s.length();
        while(i < len) {
            Node parent = queue.remove(0);
            while(s.charAt(i) != '#') {
                if(s.charAt(i) == ',') i++;
                int  val = 0;
                while (Character.isDigit(s.charAt(i))) {
                    val = val * 10 + s.charAt(i) - '0';
                    i++;
                }
                Node n = new Node(val);
                parent.children.add(n);
                queue.add(n);
            }
            i++;
        }
        return dummy.children.get(0);
    }

    //dfs
    public static String serialize(TreeNode root) {
        StringBuilder str = new StringBuilder();
        serialize(root, str);
        return str.toString();
    }
    private static void serialize(TreeNode root, StringBuilder str) {
        if(root == null) {
            str.append('#');
            str.append(',');
            return;
        }
        str.append(root.val);
        str.append(',');
        serialize(root.left, str);
        serialize(root.right, str);
    }

    public static TreeNode deserialize(String tree) {
        return deseiralizehelper(new StringBuilder(tree));
    }

    private static TreeNode deseiralizehelper(StringBuilder tree) {
        if(tree.length() == 0) return null;
        if(tree.charAt(0) == '#') {
            tree.deleteCharAt(0);
            tree.deleteCharAt(0);
            return null;
        }
        int comma = 0;
        while(tree.charAt(comma) != ',') comma++;
        int value = Integer.parseInt(tree.substring(0, comma));
        System.out.println(value);
        TreeNode root = new TreeNode(value);
        tree.delete(0, comma + 1);
        root.left = deseiralizehelper(tree);
        root.right = deseiralizehelper(tree);
        return root;
    }


}
