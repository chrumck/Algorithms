import edu.princeton.cs.algs4.*;

public class PercolationStats {
    private int N;
    private int T;
    private double[] results;
    public static final double z95 = 1.96;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException("N,T has to be larger than 0");
        }
        this.N = N;
        this.T = T;
        this.results = new double[T];

        for (int i = 0; i < T; i++) {
            results[i] = getResultFromSingleTest();
        }
    }

    //runs a single test on an N*N sized percolation grid
    private double getResultFromSingleTest() {
        Percolation perc = new Percolation(N);
        int[] randomInput = getRandomInputArray();
        for (int i = 0; i < randomInput.length; i++) {
            perc.open(randomInput[i] / N + 1, randomInput[i] % N + 1);
            if (perc.percolates()) {
                return (double)(i + 1) / (N * N);
            }
        }
        throw new IllegalArgumentException("System does not percolate");
    }

    //generates random input array
    private int[] getRandomInputArray() {
        int[] newRandomInput = new int[N * N];
        for (int i = 0; i < newRandomInput.length; i++) {
            newRandomInput[i] = i;
        }
        StdRandom.shuffle(newRandomInput);
        return newRandomInput;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - z95 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + z95 * stddev() / Math.sqrt(T);
    }

    // test client
    public static void main(String[] args) {

    }
}