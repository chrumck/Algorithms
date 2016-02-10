import edu.princeton.cs.algs4.*;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private int N;
    private boolean[] openSites;
    private int[] openBottomSites;

    // constructor - create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException("N has to be larger than 0");
        }
        this.N = N;
        openSites = new boolean[N * N];
        openBottomSites = new int[0];
        sites = new WeightedQuickUnionUF(N * N);
        for (int i = 1; i < N; i++) {
            sites.union(0, i);
        }
    }

    //translate (row i, column j) to position in sites
    private int xyTo1D(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IllegalArgumentException("i or j out of range");
        }
        return N * (i - 1) + (j - 1);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        int sIndex = xyTo1D(i, j);
        openSites[sIndex] = true;
        if (j > 1 && openSites[sIndex - 1]) {
            sites.union(sIndex - 1, sIndex);
        }
        if (j < N && openSites[sIndex + 1]) {
            sites.union(sIndex + 1, sIndex);
        }
        if (i > 1 && openSites[sIndex - N]) {
            sites.union(sIndex - N, sIndex);
        }
        if (i < N && openSites[sIndex + N]) {
            sites.union(sIndex + N, sIndex);
        }
        if (i == N) {
            addSiteToOpenBottomSites(sIndex);
        }
    }

    //resize openBottomSites and add i to at the end of resized array
    private void addSiteToOpenBottomSites(int i) {
        int[] newArray = new int[openBottomSites.length + 1];
        if (openBottomSites.length > 0) {
            System.arraycopy(openBottomSites, 0, newArray, 0,
                    openBottomSites.length);
        }
        newArray[openBottomSites.length] = i;
        openBottomSites = newArray;
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

    // does the system percolate? - go over openBottomSites and check if any of them is full
    public boolean percolates() {
        for (int i = 0; i < openBottomSites.length; i++) {
            if (sites.connected(openBottomSites[i], 0)) {
                return true;
            }
        }
        return false;
    }
}
