package board;

import java.util.ArrayList;
import java.util.List;

public class PipeGameState extends State {

    public PipeGameState(PipesBoard board) {
        this.setBoard(board);
        this.setLocation(null);
        this.setStepToDo(null);
        this.setStateBefore(null);
    }

    public PipeGameState(State pipeGameState) {
        if (pipeGameState != null) {
            this.setBoard(pipeGameState.getBoard());
            this.setStateBefore(pipeGameState.getStateBefore());
            this.setLocation(pipeGameState.getLocation());
            this.setStepToDo(pipeGameState.getStepToDo());
        }
    }

    @Override
    public void setStepToDo(Step stepToDo) {
        if (stepToDo == null || ((PipeStep)stepToDo).getNumOfRotations() == 0){
            super.setStepToDo(null);
            return;

        }

        super.setStepToDo(stepToDo);
    }

    @Override
    public void setLocation(Location location) {
        if (location != null ){
            super.setLocation(location);
        } else {
            super.setLocation(((PipesBoard)this.getBoard()).getStart());
        }
    }

    @Override
    public void setBoard(Board board) {
        if (board != null){
            this.board = new PipesBoard((PipesBoard)board);
        }
    }

    public List<Location> initDirections() {
        Location currentLocation = this.getLocation();

        List<Location> directions = new ArrayList<>();

        directions.add(new Location(currentLocation.getUp()));
        directions.add(new Location(currentLocation.getDown()));
        directions.add(new Location(currentLocation.getLeft()));
        directions.add(new Location(currentLocation.getRight()));

        return directions;
    }

    @Override
    public Double heuristicFunction() {
        double price = 0;
        try {
            Location end = ((PipesBoard)this.getBoard()).findEndLocation();
            Location currentLocation = this.getLocation();
            price = Math.sqrt(Math.pow(currentLocation.getRow() - end.getRow(), 2) + Math.pow(currentLocation.getCol() - end.getCol(), 2));
        } catch (Exception ex) {
            System.out.println("PipeGameState.heuristicFunction() Error: " + ex.getMessage());
        }
        return price;
    }

    public boolean equals(State state) {
        if (state == null) { return false; }
        return this.board.equals(state.board);
    }
}
