/**
 Find the k most frequent words from a streaming file
 or
 the k most popular hashtags in the past hour


 Naive way
 Using a hashmap<word, frequency>

 Optimal way
 Trie + minHeap
 Trie stores the terms together with frequency
 minHeap keeps the rank for top k

 At last: Merge Dupe Tags
 Union Find
 Synonyms



 Merge email lists
 Given 1 million email list:
 list 1: a@a.com, b@b.com
 list 2: b@b.com, c@c.com
 list 3: e@e.com
 list 4: a@a.com
 ...
 Combine lists with identical emails, and output tuples:
 (list 1, list 2, list 4) (a@a.com, b@b.com, c@c.com)
 (list 3) (e@e.com)


 */
import java.util.List;

class FrequencyOfTag {
    int frequency;
    String tag;
    FrequencyOfTag(int freq, String tag) {
        frequency = freq;
        this.tag = tag;
    }
}

public class TopKTags {

//    private MinHeap minHeap;
//    private Trie tags;
//
//    public TopKTags(int k) {
//        minHeap = new MinHeap(k);
//        tags = new Trie();
//    }
//
//    public List<String> getTopKTags() {
//        return minHeap.getList();
//    }
//
//    public void add(String tag) {
//        int cnt = tags.add(tag);
//        if(cnt > minHeap.top().frequency) {
//            minHeap.poll();
//            minHeap.offer(new FrequencyOfTag(cnt, tag));
//        }
//    }
//
//    public void expire(String tag) {
//        int cnt = tags.remove(tag);
//        if(cnt >= minHeap.top().frequency - 1) {
//            minHeap.remove(tag);
//            minHeap.offer(new FrequencyOfTag(cnt, tag));
//        }
//    }
}
