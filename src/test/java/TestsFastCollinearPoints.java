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
        fastCol = new FastCollinearPoints(new Point[] {
                new Point(10,10),
                new Point(11,11),
                new Point(12,12),
                null,
                new Point(13,13)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void fastCol_ThrowsIfNotEnoughPoints() {
        fastCol = new FastCollinearPoints(new Point[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fastCol_throwsIfPointsSame1() {
        fastCol = new FastCollinearPoints(new Point[] {
                new Point(11,11),
                new Point(13,13),
                new Point(10,10),
                new Point(10,10)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void fastCol_throwsIfPointsSame2() {
        fastCol = new FastCollinearPoints(new Point[] {
                new Point(10,10),
                new Point(11,11),
                new Point(13,13),
                new Point(10,10)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void fastCol_throwsIfPointsSame3() {
        fastCol = new FastCollinearPoints(new Point[] {
                new Point(14,14),
                new Point(11,11),
                new Point(14,14),
                new Point(13,13)
        });
    }

    @Test
    public void fastCol_CalculatesCollinear1() {
        fastCol = new FastCollinearPoints(new Point[] {
                new Point(11,11),
                new Point(13,13),
                new Point(10,10),
                new Point(12,12)
        });
        assertEquals(1, fastCol.numberOfSegments());
        assertEquals("(10, 10) -> (13, 13)", fastCol.segments()[0].toString());
    }



}