package com.example.demo.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ResourceHandler {
    String handle(String content) throws JsonProcessingException;
}
