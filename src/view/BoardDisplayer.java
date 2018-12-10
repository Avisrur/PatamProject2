package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by lidor.rosencovich on 10/12/2018.
 */
public class BoardDisplayer extends Canvas {

    String board;

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
            double h = H / arrBoard.length;

            GraphicsContext gc = getGraphicsContext2D();

            for (int i = 0 ; i < arrBoard.length; i++){
                for (int j = 0 ; j < arrBoard[i].length(); j++){
                    char cell = arrBoard[i].charAt(j);
                    switch (cell){
                        case 'L': {
                            gc.fillRect(j * w, i * h, w, h);
                        }
                        case 'F': {

                        }
                        case '7': {

                        }
                        case 'J': {

                        }
                        case '-': {

                        }
                        case '|': {

                        }
                    }
                }
            }


        }
    }
}
