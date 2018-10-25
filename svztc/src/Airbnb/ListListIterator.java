package Airbnb;

/**
Create iterator from list of list.
 */
import java.util.NoSuchElementException;
import java.util.Stack;
class NestedList<T> {

    private NestedList list;
    private T value;

    public NestedList(T value) {
        this.value = value;
    }

    public NestedList(NestedList ls) {
        list = ls;
    }

    public boolean hasList() {
        return value == null;
    }

    public T getValue() {
        return value;
    }

    public NestedList getList() {
        return list;
    }
}

class IterState {

    int i;
    NestedList list;

    public IterState(int idx, NestedList list) {
        i = idx;
        this.list = list;
    }
}

public class ListListIterator<T> {

    NestedList list;
    Stack<IterState> st;

//    public ListListIterator(NestedList ls) {
//        list = ls;
//        st = new Stack<>();
//        st.push(new IterState(0, list));
//    }
//
//    public boolean hasNext() {
//        findNext();
//        return !st.isEmpty();
//    }
//
//    public T next() throws NoSuchElementException {
//        if(hasNext()) {
//            T next = st.peek().list.getList().get(i);
//            st.peek().i++;
//            return next;
//        }
//        throw new NoSuchElementException();
//    }
//
//    private void findNext() {
//        while (!st.isEmpty()) {
//            IterState current = st.peek();
//            if (current.i >= list.size()) {
//                st.pop();
//            } else {
//                int idx = current.i;
//                if (current.list.get(idx).hasList()) {
//                    NestedList ls = current.list.get(idx).getList();
//                    if (ls == null || ls.isEmpty()) current.i++;
//                    else st.push(new IterState(0, ls));
//                } else {
//                    return; //next element found
//                }
//            }
//        }
//    }
}
