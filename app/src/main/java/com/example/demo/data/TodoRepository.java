package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {

    private final List<Todo> todoList = new ArrayList<>();

    public void add(Todo todo) {
        todoList.add(todo);
    }

}
