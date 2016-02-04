import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestsPercolationStats {
    private PercolationStats percStats;
    public static final int N = 3;
    public static final int T = 3;

    @Before
    public void setUp() throws Exception {
        percStats = new PercolationStats(N, T);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void PercolationStats_throwsIfNlessThan1() {
        try {
            percStats = new PercolationStats(N, 0);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("N,T has to be larger than 0"));
            return;
        }
        assertTrue(false);
    }

    @Test
    public void PercolationStats_throwsIfTlessThan1() {
        try {
            percStats = new PercolationStats(0, T);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("N,T has to be larger than 0"));
            return;
        }
        assertTrue(false);
    }


}