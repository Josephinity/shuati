/**
 Prefix tree
 */

public class Trie {

    TrieNode root;
    int charSetSize;

    class TrieNode {
        boolean isEnd;
        int frequency;
        TrieNode[] children;
        public TrieNode(int size){
            children = new TrieNode[size];
        }
    }

    private TrieNode get(String word) {
        TrieNode current = root;
        for(char c: word.toCharArray()) {
            if(current.children[c] == null) {
                current.children[c] = new TrieNode(charSetSize);
            }
            current = current.children[c];
        }
        return current;
    }

    private void set(String word, int n) {//insert word into Trie n times
        TrieNode current = root;
        for(char c: word.toCharArray()) {
            if(current.children[c] == null) {
                current.children[c] = new TrieNode(charSetSize);
            }
            current.children[c].frequency += n;
            current = current.children[c];
        }
        current.isEnd = true;
    }

    public void add(String word) {
        set(word, 1);
    }

    public void remove(String word) {
        if(get(word).frequency > 0) {
            set(word, -1);
        } else {
            //error handling
        }
    }



}
