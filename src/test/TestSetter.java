package test;

import cache.CacheManager;
import cache.FileHandler;
import client.ClientHandler;
import client.MyClient;
import search.BestFirstSearch;
import search.PipeSearchable;
import search.Searchable;
import search.Searcher;
import server.MyServer;
import server.Server;
import solver.PipeGameSolver;
import solver.Solver;

import java.util.Arrays;
import java.util.List;

public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		// server's implementation
		dt.setServerClass(MyServer.class);
		// client handler interface
		dt.setClientHandlerInterface(ClientHandler.class);
		// client handler class
		dt.setClientHandlerClass(MyClient.class);
		// cache manager interface
		dt.setCacheManagerInterface(CacheManager.class);
		// cache manager class
		dt.setCacheManagerClass(FileHandler.class);
		// solver interface
		dt.setSolverInterface(Solver.class);
		// solver class
		dt.setSolverClass(PipeGameSolver.class);
		// searchable interface
		dt.setSearchableInterface(Searchable.class);
		// searcher interface
		dt.setSearcherInterface(Searcher.class);
		// your searchable pipe game class
		dt.setPipeGameClass(PipeSearchable.class);
		// your Best First Search implementation
		dt.setBestFSClass(BestFirstSearch.class);
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port){
		s=new MyServer(port,5);
		s.start();
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}
	
	/* ------------- Best First Search Test --------------
	 * You are given a Word Game
	 * Create a new Searchable from the Word Game
	 * Solve the Word Game
	 * and return a list of index switches as strings
	 * e.g., {"0,5" , "3,4" , "2,1"}
	 *  
	 */
	
	public static List<String> solveWordGame(WordGame tp) {
		// of course, you should replace this code with a calculated answer...
		List<String> sol=Arrays.asList("0,3","1,3");
		return sol;
	}

}
