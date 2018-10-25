package Google;

/**
Recommend top 5 frequent search terms for a prefix
 */
import java.util.*;

class FrequentTerm implements Comparable<FrequentTerm> {

    String term;
    int freq;

    public FrequentTerm(String s, int f) {
        term = s;
        freq = f;
    }

    public int compareTo(FrequentTerm other) {
        return this.freq - other.freq;
    }
}

class TrieNode {

    char letter;        //the letter carried by this node
    boolean endOfWord;  //whether this node is the end of a term
    String prefix;      //the full term/prefix
    int frequency;      //occurrences of this term
    TrieNode parent;
    TrieNode[] children;
    Map<String, FrequentTerm> topKFrequentTerms;          //Put what's in the heap into a hash map for easy access
    PriorityQueue<FrequentTerm> topKFrequentTermsRank;    //Top K frequent terms kept and sorted in a min-heap

    public TrieNode(char letter, String prefix, TrieNode parent, int charsetSize) {
        this.letter = letter;
        this.prefix = prefix;
        this.parent = parent;
        children = new TrieNode[charsetSize];
        topKFrequentTermsRank = new PriorityQueue<>(); //a min heap
        topKFrequentTerms = new HashMap<>();
    }

    public boolean heapifyRank(String term, int freq, int capacity) { //When a term frequency increases, check for possible updates for the ranks heap
        int size = topKFrequentTerms.size();
        if(size == capacity && freq <= topKFrequentTermsRank.peek().freq) { //The change in frequency did not affect the ranks
            return false;
        }
        FrequentTerm tuple = new FrequentTerm(term, freq);
        if(size == capacity) {
            if(topKFrequentTerms.containsKey(term)) { //The term was a member of top k before the change. Just update its frequency in the heap
                topKFrequentTermsRank.remove(topKFrequentTerms.get(term));
            } else { //The in frequency kicks out the min-heap top, it becomes a new member of the top k heap
                topKFrequentTerms.remove(topKFrequentTermsRank.poll().term); //discard  heap top to make room
            }
        }
        topKFrequentTerms.put(term, tuple);
        topKFrequentTermsRank.add(tuple);
        return true;
    }
}

public class AutoCompletionTrie {

    int charsetSize = 26;
    int k; //maintain k most frequent words in each node
    TrieNode root;
    char firstCharacterInCharSet = 'a';

    public AutoCompletionTrie(int k) {
        //this. charsetSize = charsetSize;
        this.k = k;
        root = new TrieNode('0', "", null, charsetSize);
    }

    public void insert(String term) {  //insert a term into the tree, add 1 to its term frequency
        TrieNode curr = root;
        StringBuilder prefix = new StringBuilder();
        for(char c: term.toCharArray()) {
            prefix.append(c);
            int idx = c - firstCharacterInCharSet;
            if(curr.children[idx] == null) { //Assume '0' is first letter in entire charset
                curr.children[idx] = new TrieNode(c, prefix.toString(), curr, charsetSize);
            }
            curr = curr.children[idx];
        }
        curr.endOfWord = true;
        curr.frequency++;
        TrieNode heapParent = curr.parent;
        while(heapParent != null) { ////When a term frequency increases, check for possible updates for the ranks heap in every ancestry node
            if(heapParent.heapifyRank(curr.prefix, curr.frequency, k)) { //If this update affects the topKFrequentTerms Heap
                heapParent = heapParent.parent;  //Keep going with heapification
            } else {//Otherwise this update does not affect the top k frequent rank. Stop heapifying
                break;
            }
        }
    }

    private TrieNode find(String prefix) {//find if the prefix exists
        TrieNode curr = root;
        for(char c: prefix.toCharArray()) {
            int idx = c - firstCharacterInCharSet;
            if(curr.children[idx] == null) {    // does not exist
                return null;
            }
            curr = curr.children[idx];
        }
        return curr;    //return the end node of the given prefix
    }

    public List<String> getTopK(String prefix) {    //get top K frequent terms with a prefix
        TrieNode node = find(prefix);
        if(node == null) return null;   //prefix does not exist
        List<String> terms = new ArrayList(node.topKFrequentTerms.keySet());
        System.out.println("Top k terms with prefix [" + prefix + "]");
        for(String term: terms) {
            System.out.print(term);
            for(int i = 0; i < 18 - term.length(); i++) {
                System.out.print(" ");
            }
            System.out.println(node.topKFrequentTerms.get(term).freq);
        }
        return terms;
    }

}

