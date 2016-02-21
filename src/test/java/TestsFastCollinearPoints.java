import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestsFastCollinearPoints {
    private FastCollinearPoints fastCol;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = NullPointerException.class)
    public void fastCol_ThrowsIfPointsNull() {
        fastCol = new FastCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void fastCol_ThrowsIfSomePointsNull() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(10, 10),
                new Point(11, 11),
                new Point(12, 12),
                null,
                new Point(13, 13)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void fastCol_throwsIfPointsSame1() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(11, 11),
                new Point(13, 13),
                new Point(10, 10),
                new Point(10, 10)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void fastCol_throwsIfPointsSame2() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(10, 10),
                new Point(11, 11),
                new Point(13, 13),
                new Point(10, 10)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void fastCol_throwsIfPointsSame3() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(14, 14),
                new Point(11, 11),
                new Point(14, 14),
                new Point(13, 13)
        });
    }

    @Test
    public void fastCol_Returns0IfNotEnoutghPts() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(11, 11),
                new Point(13, 13),
                new Point(10, 10),
        });
        assertEquals(0, fastCol.numberOfSegments());
    }

    @Test
    public void fastCol_Returns0IfNotEnoutghPts2() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(10, 10),
        });
        assertEquals(0, fastCol.numberOfSegments());
    }

    @Test
    public void fastCol_CalculatesCollinear1() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(11, 11),
                new Point(13, 13),
                new Point(10, 10),
                new Point(12, 12)
        });
        assertEquals(1, fastCol.numberOfSegments());
        assertEquals("(10, 10) -> (13, 13)", fastCol.segments()[0].toString());
    }

    @Test
    public void fastCol_CalculatesCollinear2() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(11, 11),
                new Point(13, 13),
                new Point(10, 10),
                new Point(14, 14),
                new Point(12, 12)
        });
        assertEquals(1, fastCol.numberOfSegments());
        assertEquals("(10, 10) -> (14, 14)", fastCol.segments()[0].toString());
    }

    @Test
    public void fastCol_CalculatesCollinear4() {
        fastCol = new FastCollinearPoints(new Point[]{
                new Point(11, 11),
                new Point(13, 13),
                new Point(10, 10),
                new Point(14, 14),
                new Point(12, 12),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3)
        });
        assertEquals(2, fastCol.numberOfSegments());
        assertEquals("(0, 0) -> (14, 14)", fastCol.segments()[0].toString());
        assertEquals("(0, 0) -> (0, 3)", fastCol.segments()[1].toString());
    }


}