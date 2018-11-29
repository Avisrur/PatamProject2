package cache;

import solver.Solution;

public interface CacheManager {
    void saveSolvedLevel(Solution solution);
    Solution loadSolvedLevel();
}
