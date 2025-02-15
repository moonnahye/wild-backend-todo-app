package com.example.demo.application;

import com.example.demo.data.Todo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoManager {

    private final TodoRepository todoRepository;
    private static int sequence = 0;

    public TodoManager(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo make(String content) {
        Todo todo = new Todo(++sequence, content, false);
        todoRepository.add(todo);
        return todo;
    }

    public List<Todo> getTodoList() {
        return todoRepository.getAll();
    }

    public Todo getTodo(int id) {
        return todoRepository.getTodo(id);
    }

    public Todo updateTodo(int id) {
        Todo todo = todoRepository.getTodo(id);
        todo.changeStatus();
        return todo;
    }

    public List<Todo> deleteTodo(int id) {
        todoRepository.delete(id);
        return todoRepository.getAll();
    }
}
