package server;

import client.ClientHandler;
import threads.ClientRunnable;
import threads.ShortestJobFirst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class MyServer implements Server {

    final int port;
    final int threadPoolSize;
    boolean stop = false;
    final int QUEUE_SIZE = 100;
    ShortestJobFirst threadQueueHandler;

    public MyServer(int port, int threadPoolSize) {
        this.threadPoolSize=threadPoolSize;
        this.port = port;
        this.threadQueueHandler = new ShortestJobFirst(threadPoolSize,QUEUE_SIZE);
    }

    public void start(ClientHandler clientHandler) {
        new Thread(() -> {
            try {
                begin(clientHandler);
            } catch (Exception e) {
                System.out.println("MyServer.start() Error: " + e.getMessage());
            }
        }).start();

    }

    public void begin(ClientHandler ch) throws IOException {

        ServerSocket theServer = new ServerSocket(this.port);
        theServer.setSoTimeout(1000000);
        System.out.println("Connected");
        while(!stop){
            try {
                Socket client = theServer.accept();
                System.out.println("Client connected");
                threadQueueHandler.getPriorityQueue().add(new ClientRunnable(client,ch));
            } catch (SocketTimeoutException e){
                System.out.println("MyServer.begin() Error: " + e.getMessage());
            }
        }
        theServer.close();
        //threadQueueHandler.close();
    }

    @Override
    public void stop() {
        stop = true;
    }
}
