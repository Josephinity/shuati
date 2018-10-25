package Google;

/**
 */
public class TrieTest {

    public static void main(String args[]) {
        AutoCompletionTrie trie = new AutoCompletionTrie(5);
        trie.insert("stefan");
        trie.insert("stay");
        trie.insert("stacey");
        trie.insert("step");
        trie.insert("stub");
        trie.insert("starbucks");
        trie.insert("strike");
        trie.insert("stefan");
        trie.insert("strict");
        trie.insert("steam");
        trie.insert("stream");
        trie.insert("starbucks");
        trie.insert("stella");
        trie.insert("steve");
        trie.insert("stray");
        trie.insert("stub");
        trie.insert("stub");
        trie.insert("starbucks");
        trie.insert("stub");
        trie.insert("strong");
        trie.insert("strength");
        trie.insert("stormzy");
        trie.insert("student");
        trie.insert("stranger");
        trie.insert("starbucks");
        trie.insert("stella");
        trie.insert("stub");
        trie.insert("stella");

        trie.getTopK("st");
        trie.getTopK("ste");
        trie.getTopK("stra");
    }
}
