import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N;          // collection size
    private Node first;     // first node sentinel
    private Node last;      //last node sentinel

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = new Node();
        last = new Node();
        first.next = last;
        last.previous = first;
        N = 0;
        assert check();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first.next == last;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("item cannot be null");
        first.item = item;
        Node newFirst = new Node();
        newFirst.next = first;
        first.previous = newFirst;
        first = newFirst;
        N++;
        assert check();
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("item cannot be null");
        last.item = item;
        Node newLast = new Node();
        last.next = newLast;
        newLast.previous = last;
        last = newLast;
        N++;
        assert check();
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("deque empty");
        first = first.next;
        Item item = first.item;
        first.previous = null;
        first.item = null;
        N--;
        assert check();
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("deque empty");
        last = last.previous;
        Item item = last.item;
        last.next = null;
        last.item = null;
        N--;
        assert check();
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {return new DequeIterator();}

    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current.next != last;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.next.item;
            current = current.next;
            return item;
        }
    }

    // check internal invariants
    private boolean check() {

        // check a few properties of instance variable 'first'
        if (N < 0) return false;
        if (N == 0 && first.next != last)  return false;
        if (N == 1 && first.next == last) return false;
        if (N == 1 && first.next.next != last) return false;
        if (N > 1 && first.next == last) return false;
        if (N > 1 && first.next.next == last) return false;

        // check internal consistency of instance variable N
        int numberOfNodes = 0;
        for (Node x = first; x != last && numberOfNodes <= N; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != N + 1) return false;

        return true;
    }

}