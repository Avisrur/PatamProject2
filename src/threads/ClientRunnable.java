package threads;

import client.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientRunnable implements Runnable {
    String EXIT_STR = "done";
    private Socket clientSocket;
    private OutputStream outTC;
    private String level;
    private BufferedReader reader;
    ClientHandler ch;

    public ClientRunnable(Socket cs,ClientHandler ch) {
        this.ch = ch;
        this.clientSocket = cs;
        try {
            this.outTC = this.clientSocket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException("ClientRunnable.CTOR()"+e.toString());
        }
        this.getBoard();
    }

    public int getBoardSize() {
        String[] rows = this.level.split("\n");
        return rows[0].length() * rows.length;
    }

    private void getBoard() {
        ArrayList<String> lines = new ArrayList<>();
        String line;

        try {

            this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            while (!(line = this.reader.readLine()).equals(EXIT_STR)) {
                lines.add(line);
            }
            this.level = String.join(System.lineSeparator(), lines);

        } catch (IOException exception) {
            System.out.println("ClientRunnable.getBoard()"+ exception.toString());
        }
    }

    @Override
    public void run() {
        try {
            this.ch.handle(new PrintWriter(this.outTC), this.level);
            clientSocket.close();
            this.reader.close();
        } catch (Exception ex) {
            System.out.println("ClientRunnable.run()" + ex.getMessage());
        }
    }
}