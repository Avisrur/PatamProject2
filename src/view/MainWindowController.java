package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    // Just for test
    String boardData = "s|-L\nL|-L\nL--g\ndone";

    @FXML
    BoardDisplayer boardDisplayer;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.boardDisplayer.setBoard(boardData);

        this.boardDisplayer.setOnMouseClicked(event -> {
            double x = event.getSceneX();
            double y = event.getSceneY();

//            HashMap<String, Number>[][] boardCoordinates = this.boardDisplayer.getCoordinatesPixels();

            // extract the clicked image.
//            if ()
        });
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
    public void solveLevel(){}
}
