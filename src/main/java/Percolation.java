import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private boolean[] openSites;
    private int N;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException("N has to be larger than 0");
        }
        this.N = N;
        sites = new WeightedQuickUnionUF(N ^ 2 + 2);
        openSites = new boolean[N ^ 2 + 2];
    }

    //translate x,y to position in sites
    private int xyTo1D(int x, int y) {
        if (x < 1 || x > N || y < 1 || y > N) {
            throw new IllegalArgumentException("x or y out of range");
        }
        return x + N * (y - 1);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {

    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return true;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        return true;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

}
