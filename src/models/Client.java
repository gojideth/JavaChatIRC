package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;

public class Client implements Runnable {

    private String nickname;
    private String IP;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Scanner scanner;
    private String HOST = "127.0.0.1";
    private final int PORT = 5000;
    private boolean isLogged;


    public Client(String nickname, String iP) {
        this.nickname = nickname;
        this.IP = iP;
        this.scanner = new Scanner(System.in);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String write(){
        return scanner.nextLine();
    }

    public void read(String message){
        System.out.println("Mensaje recibido: " + LocalTime.now() + message);
    }

    public void connect(){
        try {

            this.socket = new Socket(HOST,PORT);

            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String messageReceived = dataInputStream.readUTF();
            System.out.println(messageReceived);
            dataOutputStream.writeUTF(this.getNickname());
            dataOutputStream.writeUTF(this.getIP());
            dataOutputStream.writeChars(scanner.nextLine());

            this.closeDataStreams();
            this.closeSocket();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeDataStreams() throws IOException {
        this.dataInputStream.close();
        this.dataOutputStream.close();
    }


    @Override
    public void run() {
        connect();
    }
}
