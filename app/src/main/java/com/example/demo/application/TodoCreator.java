package com.example.demo.application;

import com.example.demo.data.Todo;
import com.example.demo.data.TodoRepository;

public class TodoCreator {

    private final TodoRepository todoRepository = new TodoRepository();

    public Todo make(String content) {
        Todo todo = new Todo(content, false);
        todoRepository.add(todo);
        return todo;
    }
}
