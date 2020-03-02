package queue;

/**
 * @author Yaroslav Ilin
 */

public class LinkedQueue implements Queue {
    private int size;
    private Node top;
    private Node tail;

    @Override
    public void enqueue(Object element) {
        size++;
        Node newTail = new Node(element, null);
        tail.next = newTail;
        tail = newTail;
        if (size == 0) {
            top = tail;
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
    public int size() {
        return size;
    }

    @Override
    public Object element() {
        assert size > 0;
        return top.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
}
