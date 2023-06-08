package service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DownloadRunnable implements Runnable{
    private final ServerSocket serverSocket;
    private final String path;

    public DownloadRunnable(ServerSocket serverSocket, String path) {
        this.serverSocket = serverSocket;
        this.path = path;
    }

    @Override
    public void run() {
        Socket socket;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            OutputStream outputStream = socket.getOutputStream();
            File file = new File(path);
            String fileName = file.getName();
            long fileLength = file.length();
            FileInputStream fileInputStream = new FileInputStream(path);

            byte[] bytes = new byte[1024];
            int i = 0;
            while ((i = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, i);
            }
            outputStream.flush();
            fileInputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
