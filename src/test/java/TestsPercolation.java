import org.junit.*;
import static org.junit.Assert.*;

public class TestsPercolation {
    private Percolation perc;
    public static final int N = 3;

    @Before
    public void setUp() throws Exception {
        perc = new Percolation(N);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void Percolation_throwsIfNlessThan1() {
            Percolation perc = new Percolation(0);
    }

    @Test
    public void Percolation_acceptsNEquals1() {
        Percolation perc = new Percolation(1);
        assertFalse(perc.isOpen(1, 1));
        assertFalse(perc.percolates());
    }

    @Test
    public void Percolation_acceptsNEquals1Percolates() {
        Percolation perc = new Percolation(1);
        perc.open(1,1);
        assertTrue(perc.isOpen(1, 1));
        assertTrue(perc.percolates());
    }

    @Test
    public void Percolation_acceptsNEquals2() {
        Percolation perc = new Percolation(2);
        perc.open(1,1);
        perc.open(2,2);
        assertFalse(perc.isOpen(2, 1));
        assertFalse(perc.percolates());
    }

    @Test
    public void Percolation_acceptsNEquals2Percolates() {
        Percolation perc = new Percolation(2);
        perc.open(1,1);
        perc.open(2,2);
        perc.open(1,2);
        assertFalse(perc.isOpen(2, 1));
        assertTrue(perc.percolates());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Percolation_isOpen_ThrowsIfiTooSmall() {
            perc.isOpen(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Percolation_isOpen_ThrowsIfiTooLarge() {
        perc.isOpen(N + 1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Percolation_isOpen_ThrowsIfjTooSmall() {
            perc.isOpen(1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Percolation_isOpen_ThrowsIfjTooLarge() {
            perc.isOpen(1, N + 1);
    }

    @Test
    public void Percolation_open_worksForNEquals1() {
        Percolation perc = new Percolation(1);
        perc.open(1, 1);
        assertTrue(perc.isOpen(1, 1));
        assertTrue(perc.isFull(1, 1));
        assertTrue(perc.percolates());
    }

    @Test
    public void Percolation_open_opensAndDoesConnnectToClosed() {
        perc.open(2, 3);
        assertTrue(perc.isOpen(2, 3));
        assertFalse(perc.isFull(2, 3));
        assertFalse(perc.percolates());
    }

    @Test
    public void Percolation_open_opensAndConnnectsToOpenTop() {
        perc.open(2, 2);
        perc.open(3, 2);
        assertTrue(perc.isOpen(2, 2));
        assertTrue(perc.isOpen(3, 2));
        assertFalse(perc.isFull(2, 2));
        assertFalse(perc.isFull(3, 2));
        assertFalse(perc.percolates());
    }

    @Test
    public void Percolation_open_opensAndConnnectsToOpenTopPercolates() {
        perc.open(1, 2);
        perc.open(2, 2);
        perc.open(3, 2);
        assertTrue(perc.isOpen(1, 2));
        assertTrue(perc.isOpen(2, 2));
        assertTrue(perc.isOpen(3, 2));
        assertFalse(perc.isOpen(1, 3));
        assertTrue(perc.isFull(1, 2));
        assertTrue(perc.isFull(2, 2));
        assertTrue(perc.isFull(3, 2));
        assertTrue(perc.percolates());
    }

    @Test
    public void Percolation_open_opensAndConnnectsToOpenSide() {
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(2, 3);
        assertTrue(perc.isOpen(2, 1));
        assertTrue(perc.isOpen(2, 2));
        assertTrue(perc.isOpen(2, 3));
        assertFalse(perc.isOpen(1, 3));
        assertFalse(perc.isFull(2, 1));
        assertFalse(perc.isFull(2, 2));
        assertFalse(perc.isFull(2, 3));
        assertFalse(perc.percolates());
    }

    @Test
    public void Percolation_open_opensAndConnnectsToOpenSidePercolates() {
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(2, 3);
        perc.open(1, 1);
        perc.open(3, 3);
        assertTrue(perc.isOpen(2, 1));
        assertTrue(perc.isOpen(2, 2));
        assertTrue(perc.isOpen(2, 3));
        assertFalse(perc.isOpen(1, 3));
        assertTrue(perc.isFull(2, 1));
        assertTrue(perc.isFull(2, 2));
        assertTrue(perc.isFull(2, 3));
        assertTrue(perc.percolates());
    }

//    @Test
//    public void Percolation_open_doesNotBackwash() {
//        perc.open(1,3);
//        perc.open(2,3);
//        perc.open(3,3);
//        perc.open(3,1);
//        assertTrue(perc.isOpen(1,3));
//        assertTrue(perc.isOpen(2,3));
//        assertTrue(perc.isOpen(3,3));
//        assertTrue(perc.isOpen(3,1));
//        assertTrue(perc.isFull(1,3));
//        assertTrue(perc.isFull(2,3));
//        assertTrue(perc.isFull(3,3));
//        assertFalse(perc.isFull(3,1));
//        assertTrue(perc.percolates());
//    }

}