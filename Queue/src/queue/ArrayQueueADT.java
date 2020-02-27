package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    private int tail = 0;
    private int head = 0;
    private Object[] elements = new Object[4];
//    E = [e1...en]

    //    Pre: true
//    Post: E'= [e1, ..., en, e]
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }

    //    Pre: true
//    Post: E' = [e, e1, ... en]
    public static void push(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue);
        queue.head = (queue.head - 1 + queue.elements.length) % queue.elements.length;
        queue.elements[queue.head] = element;
    }

    //    Pre: true
//    Post: |E'| = 2*|E|
    private static void ensureCapacity(ArrayQueueADT queue) {
        if ((queue.tail + 1) % queue.elements.length == queue.head) {
            Object[] temp = new Object[2 * queue.elements.length];
            int j = 0;
            for (int i = queue.head; i != queue.tail; i = (i + 1) % queue.elements.length, j++) {
                temp[j] = queue.elements[i];
            }
            queue.head = 0;
            queue.tail = j;
            queue.elements = Arrays.copyOf(temp, temp.length);
        }
    }

    //    Pre : size() > 0
//    Post: R = e[n] & E' = [e1..en-1]
    public static Object remove(ArrayQueueADT queue) {
        assert size(queue) > 0;
        queue.tail = (queue.tail - 1 + queue.elements.length) % queue.elements.length;
        Object ans = queue.elements[queue.tail];
        queue.elements[queue.tail] = null;
        return ans;
    }

    //    Pre: |E| > 0
//    Post: R = e[1] & E' = [e2..en]
    public static Object dequeue(ArrayQueueADT queue) {
        assert size(queue) > 0;
        Object ans = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return ans;
    }

    //    Pre = true
//    Post: R = |E|
    public static int size(ArrayQueueADT queue) {
        return (queue.tail - queue.head + queue.elements.length) % queue.elements.length;
    }

    //    Pre: size > 0
//    Post: R = en
    public static Object peek(ArrayQueueADT queue) {
        assert size(queue) > 0;
        return queue.elements[(queue.tail - 1 + queue.elements.length) % queue.elements.length];
    }

    //    Pre: |E| > 0
//    Post: R = e1
    public static Object element(ArrayQueueADT queue) {
        assert size(queue) > 0;
        return queue.elements[queue.head];
    }

    //    Pre: true
//    Post: R = |E| == 0
    public static boolean isEmpty(ArrayQueueADT queue) {
        return size(queue) == 0;
    }

    //    Pre: true
//    Post: |E| = 0
    public static void clear(ArrayQueueADT queue) {
        queue.head = 0;
        queue.tail = 0;
        queue.elements = new Object[5];
    }
}