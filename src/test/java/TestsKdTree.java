import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestsKdTree {
    private KdTree tree;

    @Before
    public void setUp() throws Exception {
        tree = new KdTree();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void isEmpty_returnsTrue() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    public void isEmpty_returnsFalse() {
        tree.insert(new Point2D(0, 0));
        assertFalse(tree.isEmpty());

        assertNotEquals(0, tree.size());
    }

    @Test(expected = NullPointerException.class)
    public void insert_throwsIfPointNull() {
        tree.insert(null);
    }

    @Test
    public void insert_doesNotCreateDuplicates() {
        tree.insert(new Point2D(0, 0));
        tree.insert(new Point2D(0, 0));

        assertEquals(1, tree.size());
        assertTrue(tree.contains(new Point2D(0, 0)));
    }

    @Test
    public void insert_insertsProperly() {
        tree.insert(new Point2D(0, 0));
        tree.insert(new Point2D(1, 1));

        assertEquals(2, tree.size());
        assertTrue(tree.contains(new Point2D(0, 0)));
        assertTrue(tree.contains(new Point2D(1, 1)));
    }

    @Test(expected = NullPointerException.class)
    public void contains_throwsIfPointNull() {
        tree.contains(null);
    }

    @Test
    public void contains_returnsTrue() {
        tree.insert(new Point2D(0, 0));
        tree.insert(new Point2D(1, 1));

        assertTrue(tree.contains(new Point2D(1, 1)));
    }

    @Test
    public void contains_returnsFalse() {
        tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.6, 0.5));
        tree.insert(new Point2D(0.75, 0.75));
        tree.insert(new Point2D(0.70, 0.75));

        assertFalse(tree.contains(new Point2D(0.6, 0.6)));
    }

    @Test(expected = NullPointerException.class)
    public void range_throwsIfPointNull() {
        tree.range(null);
    }

    @Test
    public void range_returns1Point() {
        tree.insert(new Point2D(0.25, 0.25));
        tree.insert(new Point2D(0.50, 0.50));
        tree.insert(new Point2D(0.75, 0.75));
        List<Point2D> range = (List<Point2D>) tree.range(new RectHV(0.3, 0.3, 0.6, 0.6));

        assertTrue(range.contains(new Point2D(0.5, 0.5)));
        assertEquals(1, range.size());
    }

    @Test
    public void range_returns0Points() {
        tree.insert(new Point2D(0.50, 0.50));
        tree.insert(new Point2D(0.25, 0.25));
        tree.insert(new Point2D(0.75, 0.75));
        List<Point2D> range = (List<Point2D>) tree.range(new RectHV(0.51, 0.51, 0.6, 0.6));

        assertFalse(range.contains(new Point2D(0.5, 0.5)));
        assertEquals(0, range.size());

        range = (List<Point2D>) tree.range(new RectHV(0.51, 0.51, 0.6, 0.6));

        assertFalse(range.contains(new Point2D(0.5, 0.5)));
        assertEquals(0, range.size());
    }

    @Test
    public void range_returns0PointsIfTreeEmpty() {
        List<Point2D> range = (List<Point2D>) tree.range(new RectHV(0.51, 0.51, 0.6, 0.6));
        assertEquals(0, range.size());
    }

    @Test(expected = NullPointerException.class)
    public void nearest_throwsIfPointNull() {
        tree.nearest(null);
    }

    @Test
    public void nearest_returnsNearest() {
        tree.insert(new Point2D(0.25, 0.25));
        tree.insert(new Point2D(0.50, 0.50));
        tree.insert(new Point2D(0.75, 0.75));

        assertEquals(new Point2D(0.75, 0.75), tree.nearest(new Point2D(0.8, 0.8)));
        assertEquals(new Point2D(0.50, 0.50), tree.nearest(new Point2D(0.4, 0.4)));
    }

}