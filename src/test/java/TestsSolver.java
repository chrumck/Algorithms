import edu.princeton.cs.algs4.StdOut;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestsSolver {
    private Solver solver;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = NullPointerException.class)
    public void solver_ThrowsIfInitialNull() {
        solver = new Solver(null);
    }

    @Test
    public void solver_ReturnsSolution1() {
        int[][] initialBlocks = new int[][]{
                new int[]{1, 2},
                new int[]{0, 3}
        };
        Board initialBoard = new Board(initialBlocks);

        int[][] finalBlocks = new int[][]{
                new int[]{1, 2},
                new int[]{3, 0}
        };
        Board finalBoard = new Board(finalBlocks);

        solver = new Solver(initialBoard);
        List<Board> solution = (List<Board>) solver.solution();

        assertEquals(1, solver.moves());
        assertTrue(solver.isSolvable());
        assertTrue(solution.get(0).equals(initialBoard));
        assertTrue(solution.get(1).equals(finalBoard));
    }

    @Test
    public void solver_ReturnsSolution2() {
        int[][] initialBlocks = new int[][]{
                new int[]{0, 2},
                new int[]{1, 3}
        };
        Board initialBoard = new Board(initialBlocks);

        int[][] finalBlocks = new int[][]{
                new int[]{1, 2},
                new int[]{3, 0}
        };
        Board finalBoard = new Board(finalBlocks);

        solver = new Solver(initialBoard);
        List<Board> solution = (List<Board>) solver.solution();

        assertEquals(2, solver.moves());
        assertTrue(solver.isSolvable());
        assertTrue(solution.get(0).equals(initialBoard));
        assertTrue(solution.get(2).equals(finalBoard));
    }

    @Test
    public void solver_ReturnsSolution0() {
        int[][] initialBlocks = new int[][]{
                new int[]{0, 3},
                new int[]{1, 2}
        };
        Board initialBoard = new Board(initialBlocks);

        int[][] finalBlocks = new int[][]{
                new int[]{1, 2},
                new int[]{3, 0}
        };
        Board finalBoard = new Board(finalBlocks);

        solver = new Solver(initialBoard);
        List<Board> solution = (List<Board>) solver.solution();

        assertEquals(-1, solver.moves());
        assertFalse(solver.isSolvable());
    }

    @Test
    public void solver_ReturnsSolution4() {
        int[][] initialBlocks = new int[][]{
                new int[]{0, 1, 3},
                new int[]{4, 2, 6},
                new int[]{7, 5, 8}
        };
        Board initialBoard = new Board(initialBlocks);

        int[][] finalBlocks = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0}
        };
        Board finalBoard = new Board(finalBlocks);

        solver = new Solver(initialBoard);
        List<Board> solution = (List<Board>) solver.solution();

        assertEquals(4, solver.moves());
        assertTrue(solver.isSolvable());
        assertTrue(solution.get(0).equals(initialBoard));
        assertTrue(solution.get(4).equals(finalBoard));
    }


}