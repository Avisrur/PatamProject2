package view;

import board.Location;

import java.util.HashMap;

public class PipeBoardView {

    private String id;
    private PipeView[][] board;
    private Double height;
    private Double width;
    private Location start;
    private Location end;

    public PipeBoardView(Double height, Double width, PipeView[][] board) {
    }


    public PipeBoardView(Double height, Double width, PipeBoardView tmpBoard) {
        setWidthAndHeight(height, width);
        setBoard(tmpBoard.getBoard());
        setId(tmpBoard.toString());
        setStart(tmpBoard.findStartLocation());
        setEnd(tmpBoard.findEndLocation());
    }

    public PipeBoardView(Double height, Double width, String board) {
        setWidthAndHeight(height, width);
        setBoard(this.toBoard(board));
        setId(board);
        setStart(this.findStartLocation());
        setEnd(this.findEndLocation());
    }

    private void setWidthAndHeight(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    private void setId(String board) {
        this.id = String.valueOf(board.hashCode());
    }

    public String getId() {
        return id;
    }

    public void setId(PipeBoardView board) {
        this.id = String.valueOf(board.getId());
    }

    public PipeView[][] getBoard() {
        return board;
    }

    public PipeView getPipeView(Location loc) {
        if (loc != null && isValidLocation(loc)) {
            return this.board[loc.getRow()][loc.getCol()];
        }
        return null;
    }

    public PipeView getPipeView(Integer row, Integer col) {
        if (row < this.board.length && col < this.board[0].length && row >= 0 && col >= 0) {
            return this.board[row][col];
        }
        return null;
    }

    public Location findStartLocation() {
        for (int i = 0; i <= this.board.length; i++) {
            for (int j = 0; j <= this.board[0].length; j++) {
                if (this.board[i][j].getVal() == 's') {
                    return new Location(i, j);
                }
            }
        }
        return null;
    }

    public Location findEndLocation() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if (this.board[i][j].getVal() == 'g') {
                    return new Location(i, j);
                }
            }
        }
        return null;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = new Location(start);
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = new Location(end);
    }


    void setAllowedSteps() {

        HashMap<Character, String> top = new HashMap<Character, String>() {{
            put('|', "|gF7fG");
            put('L', "F7g|fG");
            put('J', "Fg|7Gf");
            put('s', "7F|gfG");
            put('S', "7F|gfG");
        }};
        HashMap<Character, String> bottom = new HashMap<Character, String>() {{
            put('|', "|gLJGjl");
            put('F', "|JgLjlG");
            put('7', "|JLgjlG");
            put('s', "LJ|gjlG");
            put('S', "LJ|gjlG");
        }};
        HashMap<Character, String> right = new HashMap<Character, String>() {{
            put('L', "J7g-Gj");
            put('F', "J7g-Gj");
            put('-', "J7g-Gj");
            put('s', "J7g-Gj");
            put('S', "J7g-Gj");
        }};
        HashMap<Character, String> left = new HashMap<Character, String>() {{
            put('-', "-gFLflG");
            put('J', "-gFLflG");
            put('7', "-gFLflG");
            put('S', "-gFLflG");
            put('s', "-gFLflG");
        }};
    }


    void setBoard(PipeView[][] tmpBoard) {
        try {
            Integer row = tmpBoard.length;
            Integer col = tmpBoard[0].length;
            this.board = new PipeView[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    this.board[i][j] = new PipeView(height, width, tmpBoard[i][j]);
                }
            }
        } catch (Exception e) {
            System.out.println("PipeViewsBoard.setBoard :" + e.getMessage());
        }
    }


    public PipeView[][] toBoard(String stringBoard) {
        PipeView[][] convertedBoard = null;
        try {
            String[] lines = stringBoard.split("\n");
            Integer rows = lines.length;
            Integer cols = lines[0].length();

            if (rows <= 0) {
                throw new Exception("rows are negative");
            }

            if (cols <= 0) {
                throw new Exception("cols are negative");
            }

            convertedBoard = new PipeView[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = lines[i];
                if (line == null) {
                    throw new Exception("line is null: " + i);
                }
                for (int j = 0; j < cols; j++) {
                    convertedBoard[i][j] = new PipeView(height, width, line.charAt(j), new Location(i, j));
                }
            }
        } catch (Exception e) {
            System.out.println("PipeViewsBoard.toBoard(): " + e.getMessage());
        }

        return convertedBoard;
    }


    public Boolean isValidLocation(Location loc) {
        return (loc != null & loc.getRow() < this.board.length && loc.getCol() < this.board[0].length && loc.getCol() >= 0 && loc.getRow() >= 0);
    }

    public boolean equals(PipeView[][] PipeViews) {
        boolean isEqual = false;
        Integer row = PipeViews.length;
        Integer col = PipeViews[0].length;
        try {
            compare:
            {
                for (Integer i = 0; i < row; i++) {
                    for (Integer j = 0; j < col; j++) {
                        isEqual = this.board[i][j].equals(PipeViews[i][j]);
                        if (!isEqual) break compare;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("MatrixBoard.equals(): Error details: " + ex.getMessage());
        }
        return isEqual;
    }
}
