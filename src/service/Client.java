package service;

import java.io.*;
import java.net.Socket;

public class Client {

    public int testConnect(String IP, int Port) {
        try (Socket socket = new Socket(IP, Port);){
            System.out.println("测试链接成功！");
            socket.close();
            return 0;
        } catch (IOException e) {
            System.out.println("测试链接失败！");
            throw new RuntimeException(e);
        }
    }

    public void uploadFile(String IP, int Port, String path) throws IOException {
        File file = new File(path);
        String fileName = file.getName();
        long fileLength = file.length();

        Socket socket = new Socket(IP, Port);
        OutputStream outputStream = socket.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] bytes = new byte[1024];
        int i = 0;
        while ((i = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, i);
        }
        // 释放资源
        outputStream.flush();
        fileInputStream.close();
        socket.close();
    }
    public void downloadFile(String IP, int Port, String path) {
        File file = new File(path);
        String fileName = file.getName();
        long fileLength = file.length();
        System.out.println("下载文件：" + fileName + "，其大小为：" + fileLength + "字节");
        Socket socket = null;
        try {
            socket = new Socket(IP, Port);
            InputStream inputStream = socket.getInputStream();

            String fileDir = "E:\\ClientFile\\";
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
