package view;

import board.Location;

import java.util.HashMap;

public abstract class BoardView {

    String id;
    PipeView[][] board;
    Double height;
    Double width;

    public BoardView(Double height, Double width) {
        setWidthAndHeight(height,width);
        this.id = null;
        this.board = null;
    }

    public BoardView(Double height,Double width,PipeView[][] board){
        setWidthAndHeight(height,width);
        this.setId(board.toString());
        this.setBoard(board);
    }

    private void setWidthAndHeight(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    public String getId() {
        return id;
    }

    public void setId(String board) {
        this.id = String.valueOf(board.hashCode());
    }

    public void setId(BoardView board) {
        this.id = String.valueOf(board.getId());
    }

    public PipeView[][] getBoard() {
        return board;
    }

    abstract void setBoard(PipeView[][] tmpBoard);

    public abstract PipeView[][] toBoard(String stringBoard);

    public abstract Boolean isValidLocation(Location loc);

    @Override
    public int hashCode() {
        return 0;
    }
}
