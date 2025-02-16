package com.example.demo.application;

import com.example.demo.data.Todo;

import java.util.List;

public interface TodoRepository {

    void add(Todo todo);

    List<Todo> getAll();

    Todo getTodo(int id);

    void delete(int id);
}
