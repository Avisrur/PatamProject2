package solver;


import search.Searchable;

public interface Solver {

    Solution solve(Searchable searchable);
    Solution solve(String board);
}
