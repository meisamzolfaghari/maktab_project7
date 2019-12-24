package com.company.phase1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public Server(int port) {

        Scanner scanner = new Scanner(System.in);

        try {
            server = new ServerSocket(port);
            System.out.println("Server started.");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted.");

            String serverResponse = "";
            String clientResponse = "";

            while (true) {
                try {
                    input = new DataInputStream(socket.getInputStream());
                    output = new DataOutputStream(socket.getOutputStream());
                    clientResponse = input.readUTF();
                    System.out.println("Client >> " + clientResponse);
                    System.out.print("type: ");
                    serverResponse = scanner.nextLine();
                    if (serverResponse.equals("exit"))
                        break;
                    output.writeUTF(serverResponse);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection.");

            socket.close();
            input.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(5000);
    }

}
