package service;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private final ServerSocket serverSocket;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Server() throws IOException {
        serverSocket = new ServerSocket(8888);
        System.out.println("服务端已启动！");
    }
}
