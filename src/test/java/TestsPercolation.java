import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void Percolation_throwsIfNlessThan1(){
        try {
            perc = new Percolation(0);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("N has to be larger than 0"));
            return;
        }
        assertTrue(false);
    }

}