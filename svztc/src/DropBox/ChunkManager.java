package DropBox;

/**

 */
import java.util.BitSet;
import java.util.*;

class Pool {

    BitSet set;
    int start;
    int end;
    int rankinpool;
    Pool prev; //parent
    List<Pool> next; //children

    public Pool(int nbits, int start, int end, int rank) { //end inclusive
        set = new BitSet(nbits);
        this.start = start;
        this.end = end;
        rankinpool = rank;
    }

    public boolean isDone() {
        for(int i = 0; i < set.size(); i++) {
            if(!set.get(i)) return false;
        }
        return true;
    }
}
public class ChunkManager {

    Pool root;
    int poolsize;
    int filesize;

    public ChunkManager(int fsize, int poolsize) {
        this.poolsize = poolsize;
        this.filesize = filesize;
        root = constructor(null, 0, fsize, 0);
    }

    private Pool constructor(Pool prev, int start, int end, int rank) {
        if(start <= end) return null;
        Pool p = new Pool(poolsize, start, end, rank);
        p.prev = prev;
        if(end - start == poolsize) {
            return p;
        }
        int sectionsize = (end - start) / poolsize; //each bitset in p controls sectionsize number of file units
        for(int i = 0; i < poolsize; i++) {
            p.next.set(i, constructor(p, start + i * sectionsize, start + (i + 1) * sectionsize, i));
        }
        return p;
    }

    public boolean isCompleteForNow(int s, int e) {
        update(s, e, 0, filesize);
        if(root.isDone()) return true;
        return false;
    }

    private void update(int querystart, int queryend, int rangestart, int rangeend) {

    }

}
