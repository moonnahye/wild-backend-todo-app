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
        handlers.put("GET /todo", new TodoListResource());
        handlers.put("PUT /todo", new TodoStatusChangeResource());
        handlers.put("DELETE /todo", new TodoDeleteResource());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestKey = getRequestKey(exchange);

        if (!handlers.containsKey(requestKey)) {
            exchange.sendResponseHeaders(404, -1);
        }

        ResourceHandler handler = handlers.get(requestKey);

        String requestContent = getRequestContent(exchange);
        String responseContent = handler.handle(requestContent);

        sendResponse(exchange, responseContent);
    }

    private String getRequestKey(HttpExchange exchange) {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String method = exchange.getRequestMethod();

        return method + " " + path;
    }

    private String getRequestContent(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        return new String(inputStream.readAllBytes());
    }

    private void sendResponse(HttpExchange exchange, String responseContent) throws IOException {
        byte[] bytes = responseContent.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
