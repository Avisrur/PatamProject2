package solver;


import board.State;
import board.Step;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution implements Serializable {

    List<Step> solutionSteps;

    public Solution(State stateInput) {
        State state = stateInput;
        this.solutionSteps = new ArrayList<>();
        if (state != null) {
            while(state.getStateBefore() != null) {
                if (state.getStepToDo() != null && this.solutionSteps != null) {
                    this.solutionSteps.add(state.getStepToDo());
                }

                state = state.getStateBefore();
            }

            Collections.reverse(this.solutionSteps);
        }
    }

    public Solution(Solution solution) {
        this.setSolutionSteps(solution.getSolutionSteps());
    }

    public Solution() {
        super();
        this.setSolutionSteps(null);
    }

    public List<Step> getSolutionSteps() {
        return solutionSteps;
    }

    public void setSolutionSteps(List<Step> solutionSteps) {
        this.solutionSteps = solutionSteps;
    }

}
