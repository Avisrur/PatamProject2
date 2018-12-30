package client;


import board.Board;
import board.PipeStep;
import board.PipesBoard;
import board.Step;
import cache.CacheManager;
import cache.FileHandler;
import search.BFS;
import solver.PipeGameSolver;
import solver.Solution;
import solver.Solver;

import java.io.PrintWriter;

public class MyClient implements ClientHandler {

    private CacheManager fileCacheMamager;
    private Solver pipeGameSolver;
    private Solution solvedLevel;
    private Board board;

    public MyClient(){
        this.pipeGameSolver = new PipeGameSolver(new BFS());
    }

    /*public MyClient(CacheManager fileCacheMamager, Solver pipeGameSolver) {
        this.board = new char[][]{{'s', '-', '7', ' '}, {' ', '|', 'L', '7'}, {'-', 'F', ' ', '|'}, {'7', 'F', '-', 'J'}, {' ', 'g', ' ', '-'}};
    }*/


    @Override
    public void handle(PrintWriter outTC,String level) {

        try {
            this.board = new PipesBoard(level);
        } catch (Exception e) {
            System.out.println("MyCLient.handle() Error: " + e.getMessage());
            return;
        }
        this.fileCacheMamager = new FileHandler(this.board.getId());
        this.solvedLevel = null;
        try {
            //this.solvedLevel = this.fileCacheMamager.loadSolvedLevel();
        } catch (Exception e){
            this.solvedLevel = null;
        }
        if(this.solvedLevel == null){
            this.solvedLevel = this.pipeGameSolver.solve(level);
            try {
                fileCacheMamager.saveSolvedLevel(this.solvedLevel);
            } catch (Exception e){
                System.out.println("MyClient.handle() Error: " + e.getMessage());
            }
        }

        for (Step pStep: this.solvedLevel.getSolutionSteps()) {
            outTC.println(pStep.getLocation().getRow().toString()
                    + ","
                    + pStep.getLocation().getCol().toString()
                    + ","
                    + ((PipeStep)pStep).getNumOfRotations().toString());
            System.out.println(pStep.getLocation().getRow().toString()
                    + ","
                    + pStep.getLocation().getCol().toString()
                    + ","
                    + ((PipeStep)pStep).getNumOfRotations().toString());
        }

        outTC.println("done");
        outTC.flush();

        clean(outTC);
    }

    private void clean(PrintWriter outTC) {
        if (outTC != null) {
            outTC.close();
        }
    }
}

