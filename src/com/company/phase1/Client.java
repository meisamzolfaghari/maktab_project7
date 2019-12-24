package com.company.phase1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket = null;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;

    public Client(String address, int port) {
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket(address, port);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
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
                System.out.print("type: ");
                clientResponse = scanner.nextLine();
                outputStream.writeUTF(clientResponse);
                if (clientResponse.equals("exit")) {
                    break;
                }

                serverResponse = inputStream.readUTF();
                System.out.println("Server >> " + serverResponse);

            } catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }

}
