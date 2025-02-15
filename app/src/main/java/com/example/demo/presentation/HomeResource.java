package com.example.demo.presentation;

public class HomeResource implements ResourceHandler {
    @Override
    public String handle(String content){
        return "home";
    }
}
