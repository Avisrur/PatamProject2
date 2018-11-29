package server;


import client.ClientHandler;

public interface Server {
    void start(ClientHandler ch);
    void stop();
}

