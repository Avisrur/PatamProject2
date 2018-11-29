package solver;


import board.PipeGameState;
import board.PipesBoard;
import search.PipeSearchable;
import search.Searchable;
import search.Searcher;

public class PipeGameSolver implements Solver {
    Searcher searcher;

    public PipeGameSolver(Searcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public Solution solve(Searchable searchable) {
        return searcher.search(searchable);
    }

    @Override
    public Solution solve(String board) {
        PipesBoard boardObj = new PipesBoard(board);
        PipeGameState gameCurrentState = new PipeGameState(boardObj);
        PipeSearchable searchable = new PipeSearchable(gameCurrentState);
        return this.solve(searchable);
    }
}
