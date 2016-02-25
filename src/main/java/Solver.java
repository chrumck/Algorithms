import java.util.Stack;

public class Solver {

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {


    }

    // is the initial board solvable?
    public boolean isSolvable(){
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        return 0;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        return new Stack<Board>();
    }
}
