import edu.princeton.cs.algs4.*;

public class PercolationStats {
    private int N;
    private int T;
    private double[] results;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException("N,T has to be larger than 0");
        }
        this.N = N;
        this.T = T;
        this.results = new double[T];

        for (int i = 0; i < T; i++) {
            int openSitesCount = 0;
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, N), StdRandom.uniform(1, N));
                openSitesCount++;
            }
            results[i] = openSitesCount / (N * N);
        }
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
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    // test client
    public static void main(String[] args) {

    }
}