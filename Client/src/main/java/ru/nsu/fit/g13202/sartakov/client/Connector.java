package ru.nsu.fit.g13202.sartakov.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by arty on 30.04.15.
 */
public class Connector {
    private String hostName;
    private int portNumber;
    private Socket s;

    public Connector(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public void connect() throws IOException {
        s = new Socket(hostName, portNumber);
    }

    public Socket getSocket() {
        return s;
    }

    public void finish() throws IOException {
        s.close();
    }
}


