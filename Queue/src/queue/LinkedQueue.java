package queue;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Yaroslav Ilin
 */

public class LinkedQueue extends AbstractQueue {
    private Node top;
    private Node tail;

    @Override
    public void enqueue(Object element) {
        size++;
        Node newTail = new Node(element, null);
        if (size == 1) {
            tail = newTail;
            top = tail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }
    }

    @Override
    public Object dequeue() {
        assert size > 0;
        size--;
        Object result = top.value;
        top = top.next;
        if (size == 0) {
            tail = null;
        }
        return result;
    }

    @Override
    public Object element() {
        assert size > 0;
        return top.value;
    }

    @Override
    public void clear() {
        size = 0;
        top = null;
        tail = null;
    }


    private static class Node {
        Object value;
        Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Queue factory() {
        return new LinkedQueue();
    }
}
