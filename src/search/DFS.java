package search;

import board.State;
import solver.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS implements Searcher {
    @Override
    public Solution search(Searchable searchable) {

        ArrayList<State> visitedStates = new ArrayList<>();
        Stack<State> stateStack = new Stack<>();
        State startState = searchable.getStartState();
        stateStack.push(startState);

        while(!stateStack.isEmpty()){
            State currentState = stateStack.pop();
            visitedStates.add(currentState);
            searchable.setCurrentState(currentState);
            if (searchable.isGoal(currentState)){
                return new Solution(currentState.getStateBefore());
            } else {
                List<State> allAvailableStates = searchable.getAllAvailableStates(currentState);

                for (State state: allAvailableStates) {

                    if (!stateStack.contains(state) && !visitedStates.contains(state)){
                        state.setStateBefore(currentState);
                        stateStack.push(state);
                    }
                }
            }
        }

        return null;
    }
}
