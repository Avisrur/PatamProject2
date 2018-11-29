package search;

import board.State;
import solver.Solution;

import java.util.ArrayList;
import java.util.List;

public class BestFirstSearch implements Searcher{
    @Override
    public Solution search(Searchable searchable) {
        MinHeap<State> minHeap = new MinHeap();
        List<State> visitedStates = new ArrayList<>();
        State startState = searchable.getStartState();

        minHeap.insert(new HeapNode<State>(startState, startState.heuristicFunction()));

        while (!minHeap.isEmpty()){
            State currentState = minHeap.extractMin().getNode();
            visitedStates.add(currentState);

            searchable.setCurrentState(currentState);

            if (searchable.isGoal(currentState)) {
                return new Solution(currentState.getStateBefore());
            } else {
                List<State> allAvailableStates = searchable.getAllAvailableStates(currentState);

                for (State state : allAvailableStates) {
                    state.setStateBefore(currentState);

                    HeapNode<State> node = new HeapNode<>(state, state.heuristicFunction());
                    if (!visitedStates.contains(state) && !minHeap.contains(node)) {
                        state.setStateBefore(currentState);
                        minHeap.insert(node);
                    }
                }
            }
        }

        return null;
    }


}
