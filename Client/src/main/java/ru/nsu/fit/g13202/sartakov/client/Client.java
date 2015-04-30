package ru.nsu.fit.g13202.sartakov.client;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by arty on 30.04.15.
 */
public class Client {
    private String filename;
    private String hostName;
    private int portNumber;

    public Client (String filename, String hostName, int portNumber) {
        this.filename = filename;
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public void start () {
        Connector c = new Connector(hostName, portNumber);
        try {
            c.connect();
        } catch (UnknownHostException e) {
            System.err.println("error : host wasn't found");
            return;
        } catch (IOException e) {
            System.err.println("error : couldn't get IO connection with client");
            return;
        }
        Sender s = null;
        try {
            s = new Sender(c.getSocket().getOutputStream(), c.getSocket().getInputStream(), filename);
            if (s.send()) {
                System.out.println("All done.");
            }
            else {
                System.out.println("Fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                c.finish();
                if (s != null) {
                    s.finish();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
