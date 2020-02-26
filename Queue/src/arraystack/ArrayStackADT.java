package arraystack;

import java.util.Arrays;

public class ArrayStackADT {
    private /*static*/ int size;
    private /*static*/ Object[] elements =
            new Object[10];

    public static void push(ArrayStackADT stack, Object element) {
        assert element != null;

        ensureCapacity(stack, stack.size + 1);
        stack.elements[stack.size++] = element;
    }

    private static void ensureCapacity(ArrayStackADT stack, int capacity) {
        if (capacity > stack.elements.length) {
            stack.elements = Arrays.copyOf(stack.elements, 2 * capacity);
        }
    }

    public static Object pop(ArrayStackADT stack) {
        assert stack.size > 0;

        return stack.elements[--stack.size];
    }

    public static Object peek(ArrayStackADT stack) {
        assert stack.size > 0;

        return stack.elements[stack.size - 1];
    }
    public static int size(ArrayStackADT stack) {
        return stack.size;
    }

    public static boolean isEmpty(ArrayStackADT stack) {
        return stack.size == 0;
    }
}
