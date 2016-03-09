import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestsPointSET {
    private PointSET set;

    @Before
    public void setUp() throws Exception {
        set = new PointSET();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void isEmpty_returnsTrue() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void isEmpty_returnsFalse() {
        set.insert(new Point2D(0, 0));
        assertFalse(set.isEmpty());

        assertNotEquals(0, set.size());
    }

    @Test(expected = NullPointerException.class)
    public void insert_throwsIfPointNull() {
        set.insert(null);
    }

    @Test
    public void insert_doesNotCreateDuplicates() {
        set.insert(new Point2D(0, 0));
        set.insert(new Point2D(0, 0));

        assertEquals(1, set.size());
    }

    @Test
    public void insert_insertsProperly() {
        set.insert(new Point2D(0, 0));
        set.insert(new Point2D(1, 1));

        assertEquals(2, set.size());
    }

    @Test(expected = NullPointerException.class)
    public void contains_throwsIfPointNull() {
        set.contains(null);
    }

    @Test
    public void contains_returnsTrue() {
        set.insert(new Point2D(0, 0));
        set.insert(new Point2D(1, 1));

        assertTrue(set.contains(new Point2D(1, 1)));
    }

    @Test
    public void contains_returnsFalse() {
        set.insert(new Point2D(0, 0));
        set.insert(new Point2D(1, 1));

        assertFalse(set.contains(new Point2D(0.5, 0.5)));
    }

    @Test(expected = NullPointerException.class)
    public void range_throwsIfPointNull() {
        set.range(null);
    }

    @Test
    public void range_returns1Point() {
        set.insert(new Point2D(0.25, 0.25));
        set.insert(new Point2D(0.50, 0.50));
        set.insert(new Point2D(0.75, 0.75));
        List<Point2D> range = (List<Point2D>) set.range(new RectHV(0.3, 0.3, 0.6, 0.6));

        assertTrue(range.contains(new Point2D(0.5, 0.5)));
    }

    @Test
    public void range_returns0Points() {
        set.insert(new Point2D(0.25, 0.25));
        set.insert(new Point2D(0.50, 0.50));
        set.insert(new Point2D(0.75, 0.75));
        List<Point2D> range = (List<Point2D>) set.range(new RectHV(0.51, 0.51, 0.6, 0.6));

        assertFalse(range.contains(new Point2D(0.5, 0.5)));
        assertEquals(0, range.size());
    }

    @Test(expected = NullPointerException.class)
    public void nearest_throwsIfPointNull() {
        set.nearest(null);
    }

    @Test
    public void nearest_returnsNearest() {
        set.insert(new Point2D(0.25, 0.25));
        set.insert(new Point2D(0.50, 0.50));
        set.insert(new Point2D(0.75, 0.75));

        assertEquals(new Point2D(0.75, 0.75), set.nearest(new Point2D(0.8, 0.8)));
        assertEquals(new Point2D(0.50, 0.50), set.nearest(new Point2D(0.4, 0.4)));
    }

}