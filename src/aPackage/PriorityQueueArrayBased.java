package aPackage;

import java.util.Arrays;

public class PriorityQueueArrayBased<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

    private T[] queue;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public PriorityQueueArrayBased() {
        queue = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T newEntry) {
        ensureCapacity();
        queue[size] = newEntry;
        size++;
        Arrays.sort(queue, 0, size); 
    }

    @Override
    public T remove() {
        if (isEmpty())
            return null;
        T highestPriority = queue[0];
        for (int i = 1; i < size; i++) {
            queue[i - 1] = queue[i];
        }
        queue[size - 1] = null;
        size--;
        return highestPriority;
    }

    @Override
    public T peek() {
        if (isEmpty())
            return null;
        return queue[0];
    }

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public int getSize() {return size;}

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            queue[i] = null;
        }
        size = 0;
    }

    private void ensureCapacity() {
        if (size >= queue.length) {
            queue = Arrays.copyOf(queue, 2 * queue.length);
        }
    }
}
