package com.company.phase1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;

    public Client(String address, int port) {
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket(address, port);
            System.out.println("Client is Connected...");

        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        String serverResponse = "";
        String clientResponse = "";
        while (true) {
            try {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                System.out.print("type: ");
                clientResponse = scanner.nextLine();
                dataOutputStream.writeUTF(clientResponse);
                if (clientResponse.equals("exit"))
                    break;

                dataInputStream = new DataInputStream(socket.getInputStream());
                serverResponse = dataInputStream.readUTF();
                System.out.println("Server >> " + serverResponse);

            } catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }

}
