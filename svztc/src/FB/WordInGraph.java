package FB;

/**
Given a word and an undirected graph with each node representing a char, find if the word
 exists in any path of the graph.

 e.g.
 graph:
 a-d-o-g-a-c-t

 "dog" -> true
 "cat" -> false
 */
import java.util.*;

class Node{

    char letter;
    List<Node> children;


}

public class WordInGraph {


    public boolean wordInGraph(List<Node> graph, String str) {

        char[] s = str.toCharArray();

        for(Node n: graph) {

            if (dfs(n, s, 0)) return true;

        }

        return false;
    }

    private boolean dfs(Node n, char[] s, int i) {

        if(i == s.length) return true;

        if(n.letter == s[i]) {

            for(Node child: n.children) {

                if (dfs(child, s, i + 1)) return true;

            }

        }

        return false;

    }


}