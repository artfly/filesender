package ru.nsu.fit.g13202.sartakov.server;

/**
 * Created by arty on 30.04.15.
 */
public class MainApp {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage : server <port>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);

        Server s = new Server (portNumber);
        s.start ();
    }
}
