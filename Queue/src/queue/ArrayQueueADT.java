package queue;

import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * @author Yaroslav Ilin
 */

public class ArrayQueueADT {
    private int tail = 0;
    private int head = 0;
    private int capacity = 5;
    private Object[] elements = new Object[4];

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, size(queue) + 1);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.capacity;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int size) {
        if (size >= queue.capacity) {
            Object[] temp = new Object[2 * size];
            int j = 0;
            for (int i = queue.head; i < queue.tail; i = (i + 1) % size, j++) {
                temp[j] = queue.elements[i];
            }
            queue.capacity = size * 2;
            queue.elements = Arrays.copyOf(temp, queue.capacity);
        }
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert size(queue) > 0;
        Object ans = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.capacity;
        return ans;
    }

    public static int size(ArrayQueueADT queue) {
        return abs(queue.tail - queue.head);
    }

    public static Object element(ArrayQueueADT queue) {
        assert size(queue) > 0;
        return queue.elements[queue.head];
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return size(queue) == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.head = 0;
        queue.tail = 0;
        queue.elements = new Object[5];
        queue.capacity = 5;
    }

}
