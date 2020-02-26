package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    private int tail = 0;
    private int head = 0;
    private Object[] elements = new Object[4];

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
//        print();
    }

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

    public static Object dequeue(ArrayQueueADT queue) {
        assert size(queue) > 0;
        Object ans = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
//        print();
        return ans;
    }

    public static int size(ArrayQueueADT queue) {
        return (queue.tail - queue.head + queue.elements.length) % queue.elements.length;
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
    }
}