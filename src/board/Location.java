package board;


import java.io.Serializable;

public class Location implements Serializable {
    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Location(Location location) {
        this.row = location.getRow();
        this.col = location.getCol();
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;

    }

    public Location getUp() {
        return new Location(this.getRow() - 1, this.col);
    }

    public Location getDown() {
        return new Location(this.getRow() + 1, this.col);
    }

    public Location getRight() {
        return new Location(this.row, this.col + 1);
    }

    public Location getLeft() {
        return new Location(this.row, this.getCol() - 1);

    }

    public boolean equals(Location loc) {
        return row == loc.row &&
                col == loc.col;
    }

}
