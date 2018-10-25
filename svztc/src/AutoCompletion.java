/**
 * Google
 When typing on the cellphone, how do you auto complete the possible words after two letters typed.

 Follow-up: How to rank the words if they are weighted by frequency

 */
import java.util.List;
import java.util.ArrayList;

class TrieNode {

    char letter;
    TrieNode previousLetter;
    TrieNode[] nextLetters;
    int frequency;
    boolean isEndOfWord;

    public TrieNode(char letter, TrieNode previousLetter) {
        this.letter = letter;
        this.previousLetter = previousLetter;
    }

}

class TrieX {

    private TrieNode root;

    class WordFrequency{        //keeps track of the frequency to rank the words
        String word;
        int frequency;

        WordFrequency(String w, int f) {
            word = w;
            frequency = f;
        }
    }


    public TrieX(int sizeCharSet) {
        root = new TrieNode('&', null);
        root.nextLetters = new TrieNode[sizeCharSet]; //sort by freq in descending order
    }

    public boolean insert(String word) {            //if the word is new return true, else false;
        TrieNode current = root;
        current.frequency++;                       //root.frequency stores how many words in total are in the Trie
        for(int i = 0; i < word.length(); i++) {

            int letter = word.charAt(i) - 'a';
            if(current.nextLetters[letter] == null) {               //initialize children
                current.nextLetters[letter] = new TrieNode(word.charAt(i), current);
            }
            current.nextLetters[letter].frequency++;

            current = current.nextLetters[letter];
        }
        if(current.isEndOfWord) {
            return false;
        }
        else {
            return true;
        }
    }

    public List<WordFrequency> search(String prefix) {   //returns a list of words with the given prefix
        List<WordFrequency> autoCompletion = new ArrayList<>();

        TrieNode current = root;

        for(char c: prefix.toCharArray()) {
            if(current.nextLetters[c - 'a'] == null) {
                return autoCompletion;          //if the prefix doesn't exist, return empty list
            } else {
                current = current.nextLetters[c - 'a'];
            }
        }

        List<WordFrequency> surffix = new ArrayList<>();
        depthFirstSearch(current, surffix, new StringBuilder());

        for(WordFrequency wf: surffix) {
            wf.word = prefix + wf.word;
            autoCompletion.add(wf);
        }

        //you may rank the autoCompletion by frequency according to the requirement in the follow-up question
        return autoCompletion;
    }

    private void depthFirstSearch(TrieNode current, List<WordFrequency> surffix, StringBuilder str) {
        if(current == null) return;

        str.append(current.letter);

        if(current.isEndOfWord && str.length() > 0) surffix.add(new WordFrequency(str.toString(), current.frequency));  //word found

        for(TrieNode nextLetter: current.nextLetters) {
            depthFirstSearch(nextLetter, surffix, str);
        }
        str.deleteCharAt(str.length() - 1);
    }

}

