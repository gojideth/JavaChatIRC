package test;

import models.Server;

public class TestServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.createSocket();
    }
}
