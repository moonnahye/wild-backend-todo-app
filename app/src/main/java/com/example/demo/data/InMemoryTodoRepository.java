package com.example.demo.data;

import com.example.demo.application.TodoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryTodoRepository implements TodoRepository {

    private final List<Todo> todoList = new ArrayList<>();

    @Override
    public void add(Todo todo) {
        todoList.add(todo);
    }

    @Override
    public List<Todo> getAll() {
        return new ArrayList<>(todoList);
    }

    @Override
    public Todo getTodo(int id) {
        return todoList.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        Todo todo = getTodo(id);
        todoList.remove(todo);
    }
}
