package io.github.mityavasilyev.itmomastersprogramming.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connected to server. Enter commands to send.");

        while (true) {
            System.out.print("Введите команду: ");
            String command = userInput.readLine();
            if (command == null || command.equalsIgnoreCase("exit")) break;

            out.println(command);
            String response = in.readLine();
            if (response != null) {
                System.out.println("Ответ сервера: " + response);
            } else {
                System.out.println("Server closed the connection.");
                break;
            }
        }

        socket.close();
    }
}
