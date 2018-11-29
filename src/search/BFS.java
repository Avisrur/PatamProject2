package search;

import board.State;
import solver.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS implements Searcher {
    @Override
    public Solution search(Searchable searchable) {

        List<State> visitedStates = new ArrayList<>();
        Queue queue = new LinkedList();
        State startState = searchable.getStartState();
        queue.add(startState);

        while (!queue.isEmpty()) {

            State currentState = (State) queue.poll();
            visitedStates.add(currentState);

            searchable.setCurrentState(currentState);

            if (searchable.isGoal(currentState)) {
                return new Solution(currentState.getStateBefore());
            } else {
                List<State> allAvailableStates = searchable.getAllAvailableStates(currentState);

                for (State state : allAvailableStates) {


                    if (!visitedStates.contains(state) && !queue.contains(state)) {
                        state.setStateBefore(currentState);
                        queue.add(state);
                    }
                }
            }
        }

        return null;
    }
}
