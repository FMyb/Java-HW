package queue;

import java.util.Arrays;

public class ArrayQueue {
    private int tail = 0;
    private int head = 0;
    private Object[] elements = new Object[4];

    public void enqueue(Object element) {
        assert element != null;
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
//        print();
    }

    private void ensureCapacity() {
        if ((tail + 1) % elements.length == head) {
            Object[] temp = new Object[2 * elements.length];
            int j = 0;
            for (int i = head; i != tail; i = (i + 1) % elements.length, j++) {
                temp[j] = elements[i];
            }
            head = 0;
            tail = j;
            elements = Arrays.copyOf(temp, temp.length);
        }
    }

    public Object dequeue() {
        assert size() > 0;
        Object ans = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
//        print();
        return ans;
    }

    public int size() {
        return (tail - head + elements.length) % elements.length;
    }

    public Object element() {
        assert size() > 0;
        return elements[head];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        head = 0;
        tail = 0;
        elements = new Object[5];
    }
}