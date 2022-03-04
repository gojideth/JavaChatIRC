package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server  implements Runnable{
    private ArrayList<Client> clientArrayList;
    private ServerSocket serverSocket;
    private Socket mySocket = null;
    private final int PORT = 5000;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Scanner scanner;

    public Server() {
        this.clientArrayList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }


    public void createSocket(){
        try {
            this.serverSocket = new ServerSocket(PORT);

            System.out.println("models.Server initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            mySocket = serverSocket.accept();
            this.dataInputStream = new DataInputStream(mySocket.getInputStream());
            this.dataOutputStream = new DataOutputStream(mySocket.getOutputStream());
            dataOutputStream.writeUTF("Hola mundo desde el server");
            String clientNickname = dataInputStream.readUTF();
            String clientIP = dataInputStream.readUTF();
            System.out.println("Someone has logged in:  "+"\n" +clientNickname + ", with IP: " + clientIP );
            this.addClient(this.createClientFromData(clientNickname,clientIP));
            this.showArrayList();
        }

    }

    public String obtenerInfo(){

    }


    public void metodo(){

        this.clientArrayList.get(0).getDataInputStream();

    }

    public int searchUser(String nick){
        int aux = -1;
        for (int i = 0; i < this.clientArrayList.size(); i++) {
            if (nick.equals(clientArrayList.get(i).getNickname())){
                aux = i;
            }
        }
        return aux;
    }

    public Client createClientFromData(String nickname, String IP){
        return new Client(nickname,IP);

    }

    public void addClient(Client client){
        this.clientArrayList.add(client);

    }

    public void permanentReading(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                refreshMessages();
            }
        }).start();
    }


    public void refreshMessages(){
        try {
            if (!this.dataInputStream.readUTF().equals(" ")){
                this.dataOutputStream.writeUTF(this.dataInputStream.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showArrayList(){
        System.out.println("-----Lista de clientes-----");
        for (Client client : clientArrayList) {
            System.out.println("Nickname:" + client.getNickname() + ", IP: " + client.getIP());
        }
    }

    @Override
    public void run() {
        createSocket();
    }
}
