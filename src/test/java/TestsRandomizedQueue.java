import java.util.*;

import com.sun.javafx.scene.web.Debugger;
import org.junit.*;
import static org.junit.Assert.*;

public class TestsRandomizedQueue {
    private RandomizedQueue<String> randQueue;

    @Before
    public void setUp() throws Exception {
        randQueue = new RandomizedQueue<String>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = NullPointerException.class)
    public void RandQueue_enqueue_throwsIfNullAdded() {
        randQueue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void RandQueue_dequeue_throwsIfEmpty() {
        randQueue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void RandQueue_sample_throwsIfEmpty() {
        randQueue.sample();
    }

    @Test
    public void RandQueue_isEmpty_returnsTrue() {
        assertTrue(randQueue.isEmpty());
    }

    @Test
    public void RandQueue_size_returns0() {
        assertEquals(randQueue.size(), 0);
    }

    @Test
    public void RandQueue_enqueue_AddsOne(){
        randQueue.enqueue("firstString");
        assertFalse(randQueue.isEmpty());
        assertEquals(randQueue.size(), 1);
    }

    @Test
    public void RandQueue_enqueue_AddsThree(){
        randQueue.enqueue("firstString");
        randQueue.enqueue("secondString");
        randQueue.enqueue("thirdString");
        assertFalse(randQueue.isEmpty());
        assertEquals(randQueue.size(), 3);
    }

    @Test
    public void RandQueue_enqueue_dequeue_AddsRemovesThree(){
        randQueue.enqueue("firstString");
        randQueue.enqueue("secondString");
        randQueue.enqueue("thirdString");
        randQueue.dequeue();
        randQueue.dequeue();
        randQueue.dequeue();
        assertTrue(randQueue.isEmpty());
        assertEquals(randQueue.size(), 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void RandQueue_enqueue_dequeue_throwsIfEmpty(){
        randQueue.enqueue("firstString");
        randQueue.enqueue("secondString");
        randQueue.dequeue();
        randQueue.dequeue();
        randQueue.dequeue();
    }

    @Test
    public void RandQueue_enqueue_dequeue_returnsRandom(){
        String output;
        randQueue.enqueue("firstString");
        randQueue.enqueue("secondString");
        randQueue.enqueue("thirdString");
        randQueue.enqueue("fourthString");

        output = randQueue.dequeue();
        System.out.println(output);
        assertFalse(output.isEmpty());

        output = randQueue.dequeue();
        System.out.println(output);
        assertFalse(output.isEmpty());

        output = randQueue.dequeue();
        System.out.println(output);
        assertFalse(output.isEmpty());

        output = randQueue.dequeue();
        System.out.println(output);
        assertFalse(output.isEmpty());

        assertTrue(randQueue.isEmpty());
        assertEquals(randQueue.size(), 0);
    }

    @Test
    public void RandQueue_sample_returnsRandom(){
        randQueue.enqueue("firstString");
        randQueue.enqueue("secondString");
        randQueue.enqueue("thirdString");
        System.out.println(randQueue.sample());
        System.out.println(randQueue.sample());
        System.out.println(randQueue.sample());
        System.out.println(randQueue.sample());
        System.out.println(randQueue.sample());
        System.out.println(randQueue.sample());
        assertFalse(randQueue.isEmpty());
        assertEquals(randQueue.size(), 3);
    }

    @Test
    public void RandQueue_iterator_returnsRandom(){
        randQueue.enqueue("firstString");
        randQueue.enqueue("secondString");
        randQueue.enqueue("thirdString");
        randQueue.enqueue("fourthString");

        Iterator<String> itr = randQueue.iterator();
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());

        assertFalse(itr.hasNext());
        assertEquals(randQueue.size(), 4);
    }


}