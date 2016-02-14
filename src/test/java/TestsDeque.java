import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TestsDeque {
    private Deque<String> deque;

    @Before
    public void setUp() throws Exception {
        deque = new Deque<String>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = NullPointerException.class)
    public void Deque_addFirst_throwsIfNullAdded() {
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void Deque_addLast_throwsIfNullAdded() {
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void Deque_removeFirst_throwsIfEmpty() {
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void Deque_removeLast_throwsIfEmpty() {
        deque.removeLast();
    }

    @Test
    public void Deque_isEmpty_returnsTrue() {
        assertTrue(deque.isEmpty());
    }

    @Test
    public void Deque_size_returns0() {
        assertEquals(deque.size(), 0);
    }

    @Test
    public void Deque_addFirst_AddsOne(){
        deque.addFirst("firstString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 1);
    }

    @Test
    public void Deque_addFirst_AddsTwo(){
        deque.addFirst("firstString");
        deque.addFirst("secondString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 2);
    }

    @Test
    public void Deque_addLast_AddsOne(){
        deque.addLast("firstString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 1);
    }

    @Test
    public void Deque_addLast_AddsTwo(){
        deque.addLast("firstString");
        deque.addLast("secondString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 2);
    }

    @Test
    public void Deque_addFirst_removeFirst_AddsRemovesTwo(){
        deque.addFirst("firstString");
        deque.addFirst("secondString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 2);
        String returnedString = deque.removeFirst();
        assertEquals(returnedString,"secondString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 1);
        returnedString = deque.removeFirst();
        assertEquals(returnedString,"firstString");
        assertTrue(deque.isEmpty());
        assertEquals(deque.size(), 0);
    }

    @Test
    public void Deque_addLast_removeLast_AddsRemovesTwo(){
        deque.addLast("firstString");
        deque.addLast("secondString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 2);
        String returnedString = deque.removeLast();
        assertEquals(returnedString,"secondString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 1);
        returnedString = deque.removeLast();
        assertEquals(returnedString,"firstString");
        assertTrue(deque.isEmpty());
        assertEquals(deque.size(), 0);
    }

    @Test
    public void Deque_addLast_removeFirst_AddsRemovesTwo(){
        deque.addLast("firstString");
        deque.addLast("secondString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 2);
        String returnedString = deque.removeFirst();
        assertEquals(returnedString,"firstString");
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(), 1);
        returnedString = deque.removeFirst();
        assertEquals(returnedString,"secondString");
        assertTrue(deque.isEmpty());
        assertEquals(deque.size(), 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void Deque_addLast_removeFirst_throwsIfEmpty() {
        deque.addFirst("firstString");
        deque.addFirst("secondString");
        deque.removeLast();
        deque.removeLast();
        deque.removeFirst();
    }

    @Test
    public void Deque_iterator_iterates() {
        deque.addFirst("firstString");
        deque.addFirst("secondString");
        deque.addLast("thirdString");
        Iterator<String> dequeIterator = deque.iterator();
        assertEquals(dequeIterator.next(),"secondString");
        assertEquals(dequeIterator.next(),"firstString");
        assertEquals(dequeIterator.next(),"thirdString");
    }

    @Test
    public void Deque_iterator_hasNextIsFalse() {
        deque.addFirst("firstString");
        deque.addFirst("secondString");
        Iterator<String> dequeIterator = deque.iterator();
        dequeIterator.next();
        dequeIterator.next();
        assertFalse(dequeIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void Deque_iterator_throwsNoSuchElement() {
        deque.addFirst("firstString");
        deque.addFirst("secondString");
        Iterator<String> dequeIterator = deque.iterator();
        dequeIterator.next();
        dequeIterator.next();
        dequeIterator.next();
    }
}