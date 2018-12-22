package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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


    public BoardDisplayer(){
        FPipeFileName = new SimpleStringProperty();
        LPipeFileName = new SimpleStringProperty();
        JPipeFileName = new SimpleStringProperty();
        sevenPipeFileName = new SimpleStringProperty();
        dashPipeFileName = new SimpleStringProperty();
        pipeLinePipeFileName = new SimpleStringProperty();
        wallFileName = new SimpleStringProperty();
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
        redraw();
    }


    public void redraw(){
        if (board != null){

            String[] arrBoard = board.split("\n");
            double W = getWidth();
            double H = getHeight();

            double w = W / arrBoard[0].length();
            double h = H / arrBoard.length - 1; // without the 'done'

            Image FImage = null;
            Image LImage = null;
            Image JImage = null;
            Image sevenImage = null;
            Image dashImage = null;
            Image pipeLineImage = null;
            Image wallImage = null;

            try {
                FImage = new Image(new FileInputStream(new File(getFPipeFileName())));
                LImage = new Image(new FileInputStream(new File(getLPipeFileName())));
                JImage = new Image(new FileInputStream(new File(getJPipeFileName())));
                sevenImage = new Image(new FileInputStream(new File(getSevenPipeFileName())));
                dashImage = new Image(new FileInputStream(new File(getDashPipeFileName())));
                pipeLineImage = new Image(new FileInputStream(new File(getPipeLinePipeFileName())));
                wallImage = new Image(new FileInputStream(new File(getWallFileName())));
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

            GraphicsContext gc = getGraphicsContext2D();

            gc.fillRect(0, 0, W, H);

            pipesBoard = new PipeBoardView(h,w,board);

            for (int i = 0 ; i < arrBoard.length - 1; i++){
                for (int j = 0 ; j < arrBoard[i].length(); j++){
                    char cell = pipesBoard.getBoard()[i][j].getVal();
                    switch (cell){
                        case 'L': {
                            if (LImage != null){
                                gc.drawImage(LImage, j * w, i * h, w, h);
                            }
                        }
                        case 'F': {
                            if (FImage != null){
                                gc.drawImage(FImage, j * w, i * h, w, h);
                            }
                        }
                        case '7': {
                            if (sevenImage != null){
                                gc.drawImage(sevenImage, j * w, i * h, w, h);
                            }
                        }
                        case 'J': {
                            if (JImage != null){
                                gc.drawImage(JImage, j * w, i * h, w, h);
                            }
                        }
                        case '-': {
                            if (dashImage != null){
                                gc.drawImage(dashImage, j * w, i * h, w, h);
                            }
                        }
                        case '|': {
                            if (pipeLineImage != null){
                                gc.drawImage(pipeLineImage, j * w, i * h, w, h);
                            }
                        }
                        case ' ': {
                            if (wallImage != null){
                                gc.drawImage(wallImage, j * w, i * h, w, h);
                            }
                        }
                    }
                }
            }
        }
    }
}
