package queue;

import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * @author Yaroslav Ilin
 */

public class ArrayQueueModule {
    private static int tail = 0;
    private static int head = 0;
    private static int capacity = 5;
    private static Object[] elements = new Object[4];

    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size() + 1);
        elements[tail] = element;
        tail = (tail + 1) % capacity;
    }

    private static void ensureCapacity(int size) {
        if (size >= capacity) {
            Object[] temp = new Object[2 * size];
            int j = 0;
            for (int i = head; i < tail; i = (i + 1) % size, j++) {
                temp[j] = elements[i];
            }
            capacity = size * 2;
            elements = Arrays.copyOf(temp, capacity);
        }
    }

    public static Object dequeue() {
        assert size() > 0;
        Object ans = elements[head];
        elements[head] = null;
        head = (head + 1) % capacity;
        return ans;
    }

    public  static int size() {
        return abs(tail - head);
    }

    public static Object element() {
        assert size() > 0;
        return elements[head];
    }

    public static boolean isEmpty() {
        return size() == 0;
    }

    public static void clear() {
        head = 0;
        tail = 0;
        elements = new Object[5];
        capacity = 5;
    }

}
