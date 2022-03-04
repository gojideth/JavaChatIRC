package test;

import models.Client;

import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestClientGoji {
    public static void main(String[] args) {
        try {
            Client myClient = new Client("Gojideth", InetAddress.getLocalHost().getHostAddress());
            myClient.connect();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
