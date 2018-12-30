package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    // initialize level
    String boardData = "s-F\n--g\ndone";
    int level = 1;
    String EXIT_STR = "done";
    String currentTheme = "Light";
    MediaPlayer bgMusic = null;

    @FXML BoardDisplayer boardDisplayer;
    @FXML Label numberOfSteps;
    @FXML Label timerLabel;
    private int time;
    private int steps;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.boardDisplayer.setBoard(boardData);
        this.changeBackgroundMusic();

        this.boardDisplayer.setOnMouseClicked(event -> {
            PipeBoardView board = this.boardDisplayer.getPipesBoard();
            double x = event.getSceneX();
            double y = event.getSceneY();

            PipeView pipe = board.getPipeViewByPixels(x, y);

            if (pipe != null){
                pipe.rotate();
                this.boardDisplayer.setBoard(board);

                this.steps++;
                numberOfSteps.setText(Integer.toString(this.steps));
            }
        });

        this.time = 0;
        this.steps = 0;
        timerLabel.setText(Integer.toString(this.time));
        numberOfSteps.setText(Integer.toString(this.steps));

        Runnable runnable = this::stopWatchLoop;
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void changeBackgroundMusic()
    {
        if (this.bgMusic != null) {
            this.bgMusic.stop();
        }
        String musicFileName = "./resources/themes/" + this.currentTheme + "/bg.mp3";
        Media song = new Media(new File(musicFileName).toURI().toString());
        this.bgMusic = new MediaPlayer(song);
        this.bgMusic.setOnEndOfMedia(new Runnable() {
            public void run() {
                bgMusic.seek(Duration.ZERO);
            }
        });
        this.bgMusic.play();
    }

    public void changeTheme(){
        if (currentTheme.equals("Dark")){
            currentTheme = "Light";
        } else {
            currentTheme = "Dark";
        }

        this.boardDisplayer.setDashPipeFileName("./resources/Themes/" + currentTheme + "/-Pipe.png");
        this.boardDisplayer.setFPipeFileName("./resources/Themes/" + currentTheme + "/FPipe.png");
        this.boardDisplayer.setJPipeFileName("./resources/Themes/" + currentTheme + "/JPipe.png");
        this.boardDisplayer.setLPipeFileName("./resources/Themes/" + currentTheme + "/LPipe.png");
        this.boardDisplayer.setSevenPipeFileName("./resources/Themes/" + currentTheme + "/7Pipe.png");
        this.boardDisplayer.setPipeLinePipeFileName("./resources/Themes/" + currentTheme + "/pipeLinePipe.png");
        this.boardDisplayer.setStartFileName("./resources/Themes/" + currentTheme + "/Start.png");
        this.boardDisplayer.setGoalFileName("./resources/Themes/" + currentTheme + "/Goal.png");

        this.changeBackgroundMusic();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void showTopTen(){}
    public void loadLevel(){
        LevelCurrentState state = FileHandler.LoadSolution();
        if (state != null) {
            this.setLevel(state.getLevel());
            this.steps = state.getSteps();
            numberOfSteps.setText(Integer.toString(this.steps));
            this.time = state.getTime();
            this.boardDisplayer.setBoard(state.getBoard());
        }
    }
    public void saveLevel(){
        LevelCurrentState curr = new LevelCurrentState(this.boardDisplayer.getPipesBoard().toString(), this.level, this.steps, this.time);
        FileHandler.saveState(curr);
    }

    public ArrayList<String> getStepsToSolve(){
        String[] arrBoard = this.boardDisplayer.getPipesBoard().toString().split("\n");
        int rows = arrBoard.length; // including the done (for the server)

        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int port = 8000;
        try {
            s = new Socket("127.0.0.1", port);
            try {
                s.setSoTimeout(311000);
            } catch (SocketException e) {
                e.printStackTrace();
            }

            out = new PrintWriter(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            for (int i = 0 ; i < rows ; i++){

                out.println(arrBoard[i]);

            }

            out.println("done");
            out.flush();

            ArrayList<String> steps = new ArrayList<String>();

            String line;
            while (!(line = in.readLine()).equals(EXIT_STR)) {
                steps.add(line);
            }

            steps.add(EXIT_STR);
            return steps;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void solveLevel() throws InterruptedException {
        Runnable runnable =  () -> {
            ArrayList<String> steps = getStepsToSolve();

            for (int i = 0 ; i < steps.size(); i++){
                if (!steps.get(i).equals(EXIT_STR)){
                    animateStep(steps.get(i));
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void animateStep(String step){
        String[] splitted = step.split(",");
        int row = Integer.parseInt(splitted[0]);
        int col = Integer.parseInt(splitted[1]);
        int numOfRotations = Integer.parseInt(splitted[2]);

        for (int i = 0; i < numOfRotations ; i++) {
            this.boardDisplayer.getPipesBoard().getPipeView(row, col).rotate();
            boardDisplayer.redraw();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //this.boardDisplayer.redraw();
    }

    public void handleDone() {
        String strinBoard = this.boardDisplayer.getPipesBoard().toString();

        if (this.getStepsToSolve().get(0).equals("done")){
            MessageBoxDisplayer.showInfo("Level is solved", "Hooray!", "Great job!");
        } else {
            MessageBoxDisplayer.showInfo("incorrect solution", "Failed", "Try again");
        }
    }

    private void stopWatchLoop()
    {
        while(true)
        {
            try {
                Thread.sleep(1000);
                this.time++;
                Platform.runLater(
                        () -> {
                            timerLabel.setText(Integer.toString(this.time));
                        }
                );

            } catch (InterruptedException e) { }
        }
    }

    public void closeWindow() {
        Main.active.close();
    }
}
