import edu.princeton.cs.algs4.MinPQ;

import java.util.Stack;


public class Solver {

    private class BoardKey implements Comparable<BoardKey> {
        private final BoardKey previous;
        private final Board board;
        private final int moves;
        private final int priority;

        public BoardKey(BoardKey previous, Board board, int moves) {
            this.previous = previous;
            this.board = board;
            this.moves = moves;
            this.priority = moves + board.manhattan();
        }

        public int compareTo(BoardKey that) {
            if (this.priority == that.priority) {
                return this.board.manhattan() - that.board.manhattan();
            }
            return this.priority - that.priority;
        }

        public BoardKey getPrevious() {
            return previous;
        }

        public int getMoves() {
            return moves;
        }

        public Board getBoard() {
            return board;
        }

        public boolean isGoal() {
            return board.isGoal();
        }
    }

    private MinPQ<BoardKey> queue;
    private int totalMoves;
    private Stack<Board> solution;
    private boolean isSolvable = true;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException("blocks is null");

        queue = new MinPQ<BoardKey>();
        queue.insert(new BoardKey(null, initial, 0));
        queue.insert(new BoardKey(null, initial.twin(), 0));

        BoardKey currKey = queue.delMin();
        while (!currKey.isGoal()) {
            Board currBoard = currKey.getBoard();
            BoardKey prevKey = currKey.getPrevious();
            for (Board neighbor : currBoard.neighbors()) {
                if (prevKey == null || !neighbor.equals(prevKey.getBoard())) {
                    queue.insert(new BoardKey(currKey, neighbor, currKey.getMoves() + 1));
                }
            }
            currKey = queue.delMin();
        }

        totalMoves = currKey.getMoves();
        solution = new Stack<Board>();
        solution.add(currKey.getBoard());

        while (currKey.getPrevious() != null) {
            currKey = currKey.getPrevious();
            solution.add(0, currKey.getBoard());
        }

        if (!currKey.getBoard().equals(initial)) {
            isSolvable = false;
            totalMoves = -1;
            solution = null;
        }

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of priority to solve initial board; -1 if unsolvable
    public int moves() {
        return totalMoves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }
}
