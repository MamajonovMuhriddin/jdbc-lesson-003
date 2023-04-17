package main.java;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyServer {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
         //   server.createContext("/user", new UserHandler());
            server.createContext("/pet", new PetHandler()); //todo создать таблицу pet (id, nickname, type), получать список, сохранять одного пета
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
