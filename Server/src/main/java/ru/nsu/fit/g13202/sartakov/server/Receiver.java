package ru.nsu.fit.g13202.sartakov.server;

import java.io.*;
import java.util.Arrays;

/**
 * Created by arty on 30.04.15.
 */
public class Receiver {
    private DataInputStream dis;
    private int size = 0;
    private static final int BUFSIZE = 4096;
    private byte[] chunk = new byte[BUFSIZE];
    private State state = State.GETNAME;
    private long fullSize = 0;
    private long currSize = 0;
    private File file;
    private FileOutputStream fos;
    private DataOutputStream dos;

    public Receiver (InputStream in, OutputStream out) {
        dis = new DataInputStream(in);
        dos = new DataOutputStream(out);
    }

    public void receive () throws IOException {
        while (true) {
            if (state == State.GETNAME || state == State.GETCHUNK) {
                size = dis.readInt();
                dis.readFully(chunk, 0, size);
            }

            switch (state) {
                case GETNAME:
                    createFile();
                    state = State.GETSIZE;
                    break;
                case GETSIZE:
                    state = State.GETCHUNK;
                    fullSize = dis.readLong();
                    System.out.println(fullSize);
                    break;
                case GETCHUNK:
                    if (!writeToFile()) {
                        state = State.FINISH;
                    }
                    break;
                case FINISH:
                    dos.writeLong(file.length());
                    return;
            }
        }
    }

    private void createFile() throws UnsupportedEncodingException, FileNotFoundException {
        String filename = new String (Arrays.copyOfRange(chunk, 0, size), "UTF-8");
        file = new File(filename);
        File dir = new File("uploads");
        if (!dir.exists()) {
            dir.mkdir();
        }
        filename = "uploads/" + file.getName();
        System.out.println(filename);
        file = new File(filename);
        fos = new FileOutputStream(filename);
    }

    private boolean writeToFile () throws IOException {
        fos.write(chunk, 0, size);
        currSize += size;
        return currSize != fullSize;
    }

    public void finish () throws IOException {
        dos.close();
        dis.close();
    }
}
