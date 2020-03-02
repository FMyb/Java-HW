package queue;

public class AbstractQueueTest {
    public static void fill(AbstractQueue queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
    }

    public static void dump(AbstractQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(
                    queue.size() + " " +
                            queue.element() + " " +
                            queue.dequeue()
            );
        }
    }

    public static void main(String[] args) {
        AbstractQueue queue = new LinkedQueue();
        fill(queue);
        dump(queue);
    }
}
