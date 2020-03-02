package queue;

/**
 * @author Yaroslav Ilin
 */

public interface Queue {
    //    Pre: true
//    Post: E'= [e1, ..., en, e]
    void enqueue(Object element);

    //    Pre: |E| > 0
//    Post: E' = [e2..en]
//    Ret = e1
    Object dequeue();

    //    Pre = true
//    Ret = |E|
    int size();

    //    Pre: |E| > 0
//    Ret = e1
    Object element();

    //    Pre: true
//    Ret = |E| == 0
    boolean isEmpty();

    //    Pre: true
//    Post: |E| = 0
    void clear();
}
