import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;          // collection size
    private Item[] a;         // array of items

    // construct an empty randomized queue
    public RandomizedQueue(){
        a = (Item[]) new Object[2];
        N = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("item cannot be null");
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue empty");
        int randomIdx = StdRandom.uniform(0, N);
        Item item = a[randomIdx];
        a[randomIdx] = a[N-1];
        a[N-1] = null;
        N--;
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue empty");
        int randomIdx = StdRandom.uniform(0, N);
        return a[randomIdx];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int itrIndex;
        private int[] itrArray;

        public RandomizedQueueIterator() {
            itrIndex = 0;
            //generating and shuffling itrArray
            itrArray = new int[N];
            for (int i = 0; i < N; i++) {
                int randomIdx = StdRandom.uniform(0, i + 1);
                int swap = itrArray[randomIdx];
                itrArray[i] = swap;
                itrArray[randomIdx] = i;
            }
        }

        public boolean hasNext() {
            return itrIndex < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[itrArray[itrIndex++]];
        }
    }
}