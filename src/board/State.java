package board;

/**
 * Created by lidor.rosencovich on 11/08/2018.
 */
public abstract class State {
    Board board;
    Location location;
    State stateBefore;
    Step stepToDo;

    public Board getBoard() {
        return board;
    }

    public Location getLocation() {
        return location;
    }

    public State getStateBefore() {
        return stateBefore;
    }

    public Step getStepToDo() {return this.stepToDo; }

    public abstract void setBoard(Board board);

    public void setLocation(Location location) {
        if (location != null) {
            this.location = location;
        }
    }

    public void setStateBefore(State stateBefore) {
        if (stateBefore != null) {
            this.stateBefore = stateBefore;
        }
    }

    public void setStepToDo(Step stepToDo) {
        if (stepToDo != null) {
            this.stepToDo = stepToDo;
        } else {
            this.stepToDo = null;
        }
    }

    public abstract Double heuristicFunction();
}
