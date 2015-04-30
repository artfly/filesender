package ru.nsu.fit.g13202.sartakov.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by arty on 30.04.15.
 */
public class Connector {
    ServerSocket ss;
    Socket clientSocket;
    Connector (int portNumber) throws IOException {
        ss = new ServerSocket(portNumber);
    }

    public boolean connect () throws IOException {
        clientSocket = ss.accept();
        return true;
    }

    public Socket getSocket () {
        return clientSocket;
    }

    public void finish () throws IOException {
        clientSocket.close();
    }
}