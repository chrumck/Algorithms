import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestsBruteCollinearPoints {
    private BruteCollinearPoints bruteCol;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = NullPointerException.class)
    public void bruteCol_ThrowsIfNotPointsNull() {
        bruteCol = new BruteCollinearPoints(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bruteCol_ThrowsIfNotEnoughPoints() {
        bruteCol = new BruteCollinearPoints(new Point[0]);
    }

    @Test(expected = NullPointerException.class)
    public void bruteCol_ThrowsIfSomePointsNull() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(10,10),
                new Point(11,11),
                new Point(12,12),
                null,
                new Point(13,13)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void bruteCol_throwsIfPointsSame1() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(11,11),
                new Point(13,13),
                new Point(10,10),
                new Point(10,10)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void bruteCol_throwsIfPointsSame2() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(10,10),
                new Point(11,11),
                new Point(13,13),
                new Point(10,10)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void bruteCol_throwsIfPointsSame3() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(10,10),
                new Point(11,11),
                new Point(10,10),
                new Point(13,13)
        });
    }

    @Test
    public void bruteCol_CalculatesCollinear1() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(11,11),
                new Point(13,13),
                new Point(10,10),
                new Point(12,12)
        });
        assertEquals(1, bruteCol.numberOfSegments());
        assertEquals("(10, 10) -> (13, 13)", bruteCol.segments()[0].toString());
    }

    @Test
    public void bruteCol_CalculatesCollinear2() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(11,11),
                new Point(13,13),
                new Point(10,10),
                new Point(12,12),
                new Point(14,14)
        });
        assertEquals(2, bruteCol.numberOfSegments());
        assertEquals("(10, 10) -> (13, 13)", bruteCol.segments()[0].toString());
        assertEquals("(11, 11) -> (14, 14)", bruteCol.segments()[0].toString());
    }





}