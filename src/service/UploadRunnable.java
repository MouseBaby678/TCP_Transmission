package service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadRunnable implements Runnable {
    private final ServerSocket serverSocket;
    private final String path;

    public UploadRunnable(ServerSocket serverSocket, String path) {
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
            InputStream inputStream = socket.getInputStream();
            File file = new File(path);
            String fileName = file.getName();
            long fileLength = file.length();
            System.out.println("上传文件：" + fileName + "，其大小为：" + fileLength + "字节");

            String fileDir = "E:\\ServerFile\\";
            String filePath = fileDir + fileName;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            byte[] bytes = new byte[1024];
            int i = 0;
            while ((i = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, i);
            }

            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
