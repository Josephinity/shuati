package FB;

/**
 Implement circular buffer with read & write functions
 &
 Round Robin Iterator

 */

public class CircularBuffer<T> {

        private T[] buffer;

        private int tail; //poll elements from tail

        private int head; //new elements add to the head

        @SuppressWarnings("unchecked")
        public CircularBuffer(int n) {
            buffer = (T[]) new Object[n];
            tail = 0;
            head = 0;
        }

        public void add(T toAdd) {
            if (head != (tail - 1)) {
                buffer[head++] = toAdd;
            } else {
                //throw new BufferOverflowException();
            }
            head = head % buffer.length;
        }

        public T get() {
            T t = null;
            int adjTail = tail > head ? tail - buffer.length : tail;
            if (adjTail < head) {
                t = (T) buffer[tail++];
                tail = tail % buffer.length;
            } else {//tail == head
                //throw new BufferUnderflowException();
            }
            return t;
        }

        public String toString() {
            return "CircularBuffer(size=" + buffer.length + ", head=" + head + ", tail=" + tail + ")";
        }


}
