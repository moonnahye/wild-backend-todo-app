package com.example.demo.presentation;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class RequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String method = exchange.getRequestMethod();

        String content = "";

        if(path.equals("/") && method.equals("GET")) {
            content = "Hello World \n";
        }

        if(path.equals("/todo") && method.equals("POST")) {
            content = "Todo-1 \n";
        }

        if(content.isEmpty()){
            exchange.sendResponseHeaders(204, 0);
        }

        byte[] bytes = content.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
