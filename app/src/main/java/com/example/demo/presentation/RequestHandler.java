package com.example.demo.presentation;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements HttpHandler {

    private final Map<String, ResourceHandler> handlers = new HashMap<>();

    public RequestHandler() {
        handlers.put("GET /", new HomeResource());
        handlers.put("POST /todo", new TodoCreateResource());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String method = exchange.getRequestMethod();

        String requestKey = method + " " + path;

        if(!handlers.containsKey(requestKey)) {
            exchange.sendResponseHeaders(404, -1);
        }

        ResourceHandler handler = handlers.get(requestKey);

        InputStream inputStream = exchange.getRequestBody();
        String requestContent = new String(inputStream.readAllBytes());

        String responseContent = handler.handle(requestContent);

        byte[] bytes = responseContent.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
