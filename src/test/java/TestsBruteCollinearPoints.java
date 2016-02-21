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
    public void bruteCol_ThrowsIfPointsNull() {
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
                new Point(14,20),
                new Point(13,13),
                new Point(10,10),
                new Point(12,12),
        });
        assertEquals(1, bruteCol.numberOfSegments());
        assertEquals("(10, 10) -> (13, 13)", bruteCol.segments()[0].toString());
    }

    @Test
    public void bruteCol_CalculatesCollinear3() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(11,11),
                new Point(14,40),
                new Point(13,13),
                new Point(10,10),
                new Point(12,12),
                new Point(14,41),
                new Point(14,42),
                new Point(14,43),
        });
        assertEquals(2, bruteCol.numberOfSegments());
        assertEquals("(10, 10) -> (13, 13)", bruteCol.segments()[0].toString());
        assertEquals("(14, 40) -> (14, 43)", bruteCol.segments()[1].toString());
    }

    @Test
    public void bruteCol_CalculatesCollinear4() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(11,11),
                new Point(20,10),
                new Point(13,13),
                new Point(10,10),
                new Point(12,12),
                new Point(21,10),
                new Point(22,10),
        });
        assertEquals(2, bruteCol.numberOfSegments());
        assertEquals("(10, 10) -> (22, 10)", bruteCol.segments()[0].toString());
        assertEquals("(10, 10) -> (13, 13)", bruteCol.segments()[1].toString());
    }

    @Test
    public void bruteCol_CalculatesCollinear5() {
        bruteCol = new BruteCollinearPoints(new Point[] {
                new Point(11,11),
                new Point(11,10),
                new Point(13,13),
                new Point(10,10),
                new Point(12,12),
                new Point(12,10),
                new Point(13,10),
                new Point(13,11),
                new Point(13,12),
        });
        assertEquals(3, bruteCol.numberOfSegments());
        assertEquals("(10, 10) -> (13, 10)", bruteCol.segments()[0].toString());
        assertEquals("(10, 10) -> (13, 13)", bruteCol.segments()[1].toString());
        assertEquals("(13, 10) -> (13, 13)", bruteCol.segments()[2].toString());
    }


}