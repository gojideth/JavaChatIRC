package test;

import models.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestClientArq {
    public static void main(String[] args) {
        try {
            Client myClient = new Client("Arcuca", InetAddress.getLocalHost().getHostAddress());
            myClient.connect();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
