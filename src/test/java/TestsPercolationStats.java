import org.junit.*;
import static org.junit.Assert.*;

public class TestsPercolationStats {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void PercolationStats_throwsIfNlessThan1() {
        PercolationStats percStats = new PercolationStats(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PercolationStats_throwsIfTlessThan1() {
        PercolationStats percStats = new PercolationStats(0, 1);
    }

    @Test
    public void PercolationStats_calculatesCorrectValues() {
        PercolationStats percStats = new PercolationStats(200, 100);
        assertEquals(0.593, percStats.mean(), 0.005);
        assertEquals(0.0092, percStats.stddev(), 0.005);
        assertEquals(0.5910, percStats.confidenceLo(), 0.005);
        assertEquals(0.5948, percStats.confidenceHi(), 0.005);
    }

    @Test
    public void PercolationStats_calculatesCorrectValues2() {
        PercolationStats percStats = new PercolationStats(2, 100000);
        assertEquals(0.666925, percStats.mean(), 0.005);
        assertEquals(0.1177, percStats.stddev(), 0.005);
        assertEquals(0.6650, percStats.confidenceLo(), 0.005);
        assertEquals(0.6680, percStats.confidenceHi(), 0.005);
    }

}