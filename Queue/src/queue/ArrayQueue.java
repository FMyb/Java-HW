package queue;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue {
    private int head = 0;
    private Object[] elements = new Object[4];
//    E = [e1...en]

    //    Pre: true
//    Post: E'= [e1, ..., en, e]
    @Override
    public void enqueue(Object element) {
        assert element != null;
        ensureCapacity();
        elements[(head + size) % elements.length] = element;
        size++;
    }

    //    Pre: true
//    Post: |E'| = 2*|E|
    private void ensureCapacity() {
        if (((head + size) % elements.length + 1) % elements.length == head) {
            Object[] temp = new Object[2 * elements.length];
            if (head > (head + size) % elements.length) {
                System.arraycopy(elements, head, temp, 0, elements.length - head);
                System.arraycopy(elements, 0, temp, elements.length - head, (head + size) % elements.length);
            } else {
                System.arraycopy(elements, head, temp, head, (head + size) % elements.length - head);
            }
            head = 0;
            elements = Arrays.copyOf(temp, temp.length);
        }
    }

    //    Pre: |E| > 0
//    Post: R = e[1] & E' = [e2..en]
    @Override
    public Object dequeue() {
        assert size > 0;
        size--;
        Object ans = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return ans;
    }

    //    Pre: |E| > 0
//    Post: R = e1
    @Override
    public Object element() {
        assert size() > 0;
        return elements[head];
    }

    //    Pre: true
//    Post: |E| = 0
    @Override
    public void clear() {
        head = 0;
        size = 0;
        elements = new Object[4];
    }

    @Override
    public Queue factory() {
        return new ArrayQueue();
    }
}