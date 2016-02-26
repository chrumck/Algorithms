import edu.princeton.cs.algs4.StdOut;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

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

    @Test
    public void fastCol_equals_returnsTrue() {
        int[][] blocks = new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 6, 5},
                new int[]{7, 8, 1},
        };
        board = new Board(blocks);

        assertTrue(board.equals(new Board(blocks)));
    }

    @Test
    public void fastCol_equals_returnsTrueIfSameRef() {
        int[][] blocks = new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 6, 5},
                new int[]{7, 8, 1},
        };
        board = new Board(blocks);

        assertTrue(board.equals(board));
    }

    @Test
    public void fastCol_equals_returnsFalseIfNull() {
        int[][] blocks = new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 6, 5},
                new int[]{7, 8, 1},
        };
        board = new Board(blocks);

        assertFalse(board.equals(null));
    }

    @Test
    public void fastCol_equals_returnsFalse() {
        int[][] blocks = new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 6, 5},
                new int[]{7, 8, 1},
        };
        board = new Board(blocks);

        assertFalse(board.equals(board.twin()));
    }

    @Test
    public void fastCol_toString_returnsProperFormat() {
        int[][] blocks = new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 6, 5},
                new int[]{7, 8, 1},
        };
        board = new Board(blocks);
        StdOut.print(board.toString());
        assertEquals(   "3\n" +
                        " 0  2  3 \n" +
                        " 4  6  5 \n" +
                        " 7  8  1 ",
                        board.toString());
    }

    @Test
    public void fastCol_neighbors_returns2() {
        int[][] blocks = new int[][]{
                new int[]{0, 1},
                new int[]{2, 3}
        };
        board = new Board(blocks);
        Board result1 = new Board(new int[][]{
                new int[]{1, 0},
                new int[]{2, 3}
        });

        Board result2 = new Board(new int[][]{
                new int[]{2, 1},
                new int[]{0, 3}
        });

        List<Board> neighbors = makeList(board.neighbors());
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.indexOf(result1) != -1);
        assertTrue(neighbors.indexOf(result2) != -1);
    }

    @Test
    public void fastCol_neighbors_returnsOther2() {
        int[][] blocks = new int[][]{
                new int[]{3, 1},
                new int[]{2, 0}
        };
        board = new Board(blocks);
        Board result1 = new Board(new int[][]{
                new int[]{3, 0},
                new int[]{2, 1}
        });

        Board result2 = new Board(new int[][]{
                new int[]{3, 1},
                new int[]{0, 2}
        });

        List<Board> neighbors = makeList(board.neighbors());
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.indexOf(result1) != -1);
        assertTrue(neighbors.indexOf(result2) != -1);
    }

    @Test
    public void fastCol_neighbors_returns4() {
        int[][] blocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 0, 6},
                new int[]{7, 8, 5},
        };
        board = new Board(blocks);
        Board result1 = new Board(new int[][]{
                new int[]{1, 2, 3},
                new int[]{0, 4, 6},
                new int[]{7, 8, 5},
        });
        Board result2 = new Board(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 6, 0},
                new int[]{7, 8, 5},
        });
        Board result3 = new Board(new int[][]{
                new int[]{1, 0, 3},
                new int[]{4, 2, 6},
                new int[]{7, 8, 5},
        });
        Board result4 = new Board(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 8, 6},
                new int[]{7, 0, 5},
        });

        List<Board> neighbors = makeList(board.neighbors());
        assertEquals(4, neighbors.size());
        assertTrue(neighbors.indexOf(result1) != -1);
        assertTrue(neighbors.indexOf(result2) != -1);
        assertTrue(neighbors.indexOf(result3) != -1);
        assertTrue(neighbors.indexOf(result4) != -1);
    }

    //private methods ---------------------------------------------------------------

    public static <E> List<E> makeList(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }




}