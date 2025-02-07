package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {

    private final List<Todo> todoList = new ArrayList<>();

    private static TodoRepository instance = null;

    protected TodoRepository(){
    }

    public static TodoRepository getInstance(){
        if(instance == null){
            instance = new TodoRepository();
        }
        return instance;
    }

    public void add(Todo todo) {
        todoList.add(todo);
    }

    public List<Todo> getAll() {
        return new ArrayList<>(todoList);
    }
}
