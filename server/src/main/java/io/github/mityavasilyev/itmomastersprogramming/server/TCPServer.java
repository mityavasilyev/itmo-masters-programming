package io.github.mityavasilyev.itmomastersprogramming.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TCPServer {
    private static final int PORT = 1234;
    private static final Map<String, String> store = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Сервер запущен на порту " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String[] command = inputLine.split(" ");
                    String response = "";

                    try {
                        switch (command[0]) {
                            case "store" -> {
                                store.put(command[1], command[2]);
                                response = "Значение сохранено";
                            }
                            case "load" -> response = store.getOrDefault(command[1], "Не найдено");
                            case "search" -> response = store.keySet().stream()
                                    .filter(key -> key.contains(command[1]))
                                    .collect(Collectors.joining(", "));
                            case "check" -> {
                                String value = store.get(command[1]);
                                if (value != null && value.contains(command[2])) {
                                    response = "Проверка прошла успешно";
                                } else {
                                    response = "Проверка не прошла";
                                }
                            }
                            case "put" -> {
                                store.put(command[1], command[2]);
                                response = "Ключ и значение сохранены";
                            }
                            case "get" -> response = store.getOrDefault(command[1], "Не найдено");
                            default -> response = "Неизвестная команда";
                        }
                    } catch (Exception e) {
                        response = e.getMessage();
                    }

                    out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
