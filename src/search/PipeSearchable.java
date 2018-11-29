package search;


import board.*;

import java.util.ArrayList;
import java.util.List;

public class PipeSearchable implements Searchable {

    State currentState;
    State startState;
    State endState;

    public PipeSearchable(State init) {
        this.currentState = new PipeGameState(init);
        this.startState = new PipeGameState(init);
        this.endState = null;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public State getStartState() {
        return startState;
    }

    @Override
    public State getEndState() {
        return endState;
    }

    @Override
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public void setInitialState(State initialState) {
        this.startState = initialState;
    }

    @Override
    public void setGoalState(State goalState) {
        this.endState = goalState;
    }

    @Override
    public boolean isGoal(State gameState) {
        try {
            return ((PipesBoard) gameState.getBoard()).getPipe(gameState.getLocation()).getVal() == 'g';
        } catch (Exception e) {
            //System.out.println("PipeGameState.isGoal() Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<State> getAllAvailableStates(State gameState) {
        List<State> availableStates = new ArrayList<>();

        try {
            PipeGameState PGS = new PipeGameState(gameState);
            PipesBoard currentBoard = new PipesBoard((PipesBoard) PGS.getBoard());
            Location currentLocation = PGS.getLocation();

            List<Location> allDirections = PGS.initDirections();
            for (Location dir : allDirections) {
                int maxRotations = 3;

                if (currentBoard.getPipe(dir) != null
                        && !currentBoard.getPipe(dir).isEmpty()
                        && !dir.equals(currentLocation)
                        && ((PGS.getStateBefore() != null && !dir.equals(PGS.getStateBefore().getLocation()))
                        || PGS.getStateBefore() == null)) {

                    for (int i = 0; i <= maxRotations; i++) {

                        if (i > 0 ) {
                            Pipe pipe = currentBoard.getPipe(dir);
                            if (pipe!= null){
                                pipe.rotate();
                            }
                        }

//                        boolean sameMove = false;
//                        if ( i == 2 || i == 3){
//                            Pipe tmpPipe = new Pipe(currentBoard.getPipe(dir));
//                            if (tmpPipe.getVal() == tmpPipe.rotate(2)){
//                                sameMove = true;
//                            }
//                        }
                        if (currentBoard.isValidMove(currentLocation, dir) /*&& !sameMove*/) {
                            PipeGameState neighbor = new PipeGameState(gameState);
                            PipeStep pipeStep = new PipeStep(dir, i);

                            if (gameState != null ){
                                ((PipesBoard)neighbor.getBoard()).getPipe(pipeStep.getLocation()).rotate(pipeStep.getNumOfRotations());
                                neighbor.setLocation(pipeStep.getLocation());
                                neighbor.setStepToDo(pipeStep);

                            }

                            availableStates.add(neighbor);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("PipesGameStep.getAllNeighbors(), Error: " + e.getMessage());
        }

        return availableStates;
    }
}