package queue;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Yaroslav Ilin
 */

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    @Override
    public abstract void enqueue(Object element);

    protected abstract Queue factory();

    @Override
    public abstract Object dequeue();

    @Override
    public int size() {
        return size;
    }

    @Override
    public abstract Object element();

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public abstract void clear();

    @Override
    public Queue filter(Predicate<Object> predicate) {
        assert predicate != null;
        Queue newQueue = factory();
        for (int i = 0; i < size; i++) {
            Object temp = this.dequeue();
            if (predicate.test(temp)) {
                newQueue.enqueue(temp);
            }
            this.enqueue(temp);
        }
        return newQueue;
    }

    @Override
    public Queue map(Function<Object, Object> function) {
        assert function != null;
        Queue newQueue = factory();
        for (int i = 0; i < size; i++) {
            Object temp = this.dequeue();
            newQueue.enqueue(function.apply(temp));
            this.enqueue(temp);
        }
        return newQueue;
    }
}
