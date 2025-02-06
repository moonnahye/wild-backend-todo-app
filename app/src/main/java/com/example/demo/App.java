package com.example.demo;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }

    public void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 8080);
        HttpServer httpServer = HttpServer.create(address, 0);
        httpServer.createContext("/", exchange -> {
            URI uri = exchange.getRequestURI();
            String path = uri.getPath();
            String method = exchange.getRequestMethod();

            String content = "";

            if(path.equals("/") && method.equals("GET")) {
                content = "Hello World \n";
            }
            if(content.isEmpty()){
                exchange.sendResponseHeaders(204, 0);
            }
            byte[] bytes = content.getBytes();
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(bytes);
            outputStream.close();
        });
        httpServer.start();
    }
}
