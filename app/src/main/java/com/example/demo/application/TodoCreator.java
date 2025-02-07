package com.example.demo.application;

import com.example.demo.data.Todo;
import com.example.demo.data.TodoRepository;

import java.util.List;

public class TodoCreator {

    private final TodoRepository todoRepository = TodoRepository.getInstance();

    public Todo make(String content) {
        Todo todo = new Todo(content, false);
        todoRepository.add(todo);
        return todo;
    }

    public List<Todo> getTodoList() {
        return todoRepository.getAll();
    }
}
