package board;

import java.io.Serializable;

public class PipeStep extends Step implements Serializable {

    Integer numOfRotations;

    public PipeStep(Location loc, Integer numToRotate){
        this.location = new Location(loc);
        this.numOfRotations = numToRotate;
    }

    public Integer getNumOfRotations(){ return numOfRotations; }

    public void setNumOfRotations(Integer numToRotate) {
        this.numOfRotations = numToRotate;
    }

    public void setPosition(Location loc){
        if (loc != null) {
            this.location = new Location(loc);
        }
    }
}
