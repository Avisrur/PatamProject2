package search;


import board.State;

import java.util.List;

public interface Searchable {


    State getCurrentState();

    State getStartState();

    State getEndState();

    void setCurrentState(State currentState);

    void setInitialState(State initialState);

    void setGoalState(State goalState);

    boolean isGoal(State gameState);

    List<State> getAllAvailableStates(State gameState);

}
