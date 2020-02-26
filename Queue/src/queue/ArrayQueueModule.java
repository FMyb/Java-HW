package queue;

import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * @author Yaroslav Ilin
 */

public class ArrayQueueModule {
    private static int tail = 0;
    private static int head = 0;
    private static Object[] elements = new Object[4];
//    E = [e1...en]

    //    Pre: true
//    Post: E'= [e1, ..., en, e]
    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    //    Pre: true
//    Post: |E'| = 2*|E|
    private static void ensureCapacity() {
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

    //    Pre: |E| > 0
//    Post: R = e[1] & E' = [e2..en]
    public static Object dequeue() {
        assert size() > 0;
        Object ans = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return ans;
    }

    //    Pre = true
//    Post: R = |E|
    public static int size() {
        return (tail - head + elements.length) % elements.length;
    }

    //    Pre: |E| > 0
//    Post: R = e1
    public static Object element() {
        assert size() > 0;
        return elements[head];
    }

    //    Pre: true
//    Post: R = |E| == 0
    public static boolean isEmpty() {
        return size() == 0;
    }

    //    Pre: true
//    Post: |E| = 0
    public static void clear() {
        head = 0;
        tail = 0;
        elements = new Object[5];
    }

}
