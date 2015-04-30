package ru.nsu.fit.g13202.sartakov.client;

import java.io.*;

/**
 * Created by arty on 30.04.15.
 */
public class Sender {
    private DataOutputStream dos;
    private State state = State.SENDNAME;
    private String filename;
    private FileInputStream fis;
    private File file;
    private static final int BUFSIZE = 4096;
    private int length = 0;
    private byte [] b;
    private DataInputStream dis;

    public Sender (OutputStream os, InputStream is, String filename) throws FileNotFoundException {
        dos = new DataOutputStream(os);
        dis = new DataInputStream(is);
        file = new File (filename);
        fis = new FileInputStream(filename);
        this.filename = filename;
    }

    public boolean send() throws IOException {
        while (true) {
            switch (state) {
                case SENDNAME:
                    b = filename.getBytes("UTF-8");
                    length = filename.length();
                    System.out.println(b.length);
                    sendChunk();
                    b = new byte[BUFSIZE];
                    state = State.SENDSIZE;
                    break;
                case SENDSIZE:
                    dos.writeLong(file.length());
                    state = State.SENDCHUNK;
                    break;
                case SENDCHUNK:
                    length = fis.read(b, 0, BUFSIZE);
                    if (length == -1) {
                        fis.close();
                        state = State.FINISH;
                        break;
                    }
                    sendChunk();
                    break;
                case FINISH:
                    long received = dis.readLong();
                    return received == file.length();
            }
        }
    }

    private void sendChunk () throws IOException {
        dos.writeInt(length);
        dos.write(b, 0, length);                                               //check
    }

    public void finish () throws IOException {
        dos.close();
        dis.close();
    }
}
