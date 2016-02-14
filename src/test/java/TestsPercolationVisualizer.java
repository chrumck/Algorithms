import org.junit.*;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.*;

public class TestsPercolationVisualizer {
    private Percolation perc;
    private PercolationVisualizer percVis;

    public static final int N = 10;
    private static final int DELAY = 1000;

    @Before
    public void setUp() throws Exception {
        perc = new Percolation(N);
        percVis = new PercolationVisualizer();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void PercolationStats_throwsIfNlessThan1() {
//        percVis.draw(perc, N);
//        StdDraw.show(DELAY);
    }




}