package ru.nsu.fit.g13202.sartakov.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by arty on 30.04.15.
 */
public class Server {
    private int portNumber;
    Server (int portNumber) {
        this.portNumber = portNumber;
    }

    public void start () {
        Connector c = null;
        Receiver r = null;
        try {
            c = new Connector(portNumber);
            while (c.connect()) {
                try {
                    r = new Receiver(c.getSocket().getInputStream(), c.getSocket().getOutputStream());
                    r.receive();
                } catch (IOException e) {
                    System.err.println("Connection with client failed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (r != null)
                    r.finish();
                if (c != null)
                    c.finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
