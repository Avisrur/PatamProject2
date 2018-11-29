package board;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Board{

    String id;
    Pipe[][] board;
    HashMap<String,HashMap<Character,String>> allowedSteps;

    public Board() {
        this.id = null;
        this.board = null;
        this.setAllowedSteps();
    }

    public Board(Pipe[][] board){
        this.setId(board.toString());
        this.setBoard(board);
        this.setAllowedSteps();
    }

    abstract void setAllowedSteps();

    public String getId() {
        return id;
    }

    public void setId(String board) {
        this.id = String.valueOf(board.hashCode());
    }

    public void setId(Board board) {
        this.id = String.valueOf(board.getId());
    }

    public Pipe[][] getBoard() {
        return board;
    }

    public HashMap<String, HashMap<Character, String>> getAllowedSteps() {
        return allowedSteps;
    }

    public void setAllowedSteps(HashMap<String, HashMap<Character, String>> allowedSteps) {
        this.allowedSteps = allowedSteps;
    }

    abstract void setBoard(Pipe[][] board);

    public abstract boolean isValidMove(Location x,Location y);

    public abstract Pipe[][] toBoard(String board);

    public abstract Boolean isValidBoard();

    public abstract Boolean isValidLocation(Location loc);

    @Override
    public int hashCode() {
        return 0;
    }
}
