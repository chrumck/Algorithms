import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class TestsPoint {
    private Point testPoint;

    @Before
    public void setUp() throws Exception {
        testPoint = new Point(10, 10);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void Point_compareTo_Returns0IfPointsSame() {
        int result = testPoint.compareTo(testPoint);
        assertEquals(0, result);
    }

    @Test
    public void Point_compareTo_ReturnsPositiveIfThatYSmaller() {
        int result = testPoint.compareTo(new Point(9, 9));
        assertEquals(1, result);
    }

    @Test
    public void Point_compareTo_ReturnsPositiveIfThatYSmallerAndXSame() {
        int result = testPoint.compareTo(new Point(10, 9));
        assertEquals(1, result);
    }

    @Test
    public void Point_compareTo_ReturnsPositiveIfThatXSmallerAndYSame() {
        int result = testPoint.compareTo(new Point(9, 10));
        assertEquals(1, result);
    }

    @Test
    public void Point_compareTo_ReturnsNegativeIfThatYBiggerAndXSame() {
        int result = testPoint.compareTo(new Point(10, 11));
        assertEquals(-1, result);
    }

    @Test
    public void Point_compareTo_ReturnsNegativeIfThatXBiggerAndYSame() {
        int result = testPoint.compareTo(new Point(11, 10));
        assertEquals(-1, result);
    }

    @Test
    public void Point_SlopeTo_Returns1() {
        double result = testPoint.slopeTo(new Point(11, 11));
        assertEquals(1, result, 0.000001);
    }

    @Test
    public void Point_SlopeTo_Returns2() {
        double result = testPoint.slopeTo(new Point(11, 12));
        assertEquals(2, result, 0.000001);
    }

    @Test
    public void Point_SlopeTo_ReturnsNegative2() {
        double result = testPoint.slopeTo(new Point(11, 8));
        assertEquals(-2, result, 0.000001);
    }

    @Test
    public void Point_SlopeTo_ReturnsNegative3() {
        double result = testPoint.slopeTo(new Point(9, 13));
        assertEquals(-3, result, 0.000001);
    }

    @Test
    public void Point_SlopeTo_ReturnsPositiveOForEqualY() {
        double result = testPoint.slopeTo(new Point(11, 10));
        assertEquals(+0, result, 0.000001);
    }

    @Test
    public void Point_SlopeTo_ReturnsPositiveOForEqualY2() {
        double result = testPoint.slopeTo(new Point(-11, 10));
        assertEquals(+0, result, 0.000001);
    }

    @Test
    public void Point_SlopeTo_ReturnsNegativeInfinityIfPointsSame() {
        double result = testPoint.slopeTo(testPoint);
        assertEquals(Double.NEGATIVE_INFINITY, result, 0.000001);
    }

    @Test
    public void Point_SlopeTo_ReturnsPositiveInfinityIfXSame() {
        double result = testPoint.slopeTo(new Point(10, 11));
        assertEquals(Double.POSITIVE_INFINITY, result, 0.000001);
    }

    @Test
    public void Point_SlopeTo_ReturnsPositiveInfinityIfXSame2() {
        double result = testPoint.slopeTo(new Point(10, -11));
        assertEquals(Double.POSITIVE_INFINITY, result, 0.000001);
    }

    @Test
    public void Point_SlopeOrder_ReturnsPostiveIfQ1LargerThanQ2() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(10000, 11), new Point(10000, 10));
        assertEquals(1, result);
    }

    @Test
    public void Point_SlopeOrder_ReturnsNegativeIfQ1SmallerThanQ2() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(10000, 10), new Point(10000, 11));
        assertEquals(-1, result);
    }

    @Test
    public void Point_SlopeOrder_Returns0IfQ1AndQ2Same() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(1000000000, 11), new Point(1000000000, 11));
        assertEquals(0, result);
    }

    @Test
    public void Point_SlopeOrder_Returns0IfQ1AndQ2Horizontal() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(-11, 10), new Point(11, 10));
        assertEquals(0, result);
    }

    @Test
    public void Point_SlopeOrder_Returns0IfQ1AndQ2Vertical() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(10, 11), new Point(10, -11));
        assertEquals(0, result);
    }

    @Test
    public void Point_SlopeOrder_ReturnsPositiveIfQ1VerticalAndQ2Horizontal() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(10, 11), new Point(11, 10));
        assertEquals(1, result);
    }

    @Test
    public void Point_SlopeOrder_ReturnsNegativeIfQ1HorizontalAndQ2Vertical() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(11, 10), new Point(10, 11));
        assertEquals(-1, result);
    }

    @Test
    public void Point_SlopeOrder_ThrowsIfQ1SameAsP() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(10, 10), new Point(11, 11));
        assertEquals(-1, result, 0.00001);
    }


    @Test
    public void Point_SlopeOrder_ThrowsIfQ2SameAsP() {
        Comparator<Point> cmpr = testPoint.slopeOrder();
        int result = cmpr.compare(new Point(11, 11), new Point(10, 10));
        assertEquals(1, result, 0.00001);

    }



}