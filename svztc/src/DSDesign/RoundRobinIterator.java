package DSDesign;

import java.util.Iterator;
import java.util.List;

/**
 Design a class that takes in a list of iterators and iterates in a round robin fashion,
 we don't care about priorities of jobs.
 */
public class RoundRobinIterator {
    List<Iterator> iterators;
    int index;  //current element
    int size;

    public RoundRobinIterator(List<Iterator> iter) {
        iterators = iter;
        size = iterators.size();
        index = 0;
        if(size > 0) locateNext();
    }

    public Object next() {
        locateNext();
        if(size == 0) return null;
        return iterators.get(index).next();
    }

    public boolean hasNext() {      // tradeoff - O(1) or O(n) ?
        if(size == 0) return false;
        return true;

    }

    private void locateNext() {
        if(iterators.get(index).hasNext()) {
            index = (index + 1) % size;
        }
        while(size > 0 && !iterators.get(index).hasNext()) {
            Iterator tmp = iterators.get(index);
            iterators.set(index, iterators.get(size - 1));
            iterators.set(size - 1, tmp);
            size--;
            if(index == size) index = 0;
        }
    }

}
