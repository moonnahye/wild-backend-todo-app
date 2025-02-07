package com.example.demo.application;

import com.example.demo.data.Todo;

public class TodoCreator {

    public Todo make(String content) {
        return new Todo(content, false);
    }
}
