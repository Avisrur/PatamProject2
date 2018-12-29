package board;

import java.util.HashMap;

public class PipesBoard extends Board {

    private Location start;
    private Location end;

    public PipesBoard(Pipe[][] board) {
        super(board);
    }

    public PipesBoard(PipesBoard tmpBoard) {
        setBoard(tmpBoard.getBoard());
        setId(tmpBoard);
        setStart(tmpBoard.findStartLocation());
        setEnd(tmpBoard.findEndLocation());
    }

    public PipesBoard(String board) {
        super();
        setBoard(this.toBoard(board));
        setId(board);
        setStart(this.findStartLocation());
        setEnd(this.findEndLocation());
    }

    public Pipe getPipe(Location loc) {
        if (loc != null && isValidLocation(loc)) {
            return this.board[loc.getRow()][loc.getCol()];
        }
        return null;
    }

    public Pipe getPipe(Integer row, Integer col) {
        if (row < this.board.length && col < this.board[0].length && row >= 0 && col >= 0) {
            return this.board[row][col];
        }
        return null;
    }

    public Location findStartLocation() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
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

    @Override
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

        this.allowedSteps = new HashMap<String, HashMap<Character, String>>() {{
            put("top", top);
            put("bottom", bottom);
            put("left", left);
            put("right", right);
        }};
    }

    @Override
    void setBoard(Pipe[][] tmpBoard) {
        try {
            Integer row = tmpBoard.length;
            Integer col = tmpBoard[0].length;
            this.board = new Pipe[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    this.board[i][j] = new Pipe(tmpBoard[i][j]);
                }
            }
        } catch (Exception e) {
            System.out.println("PipesBoard.setBoard :" + e.getMessage());
        }
    }

    @Override
    public boolean isValidMove(Location x, Location y) {
        try {
            if (this.isValidBoard() && this.isValidLocation(x) && this.isValidLocation(y)) {
                String direction = this.getDirection(x, y);
                if (direction != null) {
                    String allowedStep = this.allowedSteps.get(direction).get(this.getPipe(x).getVal());
                    if (allowedStep != null) {
                        return allowedStep.contains(this.getPipe(y).getVal().toString());
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("PipesBoard.isValidMove(): " + ex.getMessage());
        }
        return false;
    }

    private String getDirection(Location x, Location y) {
        try {
            // In case we move up or down
            if (x.getCol() == (y.getCol())) {
                if (x.getRow() + 1 == y.getRow()) {
                    return "bottom";
                }
                if (x.getRow() - 1 == y.getRow()) {
                    return "top";
                }
                // In case we move right or left
            } else if (x.getRow() == (y.getRow())) {
                if (x.getCol() + 1 == y.getCol()) {
                    return "right";
                }
                if (x.getCol() - 1 == y.getCol()) {
                    return "left";
                }
            }
        } catch (Exception ex) {
            System.out.println("PipesBoard.getDirection(): " + ex.getMessage());
        }
        return null;
    }

    @Override
    public Pipe[][] toBoard(String stringBoard) {
        Pipe[][] convertedBoard = null;
        try {
            String[] lines = stringBoard.split("\n");
            Integer rows = lines.length;
            Integer cols = lines[0].length();

            if (rows <= 0) {
                throw new Exception("rows are negative");
            }

            if (cols <= 0){
                throw new Exception("cols are negative");
            }

            convertedBoard = new Pipe[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = lines[i];
                if (line == null) {
                    throw new Exception("line is null: " + i);
                }
                for (int j = 0; j < cols; j++) {
                    convertedBoard[i][j] = new Pipe(line.charAt(j),new Location(i, j));
                }
            }
        } catch (Exception e){
            System.out.println("PipesBoard.toBoard(): " + e.getMessage());
        }

        return convertedBoard;
    }

    @Override
    public Boolean isValidBoard() {
        return (this.board != null && this.board.length > 0 && this.board[0].length > 0);
    }

    @Override
    public Boolean isValidLocation(Location loc) {
        return (loc != null & loc.getRow() < this.board.length && loc.getCol() < this.board[0].length && loc.getCol() >= 0 && loc.getRow() >= 0);
    }

    public boolean equals(Pipe[][] pipes) {
        boolean isEqual = false;
        Integer row = pipes.length;
        Integer col = pipes[0].length;
        try {
            compare:
            {
                for (Integer i = 0; i < row; i++) {
                    for (Integer j = 0; j < col; j++) {
                        isEqual = this.board[i][j].equals(pipes[i][j]);
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
