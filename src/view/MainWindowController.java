package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    // Just for test
    String boardData = "s|-L\nL|-L\nL--g\ndone";

    @FXML BoardDisplayer boardDisplayer;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.boardDisplayer.setBoard(boardData);

        this.boardDisplayer.setOnMouseClicked(event -> {
            double x = event.getSceneX();
            double y = event.getSceneY();

            HashMap<String, Number>[][] boardCoordinates = this.boardDisplayer.getCoordinatesPixels();

            // extract the clicked image.
            //if ()
        });

        this.boardDisplayer.widthProperty().addListener((observableValue) ->
                this.boardDisplayer.redraw());
        this.boardDisplayer.heightProperty().addListener((observableValue) ->
                this.boardDisplayer.redraw());
    }
    public void Message(String n) {
        switch(n){
            case "1": {
                // Victory
                break;
            }
            case "2": {
                // Lose
                break;
            }
            case "3": {
                // Error
                break;
            }
        }
    }

    public void changeTheme(){
        System.out.println("hit");
    }
    public void showTopTen(){}
    public void loadLevel(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Level");
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Text files only","*.txt"));
        File chosen = fc.showOpenDialog(null);

        if (chosen != null){
            System.out.println(chosen.getName());
        }
    }
    public void saveLevel(){}

    public String[] getStepsToSolve(){
        String[] arrBoard = boardData.split("\n");
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

            out.flush();

            return in.readLine().split("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void solveLevel(){
        String[] steps = getStepsToSolve();

        for (int i = 0 ; i < steps.length; i++){

        }
    }
}
