import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class Board {
    private char[] tiles;
    private int N;
    private int h;
    private int m;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null) throw new NullPointerException("blocks is null");
        N = blocks.length;
        copy2Dto1D(blocks);
        calcHamming();
        calcManhattan();
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        return h;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return m;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return h == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        char[] newTiles = Arrays.copyOf(tiles, tiles.length);
        if (newTiles[0] != 0 && newTiles[1] != 0) swapTiles(newTiles, 0, 1);
        else swapTiles(newTiles, N, N + 1);
        return new Board(copy1Dto2D(newTiles));
    }


    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.m == this.m && Arrays.equals(that.tiles,this.tiles)) return true;
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Stack<Board>();
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        return "";
    }

    //private methods -------------------------------------------------------------------

    //copy2Dto1D
    private void copy2Dto1D(int[][] blocks) {
        tiles = new char[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i * N + j] = (char) blocks[i][j];
            }
        }
        /*
        char[] tilesSorted = Arrays.copyOf(tiles, tiles.length);
        Arrays.sort(tilesSorted);
        for (int i = 0; i < tilesSorted.length - 1; i++) {
            if (tilesSorted[i] != tilesSorted[i + 1] - 1) throw new IllegalArgumentException();
        }
        if (tilesSorted[0] != 0) throw new IllegalArgumentException();
        */
    }

    //copy1Dto2D
    private int[][] copy1Dto2D(char[] array) {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = array[i * N + j];
            }
        }
        return blocks;
    }

    //calcHamming
    private void calcHamming() {
        h = 0;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i] != i + 1) h++;
        }
    }

    //calcManhattan
    private void calcManhattan() {
        m = 0;
        for (int i = 0; i < tiles.length; i++) {
            int tileNumber = tiles[i] - 1;
            if (tileNumber == -1) continue;
            m += Math.abs(tileNumber / N - i / N) +
                    Math.abs(tileNumber % N - i % N);
        }
    }

    //swapTiles
    private void swapTiles(char[] array, int i, int j) {
        char t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
