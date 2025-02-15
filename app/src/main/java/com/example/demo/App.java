package com.example.demo;

import com.example.demo.presentation.RequestHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.InetSocketAddress;

@SpringBootApplication
public class App {
    public static void main(String[] args)  {
        SpringApplication.run(App.class, args);
    }
}
