package board;

import java.io.Serializable;

public abstract class Step implements Serializable {
    protected Location location;

    public Location getLocation() {
        return location;
    }

    public abstract void setPosition(Location loc);
}
