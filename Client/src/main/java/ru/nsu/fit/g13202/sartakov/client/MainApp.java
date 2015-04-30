package ru.nsu.fit.g13202.sartakov.client;

/**
 * Created by arty on 30.04.15.
 */
public class MainApp {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage : client <path/to/file> <server_name> <port>");
            System.exit(1);
        }
        String hostName = args[1];
        int portNumber = Integer.parseInt(args[2]);
        Client c = new Client(args[0], hostName, portNumber);
        c.start();
    }
}
