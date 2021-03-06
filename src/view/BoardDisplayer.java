package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BoardDisplayer extends Canvas {
    private String board;
    private StringProperty FPipeFileName;
    private StringProperty LPipeFileName;
    private StringProperty JPipeFileName;
    private StringProperty sevenPipeFileName;
    private StringProperty dashPipeFileName;
    private StringProperty pipeLinePipeFileName;
    private StringProperty wallFileName;
    private PipeBoardView pipesBoard;
    private StringProperty startFileName;
    private StringProperty goalFileName;
    private double w;
    private double h;
    private double W;
    private double H;

    @Override
    public double minHeight(double width)
    {
        return 300;
    }

    @Override
    public double maxHeight(double width)
    {
        return 10000;
    }

    @Override
    public double prefHeight(double width)
    {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height)
    {
        return 300;
    }

    @Override
    public double maxWidth(double height)
    {
        return 100000;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height)
    {
        super.setWidth(width);
        super.setHeight(height);
        W = width;
        H = height;
        w = W / pipesBoard.getBoard()[0].length;
        h = H / pipesBoard.getBoard().length;

        PipeBoardView newBoard = new PipeBoardView(h, w, this.pipesBoard.toString());
        this.setBoard(newBoard);
    }

    public PipeBoardView getPipesBoard() {
        return pipesBoard;
    }

    public void setPipesBoard(PipeBoardView pipesBoard) {
        this.pipesBoard = pipesBoard;
    }

    public String getStartFileName() {
        return startFileName.get();
    }

    public void setStartFileName(String startFileName) {
        this.startFileName.set(startFileName);
    }

    public String getGoalFileName() {
        return goalFileName.get();
    }

    public void setGoalFileName(String goalFileName) {
        this.goalFileName.set(goalFileName);
    }

    public BoardDisplayer(){
        FPipeFileName = new SimpleStringProperty();
        LPipeFileName = new SimpleStringProperty();
        JPipeFileName = new SimpleStringProperty();
        sevenPipeFileName = new SimpleStringProperty();
        dashPipeFileName = new SimpleStringProperty();
        pipeLinePipeFileName = new SimpleStringProperty();
        wallFileName = new SimpleStringProperty();
        startFileName = new SimpleStringProperty();
        goalFileName = new SimpleStringProperty();
    }


    public String getWallFileName() {
        return wallFileName.get();
    }

    public void setWallFileName(String wallFileName) {
        this.wallFileName.set(wallFileName);
    }

    public void setFPipeFileName(String FPipeFileName) {
        this.FPipeFileName.set(FPipeFileName);
    }

    public void setLPipeFileName(String LPipeFileName) {
        this.LPipeFileName.set(LPipeFileName);
    }

    public void setJPipeFileName(String JPipeFileName) {
        this.JPipeFileName.set(JPipeFileName);
    }

    public void setSevenPipeFileName(String sevenPipeFileName) {
        this.sevenPipeFileName.set(sevenPipeFileName);
    }

    public void setDashPipeFileName(String dashPipeFileName) {
        this.dashPipeFileName.set(dashPipeFileName);
    }

    public void setPipeLinePipeFileName(String pipeLinePipeFileName) {
        this.pipeLinePipeFileName.set(pipeLinePipeFileName);
    }

    public String getFPipeFileName() {
        return FPipeFileName.get();
    }

    public String getLPipeFileName() {
        return LPipeFileName.get();
    }

    public String getJPipeFileName() {
        return JPipeFileName.get();
    }

    public String getSevenPipeFileName() {
        return sevenPipeFileName.get();
    }

    public String getDashPipeFileName() {
        return dashPipeFileName.get();
    }

    public String getPipeLinePipeFileName() {
        return pipeLinePipeFileName.get();
    }

    public void setBoard(String board){
        this.board = board;
        String[] arrBoard = board.split("\n");
        W = getWidth();
        H = getHeight();
        w = W / arrBoard[0].length();
        h = H / arrBoard.length - 1; // without the 'done'
        pipesBoard = new PipeBoardView(h,w,board);
        redraw();
    }

    public void setBoard(PipeBoardView pipesBoarda){
        pipesBoard = pipesBoarda;
        redraw();
    }

    public void redraw() {
        if (pipesBoard != null){

            Image FImage = null;
            Image LImage = null;
            Image JImage = null;
            Image sevenImage = null;
            Image dashImage = null;
            Image pipeLineImage = null;
            Image wallImage = null;
            Image startImage = null;
            Image goalImage = null;

            try {
                String path = new File(".").getCanonicalPath();
                System.out.println(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                FImage = new Image(new FileInputStream(new File(getFPipeFileName())));
                LImage = new Image(new FileInputStream(new File(getLPipeFileName())));
                JImage = new Image(new FileInputStream(new File(getJPipeFileName())));
                sevenImage = new Image(new FileInputStream(new File(getSevenPipeFileName())));
                dashImage = new Image(new FileInputStream(new File(getDashPipeFileName())));
                pipeLineImage = new Image(new FileInputStream(new File(getPipeLinePipeFileName())));
                wallImage = new Image(new FileInputStream(new File(getWallFileName())));
                startImage = new Image(new FileInputStream(new File(getStartFileName())));
                goalImage = new Image(new FileInputStream(new File(getGoalFileName())));
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, W, H);

            int rows = pipesBoard.getBoard().length;
            int cols = pipesBoard.getBoard()[0].length;

            for (int i = 0 ; i < rows; i++){
                for (int j = 0 ; j < cols; j++){
                    char cell = pipesBoard.getBoard()[i][j].getVal();
                    switch (cell){
                        case 'L': {
                            if (LImage != null){
                                gc.drawImage(LImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                        case 'F': {
                            if (FImage != null){
                                gc.drawImage(FImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                        case '7': {
                            if (sevenImage != null){
                                gc.drawImage(sevenImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                        case 'J': {
                            if (JImage != null){
                                gc.drawImage(JImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                        case '-': {
                            if (dashImage != null){
                                gc.drawImage(dashImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                        case '|': {
                            if (pipeLineImage != null){
                                gc.drawImage(pipeLineImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                        case 's': {
                            if (startImage != null){
                                gc.drawImage(startImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                        case 'g': {
                            if (goalImage != null){
                                gc.drawImage(goalImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                        case ' ': {
                            if (wallImage != null){
                                gc.drawImage(wallImage, j * w, i * h, w, h);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
