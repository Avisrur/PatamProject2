package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LevelCurrentState {
    String board;
    int level;
    int steps;
    int time;


    public LevelCurrentState(String board, int level, int steps, int time)
    {
        this.board = board;
        this.level = level;
        this.steps = steps;
        this.time = time;
    }

    public String getBoard() {return this.board;}
    public int getLevel() {return this.level;}
    public int getSteps() {return this.steps;}
    public int getTime() {return this.time;}

    public static LevelCurrentState fromFile(String path)
    {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            String board =in.readUTF();
            int level = in.readInt();
            int steps = in.readInt();
            int time = in.readInt();
            return new LevelCurrentState(board, level, steps, time);
        }
        catch (Exception e) {
            return null;
        }
    }

    public void toFile(String path)
    {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeUTF(this.board);
            out.writeInt(this.level);
            out.writeInt(this.steps);
            out.writeInt(this.time);
            out.flush();
            out.close();
        }
        catch (Exception e) {
        }
    }
}
