import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestsBoard {
    private Board board;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = NullPointerException.class)
    public void fastCol_ThrowsIfPointsNull() {
        board = new Board(null);
    }

    /*
    @Test(expected = IllegalArgumentException.class)
    public void fastCol_ThrowsIfDublicateTiles() {
    int[][] blocks = new int[][]{
    new int[]{0, 1, 2},
    new int[]{3, 4, 5},
    new int[]{6, 8, 8}
    };
    board = new Board(blocks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fastCol_ThrowsIfNoZeroTile() {
    int[][] blocks = new int[][]{
    new int[]{9, 1, 2},
    new int[]{3, 4, 5},
    new int[]{6, 7, 8}
    };
    board = new Board(blocks);
    }
    */

    @Test
    public void fastCol_dimension_returnsN() {
        int[][] blocks = new int[][]{
                new int[]{3, 2},
                new int[]{0, 1}
        };
        board = new Board(blocks);

        assertEquals(2, board.dimension());
    }

    @Test
    public void fastCol_hamming_returns2() {
        int[][] blocks = new int[][]{
                new int[]{3, 2},
                new int[]{0, 1}
        };
        board = new Board(blocks);

        assertEquals(2, board.hamming());
    }

    @Test
    public void fastCol_hamming_returns0() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0},
        };
        board = new Board(blocks);

        assertEquals(0, board.hamming());
    }

    @Test
    public void fastCol_hamming_returns1() {
        int[][] blocks = new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 1},
        };
        board = new Board(blocks);

        assertEquals(1, board.hamming());
    }

    @Test
    public void fastCol_hamming_returns3() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{5, 4, 6},
                new int[]{7, 0, 8},
        };
        board = new Board(blocks);

        assertEquals(3, board.hamming());
    }

    @Test
    public void fastCol_manhattan_returns0() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0},
        };
        board = new Board(blocks);

        assertEquals(0, board.manhattan());
    }

    @Test
    public void fastCol_manhattan_returns1() {
        int[][] blocks = new int[][]{
                new int[]{1, 2},
                new int[]{0, 3}
        };
        board = new Board(blocks);

        assertEquals(1, board.manhattan());
    }

    @Test
    public void fastCol_manhattan_returns1vert() {
        int[][] blocks = new int[][]{
                new int[]{1, 0},
                new int[]{3, 2}
        };
        board = new Board(blocks);

        assertEquals(1, board.manhattan());
    }

    @Test
    public void fastCol_manhattan_returns2() {
        int[][] blocks = new int[][]{
                new int[]{2, 1, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0},
        };
        board = new Board(blocks);

        assertEquals(2, board.manhattan());
    }

    @Test
    public void fastCol_manhattan_returns2vert() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 8, 6},
                new int[]{7, 5, 0},
        };
        board = new Board(blocks);

        assertEquals(2, board.manhattan());
    }

    @Test
    public void fastCol_manhattan_returns4diagonal() {
        int[][] blocks = new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 1},
        };
        board = new Board(blocks);

        assertEquals(4, board.manhattan());
    }

    @Test
    public void fastCol_manhattan_returns8diagonal() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 7},
                new int[]{4, 5, 6},
                new int[]{3, 8, 0},
        };
        board = new Board(blocks);

        assertEquals(8, board.manhattan());
    }

    @Test
    public void fastCol_manhattan_returns12mixed() {
        int[][] blocks = new int[][]{
                new int[]{1, 8, 7},
                new int[]{4, 5, 6},
                new int[]{3, 2, 0},
        };
        board = new Board(blocks);

        assertEquals(12, board.manhattan());
    }

    @Test
    public void fastCol_isGoal_returnsTrue() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0},
        };
        board = new Board(blocks);

        assertTrue(board.isGoal());
    }

    @Test
    public void fastCol_isGoal_returnsFalse() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 0, 8},
        };
        board = new Board(blocks);

        assertFalse(board.isGoal());
    }

    @Test
    public void fastCol_twin_swapsFirstRow() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0},
        };
        board = new Board(blocks);
        Board twin = board.twin();

        assertEquals(2, twin.hamming());
        assertEquals(2, twin.manhattan());
    }

    @Test
    public void fastCol_twin_swapsSecondRow() {
        int[][] blocks = new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 6, 5},
                new int[]{7, 8, 1},
        };
        board = new Board(blocks);
        Board twin = board.twin();

        assertEquals(1 + 3, twin.hamming());
        assertEquals(4 + 1 + 2 + 1, twin.manhattan());
    }


}