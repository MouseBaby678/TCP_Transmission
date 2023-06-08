package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestConnectRunnable implements Runnable{
    private final ServerSocket serverSocket;

    public TestConnectRunnable(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        Socket socket;
        try {
            socket = serverSocket.accept();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
