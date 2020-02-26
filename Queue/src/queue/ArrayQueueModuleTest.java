package queue;

import java.rmi.ServerError;
import java.util.Scanner;

public class ArrayQueueModuleTest {
    public static void fill() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.element() + " " +
                            ArrayQueueModule.dequeue()
            );
        }
    }

    public static void main(String[] args) {
        fill();
        dump();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String str = in.next();
            if (str.equals("push")) {
                int x = in.nextInt();
                ArrayQueueModule.enqueue(x);
            }
            if (str.equals("pop")) {
                int x = (int) ArrayQueueModule.dequeue();
                System.err.println(x);
            }
            if (str.equals("size")) {
                System.err.println(ArrayQueueModule.size());
            }
            if (str.equals("isEmpty")) {
                System.err.println(ArrayQueueModule.isEmpty());
            }
        }
    }
}
