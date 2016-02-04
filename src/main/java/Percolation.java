import edu.princeton.cs.algs4.*;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private int N;
    private boolean[] openSites;
    private boolean percolates = false;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException("N has to be larger than 0");
        }
        this.N = N;
        openSites = new boolean[N * N + 1];
        sites = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 1; i < N + 1; i++) {
            sites.union(i, 0);
        }
    }

    //translate x,y to position in sites
    private int xyTo1D(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IllegalArgumentException("i or j out of range");
        }
        return i + N * (j - 1);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        int sIndex = xyTo1D(i, j);
        openSites[sIndex] = true;
        if (i > 1 && openSites[sIndex - 1]) {
            sites.union(sIndex, sIndex - 1);
        }
        if (i < N && openSites[sIndex + 1]) {
            sites.union(sIndex, sIndex + 1);
        }
        if (j > 1 && openSites[sIndex - N]) {
            sites.union(sIndex, sIndex - N);
        }
        if (j < N && openSites[sIndex + N]) {
            sites.union(sIndex, sIndex + N);
        }
        if (!percolates && j == N && sites.connected(sIndex, 0)) {
            percolates = true;
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return openSites[xyTo1D(i, j)];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        int sIndex = xyTo1D(i, j);
        return openSites[sIndex] && sites.connected(sIndex, 0);
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;
    }

}
